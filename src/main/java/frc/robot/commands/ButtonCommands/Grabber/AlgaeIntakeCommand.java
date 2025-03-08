package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.GrabberCommands.AlgaeDetectionCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.GrabberCommands.ServoLockCommand;
import frc.robot.commands.GrabberCommands.ServoUnlockCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeIntakeCommand extends SequentialCommandGroup {

    public AlgaeIntakeCommand(SubsystemList subsystems, String algaeLevel) {

        switch (algaeLevel.toLowerCase()) {

            case "lower":
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 96, 1.0),
                    new GrabberIntakeCommand(subsystems, 0.25).raceWith(
                        new AlgaeDetectionCommand(subsystems)),
                    new ServoLockCommand(subsystems).alongWith(
                        new WaitCommand(0.25)),
                    new GrabberStopCommand(subsystems).alongWith(
                        new ServoUnlockCommand(subsystems))
                );
                break;

            case "upper":
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 131, 1.0),
                    new GrabberIntakeCommand(subsystems, 0.25).raceWith(
                        new AlgaeDetectionCommand(subsystems)),
                    new ServoLockCommand(subsystems).alongWith(
                        new WaitCommand(0.25)),
                    new GrabberStopCommand(subsystems).alongWith(
                        new ServoUnlockCommand(subsystems))
                );
                break;

            case "floor":
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 55, 1.0),
                    new GrabberIntakeCommand(subsystems, 0.25).raceWith(
                        new AlgaeDetectionCommand(subsystems)),
                    new ServoLockCommand(subsystems).alongWith(
                        new WaitCommand(0.25)),
                    new GrabberStopCommand(subsystems).alongWith(
                        new ServoUnlockCommand(subsystems))
                );
                break;

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, armType.grabber)
                );

        }

    }

    private Command ServoUnlockCommand(SubsystemList subsystems) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ServoUnlockCommand'");
    }

}
