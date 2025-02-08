// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Base WPI Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
import frc.robot.commands.ArmCommands.ArmExtensionStopCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.patternType;
import frc.robot.subsystems.ClimbSubsytem;
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
  LEDSubsystem led = new LEDSubsystem(60, 10); // 60 and 10 are placeholders
  
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
        // buttonBoard.button(8).onTrue(new GrabberIntakeCommand(subsystemList, 0.1)); //.raceWith(new AlgeaDetectionCommand(subsystemList)));
        // buttonBoard.button(7).onTrue(new GrabberShootCommand(subsystemList, 1));
        // buttonBoard.button(6).onTrue(new GrabberStopCommand(subsystemList));

        buttonBoard.button(4).onTrue(new ArmMoveCommand(subsystemList, armType.claw, 6.6, .05));
        buttonBoard.button(3).onTrue(new ArmExtensionStopCommand(subsystemList, armType.claw));

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