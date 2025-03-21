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
import frc.robot.commands.ArmCommands.ArmIdleCommand;
import frc.robot.commands.ButtonCommands.Claw.CoralFeederIntakeCommand;
import frc.robot.commands.ButtonCommands.Claw.CoralFeederPrepCommand;
import frc.robot.commands.ButtonCommands.Claw.CoralLevelCommand;
import frc.robot.commands.ButtonCommands.Claw.OpenClawCommand;
import frc.robot.commands.ButtonCommands.Climb.ClimbGrabCommand;
import frc.robot.commands.ButtonCommands.Climb.ClimbRaiseCommand;
import frc.robot.commands.ButtonCommands.Grabber.AlgaeIntakeCommand;
import frc.robot.commands.ButtonCommands.Grabber.AlgaeNetCommand;
import frc.robot.commands.ButtonCommands.Grabber.AlgaeProcessorCommand;
import frc.robot.commands.ButtonCommands.Grabber.AlgaeShootCommand;
import frc.robot.commands.ButtonCommands.Safety.CancelCommand;
import frc.robot.commands.ButtonCommands.Safety.StowCommand;
import frc.robot.commands.ClawCommands.WristIdleCommand;
// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.subsystems.LEDSubsystem.patternType;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

// Utility Imports
import frc.robot.utils.AutoPicker;
import frc.robot.utils.LEDTriggerManager;
import frc.robot.utils.SubsystemList;

// Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {

    /*  Subsystem and SubsystemList Declarations  */

    LEDSubsystem led = new LEDSubsystem(62);
    DriveSubsystem drive = new DriveSubsystem();
    GrabberSubsystem grabber = new GrabberSubsystem();
    ClawArmSubsystem clawArm = new ClawArmSubsystem();
    ClimbSubsystem climb = new ClimbSubsystem();
    ClawSubsystem claw = new ClawSubsystem();
    GrabberArmSubsystem grabberArm = new GrabberArmSubsystem();
    SubsystemBase[] subsystems = { drive, clawArm, grabberArm, grabber, claw, climb, led };
    SubsystemList subsystemList = new SubsystemList(subsystems);
  
    /*  Controller Declarations  */

    CommandXboxController commandDriverController = new CommandXboxController(Constants.DriveControllerPort);
    XboxController driverController = new XboxController(Constants.DriveControllerPort);

    CommandXboxController commandOperatorController = new CommandXboxController(Constants.OperatorControllerPort);
    XboxController operatorController = new XboxController(Constants.OperatorControllerPort);

    CommandGenericHID upperButtonBoard = new CommandGenericHID(Constants.UpperButtonBoardPort);
    CommandGenericHID lowerButtonBoard = new CommandGenericHID(Constants.LowerButtonBoardPort);

    AutoPicker autoPicker = new AutoPicker(subsystemList);

    public RobotContainer() {

        drive.setDefaultCommand(new DriveStopCommand(subsystemList));
        clawArm.setDefaultCommand(new ArmIdleCommand(subsystemList, armType.claw));
        grabberArm.setDefaultCommand(new ArmIdleCommand(subsystemList, armType.grabber));
        claw.setDefaultCommand(new WristIdleCommand(subsystemList));

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

        upperButtonBoard.button(1).onTrue(new CoralFeederPrepCommand(subsystemList));
        upperButtonBoard.button(2).onTrue(new CoralLevelCommand(subsystemList, 1));
        upperButtonBoard.button(3).onTrue(new CoralLevelCommand(subsystemList, 2));
        upperButtonBoard.button(4).onTrue(new CoralLevelCommand(subsystemList, 3));
        upperButtonBoard.button(5).onTrue(new CoralLevelCommand(subsystemList, 4));
        upperButtonBoard.button(6).onTrue(new CoralFeederIntakeCommand(subsystemList));
        upperButtonBoard.button(7).onTrue(new CancelCommand(subsystemList)); // Coral floor prep
        upperButtonBoard.button(8).onTrue(new CancelCommand(subsystemList)); // Coral floor grab
        upperButtonBoard.button(9).onTrue(new StowCommand(subsystemList, armType.claw));
        upperButtonBoard.button(10).onTrue(new OpenClawCommand(subsystemList));

        lowerButtonBoard.button(1).onTrue(new AlgaeIntakeCommand(subsystemList, "floor"));
        lowerButtonBoard.button(2).onTrue(new AlgaeIntakeCommand(subsystemList, "lower"));
        lowerButtonBoard.button(3).onTrue(new AlgaeIntakeCommand(subsystemList, "upper"));
        lowerButtonBoard.button(4).onTrue(new AlgaeProcessorCommand(subsystemList));
        lowerButtonBoard.button(5).onTrue(new AlgaeNetCommand(subsystemList));
        lowerButtonBoard.button(6).onTrue(new AlgaeShootCommand(subsystemList));
        lowerButtonBoard.button(7).onTrue(new ClimbGrabCommand(subsystemList));
        lowerButtonBoard.button(8).onTrue(new ClimbRaiseCommand(subsystemList));
        lowerButtonBoard.button(9).onTrue(new StowCommand(subsystemList, armType.grabber));
        lowerButtonBoard.button(10).onTrue(new CancelCommand(subsystemList));
     
        displayDashboard();

        new LEDTriggerManager(subsystemList);

    }

    public Command getAutonomousCommand() {

        return autoPicker.GetAuto();

    }

    public void displayDashboard() {

        clawArm.DisplayDebuggingInfo();
        claw.DisplayDebuggingInfo();
        grabberArm.DisplayDebuggingInfo();
        grabber.DisplayDebuggingInfo();
        drive.DisplayDebuggingInfo();

    }



    public SubsystemList getSubsystemList() {

        return subsystemList;

    }

}