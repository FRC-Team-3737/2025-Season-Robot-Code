package frc.robot.commands.ButtonCommands.Safety;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.GrabberSubsystem;

import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class StowCommand extends SequentialCommandGroup {

    public StowCommand(SubsystemList subsystems, armType type) {

        GrabberSubsystem grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

        switch (type) {

            case grabber:
                addCommands(
                    new ArmPivotCommand(subsystems, type, 0, 0)
                );

            case claw:
                addCommands(
                    new ArmPivotCommand(subsystems, type, 0, 0)
                );

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, type)
                );

        }

    }

}
