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
import frc.robot.commands.ClawArmCommands.ClawArmExtendCommand;
import frc.robot.commands.ClawArmCommands.ClawArmRetractCommand;
import frc.robot.commands.ClawArmCommands.ClawArmExtensionStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotExtendCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotRetractCommand;
import frc.robot.commands.ClawArmCommands.ClawArmFullStopCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;

// Utility Imports
import frc.robot.utils.AutoPicker;
import frc.robot.utils.SubsystemList;

// Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {

  /*  Subsystem and SubsystemList Declarations  */

  DriveSubsystem drive = new DriveSubsystem();
  ClawArmSubsystem clawArm = new ClawArmSubsystem();
  GrabberArmSubsystem grabberArm = new GrabberArmSubsystem();
  SubsystemBase[] subsystems = { drive, clawArm, grabberArm };
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
    buttonBoard.button(4).onTrue(new ClawArmExtendCommand(subsystemList, 1000, 0.1));
    buttonBoard.button(3).onTrue(new ClawArmRetractCommand(subsystemList, 0, 0.1));
    buttonBoard.button(2).onTrue(new ClawArmExtensionStopCommand(subsystemList));
    buttonBoard.button(1).onTrue(new ClawArmPivotCommand(subsystemList, 180));
    buttonBoard.button(8).onTrue(new ClawArmPivotStopCommand(subsystemList));
    buttonBoard.button(7).onTrue(new ClawArmPivotExtendCommand(subsystemList, 90, 500, 0.1));
    buttonBoard.button(6).onTrue(new ClawArmPivotRetractCommand(subsystemList, 0, 0, 0.1));
    buttonBoard.button(5).onTrue(new ClawArmFullStopCommand(subsystemList));


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