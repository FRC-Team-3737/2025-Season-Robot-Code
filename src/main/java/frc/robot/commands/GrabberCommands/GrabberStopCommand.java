package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberSubsystem;

public class GrabberStopCommand extends Command{

    final GrabberSubsystem grabber;

    public GrabberStopCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

        addRequirements(grabber);

    }

    @Override
    public void initialize() {

        grabber.Stop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
