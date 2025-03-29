package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;

import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;

import edu.wpi.first.wpilibj2.command.WaitCommand;

public class CoralLevelAutoCommand extends SequentialCommandGroup {

    public CoralLevelAutoCommand(SubsystemList subsystems, int level) {

        switch (level) {
            case 1:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 90, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5)).raceWith(
                        new WaitCommand(3))
                );
                break;

            case 2:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 90, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5)).raceWith(
                        new WaitCommand(3)),
                    new ArmFullStopCommand(subsystems, armType.claw).alongWith(
                        new WristStopCommand(subsystems))
                );
                break;

            case 3:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 130, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 100, 0.5)).raceWith(
                        new WaitCommand(3))
                );
                break;

            case 4:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 157.5, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 137, 0.5)),
                    new ArmMoveCommand(subsystems, armType.claw, 132, 1.00).raceWith(
                        new ArmPivotHoldCommand(subsystems, armType.claw)).raceWith(
                        new WaitCommand(2))
                );
                break;
        
            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, armType.claw),
                    new WristStopCommand(subsystems)  
                );

        }

    }

}
