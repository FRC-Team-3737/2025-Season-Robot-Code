package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeShootCommand extends SequentialCommandGroup {

    public AlgaeShootCommand(SubsystemList subsystems, double speed) {

        addCommands(
            new GrabberIntakeCommand(subsystems, .25).raceWith(
                    new WaitCommand(.25)),
            new GrabberStopCommand(subsystems).alongWith(
                new ArmPivotHoldCommand(subsystems, armType.grabber)),
            new GrabberShootCommand(subsystems, speed).raceWith(
                new WaitCommand(.5)),
            new GrabberStopCommand(subsystems)
        );

    }

}
