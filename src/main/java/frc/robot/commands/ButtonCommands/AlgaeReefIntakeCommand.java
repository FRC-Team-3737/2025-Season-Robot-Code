package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

import frc.robot.commands.GrabberCommands.AlgeaDetectionCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeReefIntakeCommand extends SequentialCommandGroup {

    public AlgaeReefIntakeCommand(SubsystemList subsystems, int level) {

        if (level == 1) {

            addCommands(
                
                new ArmPivotMoveCommand(subsystems, armType.grabber, 45, 1, .5, .3),
                new ArmPivotStopCommand(subsystems, armType.grabber),
                new GrabberIntakeCommand(subsystems, .5).raceWith(new AlgeaDetectionCommand(subsystems)),
                new GrabberStopCommand(subsystems).alongWith(new ArmPivotMoveCommand(subsystems, armType.grabber, 25, 1, 0, .3))

            );

        } else {

            addCommands(
                
                new ArmPivotMoveCommand(subsystems, armType.grabber, 90, 1, .5, .3),
                new ArmPivotStopCommand(subsystems, armType.grabber),
                new GrabberIntakeCommand(subsystems, .5).raceWith(new AlgeaDetectionCommand(subsystems)),
                new GrabberStopCommand(subsystems).alongWith(new ArmPivotMoveCommand(subsystems, armType.grabber, 25, 1, 0, .3))

            );

        }

    }

}
