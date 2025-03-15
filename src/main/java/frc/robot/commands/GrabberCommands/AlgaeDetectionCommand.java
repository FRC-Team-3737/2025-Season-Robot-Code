package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.SubsystemList;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.GrabberSubsystem;

public class AlgaeDetectionCommand extends Command {

    private final GrabberSubsystem grabber;

    public AlgaeDetectionCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem(subsystemType.GRABBER.name());

    }

    @Override
    public boolean isFinished() {
        return grabber.GetAlgeaIn();
    }

}
