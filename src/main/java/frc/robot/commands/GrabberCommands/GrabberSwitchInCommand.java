package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberSubsystem;

public class GrabberSwitchInCommand extends Command {
    
    final GrabberSubsystem grabber;

    public GrabberSwitchInCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

        addRequirements(grabber);
        
    }

    @Override
    public boolean isFinished() {

        return grabber.SwitchObjectIn();

    }

}
