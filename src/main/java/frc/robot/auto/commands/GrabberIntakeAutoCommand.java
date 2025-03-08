package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.GrabberCommands.ServoLockCommand;
import frc.robot.commands.GrabberCommands.ServoUnlockCommand;
import frc.robot.commands.GrabberCommands.AlgaeDetectionCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

public class GrabberIntakeAutoCommand extends SequentialCommandGroup {

    public GrabberIntakeAutoCommand(SubsystemList subsystems, int level) {


        switch (level) {
            case 1:
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 96, 1).raceWith(
                        new WaitCommand(3)),
                    new GrabberIntakeCommand(subsystems, .25).raceWith(
                        new AlgaeDetectionCommand(subsystems).raceWith(
                            new WaitCommand(3))),
                    new ServoLockCommand(subsystems).alongWith(
                        new WaitCommand(.25)),
                    new GrabberStopCommand(subsystems).alongWith(
                        new ServoUnlockCommand(subsystems).alongWith(
                            new ArmPivotCommand(subsystems, armType.grabber, 0, 1)))
                );
                break;
            
            case 2:
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 131, 1).raceWith(
                        new WaitCommand(3)),
                    new GrabberIntakeCommand(subsystems, .25).raceWith(
                        new AlgaeDetectionCommand(subsystems).raceWith(
                            new WaitCommand(3))),
                    new ServoLockCommand(subsystems).alongWith(
                        new WaitCommand(.25)),
                    new GrabberStopCommand(subsystems).alongWith(
                        new ServoUnlockCommand(subsystems).alongWith(
                            new ArmPivotCommand(subsystems, armType.grabber, 0, 1)))
                );
                break;
        
            default:
                addCommands(
                    new GrabberStopCommand(subsystems)
                );
                break;
        }

    }

}
