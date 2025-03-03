// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Base WPI Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// Informational Imports
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.RobotState;

// Controller Imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Command Imports
import frc.robot.commands.DriveCommands.DriveStopCommand;
import frc.robot.commands.DriveCommands.TeleopMoveCommand;
import frc.robot.commands.GrabberCommands.ServoLockCommand;
import frc.robot.commands.GrabberCommands.ServoUnlockCommand;
import frc.robot.commands.GrabberCommands.AlgaeDetectionCommand;
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
import frc.robot.commands.ArmCommands.ArmExtensionStopCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

// Utility Imports
import frc.robot.utils.AutoPicker;
import frc.robot.utils.SubsystemList;

// Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {

    /*  Subsystem and SubsystemList Declarations  */

    LEDSubsystem led = new LEDSubsystem(32, 19); // 60 and 10 are placeholders
    DriveSubsystem drive = new DriveSubsystem();
    GrabberSubsystem grabber = new GrabberSubsystem();
    ClawArmSubsystem clawArm = new ClawArmSubsystem();
    ClimbSubsystem climb = new ClimbSubsystem();
    ClawSubsystem claw = new ClawSubsystem();
    GrabberArmSubsystem grabberArm = new GrabberArmSubsystem();
    SubsystemBase[] subsystems = { drive, clawArm, grabberArm, grabber, claw, climb };
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
    // LED Triggers

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


        // CLAW

        buttonBoard.button(1).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 90, 1).alongWith(new WristPivotCommand(subsystemList, 95, 0.5)));
        buttonBoard.button(2).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 105, 1).alongWith(new WristPivotCommand(subsystemList, 90, 0.5)));
        buttonBoard.button(3).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 7, 1).alongWith(new WristPivotCommand(subsystemList, 120, 0.5)).raceWith(new WaitCommand(0.5)).andThen(new ArmMoveCommand(subsystemList, armType.claw, 9, .5).alongWith(new ArmPivotHoldCommand(subsystemList, armType.claw)).raceWith(new WaitCommand(0.5)).andThen(new ArmMoveCommand(subsystemList, armType.claw, 0, .5).alongWith(new ArmPivotHoldCommand(subsystemList, armType.claw)))));
        buttonBoard.button(4).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 110, 1).alongWith(new WristPivotCommand(subsystemList, 150, 0.5)).raceWith(new WaitCommand(1)).andThen(new ArmPivotHoldCommand(subsystemList, armType.claw).alongWith(new ArmMoveCommand(subsystemList, armType.claw, 125, 0.5))));
        buttonBoard.button(5).onTrue(new ArmFullStopCommand(subsystemList, armType.claw));
        buttonBoard.button(6).onTrue(new ClawOpenCommand(subsystemList, 0.30).andThen(new WaitCommand(0.25).andThen(new ClawCloseCommand(subsystemList, 0.06))));
        buttonBoard.button(7).onTrue(new ArmMoveCommand(subsystemList, armType.claw, 0, 0.5).andThen(new ArmPivotCommand(subsystemList, armType.claw, 5, 1).raceWith(new WaitCommand(0.5))).andThen(new ArmPivotStopCommand(subsystemList, armType.claw)));
        buttonBoard.button(8).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 40, 1).alongWith(new WristPivotCommand(subsystemList, 5, 0.5)));

        // buttonBoard.button(4).onTrue(new ClawOpenCommand(subsystemList, .3));
        // buttonBoard.button(3).onTrue(new ClawCloseCommand(subsystemList, 0.06));
        // buttonBoard.button(2).onTrue(new ArmPivotCommand(subsystemList, armType.claw, 85, 1));
        // buttonBoard.button(1).onTrue(new ArmMoveCommand(subsystemList, armType.claw, 10, .5));
        // buttonBoard.button(8).onTrue(new WristPivotCommand(subsystemList, 70, 1));
        // buttonBoard.button(7).onTrue(new WristPivotCommand(subsystemList, 0, 1));
        // buttonBoard.button(6).onTrue(new ClawStopCommand(subsystemList));
        // buttonBoard.button(5).onTrue(new ArmFullStopCommand(subsystemList, armType.claw));

        // buttonBoard.button(4).onTrue(new ArmMoveCommand(subsystemList, armType.grabber, 30, .3));
        // buttonBoard.button(3).onTrue(new ArmMoveCommand(subsystemList, armType.grabber, 0, .30));
        // buttonBoard.button(8).onTrue(new ArmExtensionStopCommand(subsystemList, armType.claw));

        // // buttonBoard.button(4).onTrue(new WristPivotCommand(subsystemList,120, 1));
        // // buttonBoard.button(3).onTrue(new WristPivotCommand(subsystemList, 40, 1));
        // // buttonBoard.button(2).onTrue(new WristStopCommand(subsystemList));

        // GRABBER

        // buttonBoard.button(5).onTrue(new ServoUnlockCommand(subsystemList));
        // buttonBoard.button(4).onTrue(new ArmPivotCommand(subsystemList, armType.grabber, 85, 1));
        // buttonBoard.button(3).onTrue(new ArmPivotCommand(subsystemList, armType.grabber, 13, 1));
        // buttonBoard.button(2).onTrue((new GrabberIntakeCommand(subsystemList, .25).raceWith(new WaitCommand(.1))).andThen(new GrabberStopCommand(subsystemList)).andThen((new GrabberShootCommand(subsystemList, .05).raceWith(new WaitCommand(.5)))));
        // buttonBoard.button(1).onTrue((new GrabberIntakeCommand(subsystemList, .40).raceWith(new AlgaeDetectionCommand(subsystemList))).andThen(new ServoLockCommand(subsystemList)).andThen(new WaitCommand(0.5)).andThen(new GrabberStopCommand(subsystemList)).andThen(new ServoUnlockCommand(subsystemList)));
        // buttonBoard.button(5).onTrue(new ArmFullStopCommand(subsystemList, armType.grabber));
        // buttonBoard.button(7).onTrue((new GrabberIntakeCommand(subsystemList, .25).raceWith(new WaitCommand(.1))).andThen(new GrabberStopCommand(subsystemList)).andThen((new GrabberShootCommand(subsystemList, .6).raceWith(new WaitCommand(.5)))));
        // buttonBoard.button(6).onTrue(new GrabberStopCommand(subsystemList));

        // CLIMB

        // buttonBoard.button(4).onTrue(new ClimbRotateCommand(subsystemList, .3, -30, .2));
        // buttonBoard.button(3).onTrue(new ClimbRotateCommand(subsystemList, .15, 7, .2));
        // buttonBoard.button(2).onTrue(new ClimbRotateCommand(subsystemList, .30, 7, .2));

        displayDashboard();

    }

    public Command getAutonomousCommand() {

        return autoPicker.GetAuto();

    }

    public void displayDashboard() {

        clawArm.DisplayDebuggingInfo();
        claw.DisplayDebuggingInfo();
        drive.DisplayDebuggingInfo();

    }

}