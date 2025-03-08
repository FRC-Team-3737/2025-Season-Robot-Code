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
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5)).raceWith(
                        new WaitCommand(3)),
                    new ArmPivotHoldCommand(subsystems, armType.claw)
                );
                break;

            case 2:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5)).raceWith(
                        new WaitCommand(3)),
                    new ArmFullStopCommand(subsystems, armType.claw).alongWith(
                        new WristStopCommand(subsystems)),
                    new ArmPivotHoldCommand(subsystems, armType.claw)
                );
                break;

            case 3:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 130, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 100, 0.5)).raceWith(
                        new WaitCommand(3)),
                    new ArmPivotHoldCommand(subsystems, armType.claw)
                );
                break;

            case 4:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5)).raceWith(
                        new WaitCommand(3)),
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00)
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
