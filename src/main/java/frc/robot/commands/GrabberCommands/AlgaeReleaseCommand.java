package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.utils.SubsystemList;

public class AlgaeReleaseCommand extends Command {

    final GrabberSubsystem grabber;
    
    public AlgaeReleaseCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

    }

    public void initialize() {

        grabber.SetAlgaeState(false);

    }

    public boolean isFinished() {

        return false;

    }

}
