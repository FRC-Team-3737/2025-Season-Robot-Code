package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeProcessorCommand extends SequentialCommandGroup {

    public AlgaeProcessorCommand(SubsystemList subsystems) {

        addCommands(
            new ArmPivotMoveCommand(subsystems, armType.grabber, 25, 3, 1, .5),
            new GrabberShootCommand(subsystems, .5).alongWith(
                new ArmPivotStopCommand(subsystems, armType.grabber)).raceWith(
                    new WaitCommand(2)),
            new GrabberStopCommand(subsystems).alongWith(
                new ArmPivotMoveCommand(subsystems, armType.grabber, 10, 3, 0, .5)),
            new ArmPivotStopCommand(subsystems, armType.grabber)
        );

    }

}
