// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Base WPI Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Controller Imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Command Imports
import frc.robot.commands.DriveCommands.DriveStopCommand;
import frc.robot.commands.DriveCommands.TeleopMoveCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.GrabberCommands.AlgeaDetectionCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GrabberSubsystem;

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
  SubsystemBase[] subsystems = { drive, grabber };
  SubsystemList subsystemList = new SubsystemList(subsystems);
  
  /*  Controller Declarations  */

  CommandXboxController commandDriverController = new CommandXboxController(Constants.DRIVE_CONTROL_PORT);
  XboxController driverController = new XboxController(Constants.DRIVE_CONTROL_PORT);

  CommandXboxController commandOperatorController = new CommandXboxController(Constants.OPERATOR_CONTROL_PORT);
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROL_PORT);

  CommandGenericHID buttonBoard = new CommandGenericHID(Constants.BUTTON_BOARD_PORT);

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
    buttonBoard.button(4).onTrue(new GrabberIntakeCommand(subsystemList, .5)); // Works
    buttonBoard.button(3).onTrue(new GrabberShootCommand(subsystemList, .5)); // Works
    buttonBoard.button(2).onTrue(new GrabberStopCommand(subsystemList)); // Works
    buttonBoard.button(1).onTrue(new AlgeaDetectionCommand(subsystemList)); // Works

    displayDashboard();
  }

  public Command getAutonomousCommand() {
    return autoPicker.GetAuto();
  }

  public void displayDashboard() {
    // SMART DASHBOARD
    // import.method();
    
    // SHUFFLEBOARD
    // Shuffleboard.getTab("TabName").addDouble("DisplayName", () -> import.method())
    //   .withWidget("Widget")
    //   .withPosition(x, y);

  }
}