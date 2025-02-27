// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Base WPI Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// Controller Imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Command Imports
import frc.robot.commands.DriveCommands.DriveStopCommand;
import frc.robot.commands.DriveCommands.TeleopMoveCommand;
import frc.robot.commands.GrabberCommands.AlgeaDetectionCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawCloseCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;
import frc.robot.commands.ButtonTestCommands.TestWristCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.ButtonTestCommands.TestClawCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.subsystems.ClawSubsystem;

// Utility Imports
import frc.robot.utils.AutoPicker;
import frc.robot.utils.SubsystemList;

// Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {

    /*  Subsystem and SubsystemList Declarations  */

    DriveSubsystem drive = new DriveSubsystem();
    GrabberSubsystem grabber = new GrabberSubsystem();
    ClawArmSubsystem clawArm = new ClawArmSubsystem();
    ClimbSubsystem climb = new ClimbSubsystem();
    ClawSubsystem claw = new ClawSubsystem();
    GrabberArmSubsystem grabberArm = new GrabberArmSubsystem();
    SubsystemBase[] subsystems = { drive, clawArm, grabberArm, grabber, claw };
    SubsystemList subsystemList = new SubsystemList(subsystems);
  
    /*  Controller Declarations  */

    CommandXboxController commandDriverController = new CommandXboxController(Constants.DriveControllerPort);
    XboxController driverController = new XboxController(Constants.DriveControllerPort);

    CommandXboxController commandOperatorController = new CommandXboxController(Constants.OperatorControllerPort);
    XboxController operatorController = new XboxController(Constants.OperatorControllerPort);

    CommandGenericHID buttonBoard = new CommandGenericHID(Constants.ButtonBoardPort);

    AutoPicker autoPicker = new AutoPicker(subsystemList);

    public RobotContainer() {

        drive.setDefaultCommand(new DriveStopCommand(subsystemList));

        configureBindings();

    }

    private void configureBindings() {

        SmartDashboard.putData("Reset Gyro", new InstantCommand(()->{DriveSubsystem.resetGyro(0);}));

        // Driver Triggers

        commandDriverController.axisGreaterThan(0, 0.1)
            .or(commandDriverController.axisLessThan(0, -0.1))
            .or(commandDriverController.axisGreaterThan(1, 0.1))
            .or(commandDriverController.axisLessThan(1, -0.1))
            .or(commandDriverController.axisGreaterThan(2, 0.1))
            .or(commandDriverController.axisGreaterThan(3, 0.1))
            .or(commandDriverController.leftBumper())
            .or(commandDriverController.rightBumper())
            .onTrue(new TeleopMoveCommand(subsystemList, driverController));

        // Operator Triggers
        //  buttonBoard.button(8).onTrue(new GrabberIntakeCommand(subsystemList, 0.1)); //.raceWith(new AlgeaDetectionCommand(subsystemList)));
        //  buttonBoard.button(7).onTrue(new GrabberShootCommand(subsystemList, 1));
        //  buttonBoard.button(6).onTrue(new GrabberStopCommand(subsystemList));

          buttonBoard.button(4).onTrue(new ArmPivotCommand(subsystemList, armType.grabber, 135, .5));
          buttonBoard.button(3).onTrue(new ArmPivotStopCommand(subsystemList, armType.grabber));

        // buttonBoard.button(8).onTrue(new TestClawCommand(subsystemList, .3));
        // buttonBoard.button(8).onTrue(new ClawStopCommand(subsystemList));

        // buttonBoard.button(4).onTrue(new WristPivotCommand(subsystemList,120, 1));
        // buttonBoard.button(3).onTrue(new WristPivotCommand(subsystemList, 40, 1));
        // buttonBoard.button(2).onTrue(new WristStopCommand(subsystemList));

        displayDashboard();

    }

    public Command getAutonomousCommand() {

        return autoPicker.GetAuto();

    }

    public void displayDashboard() {

        grabber.DisplayDebuggingInfo();

    }

}