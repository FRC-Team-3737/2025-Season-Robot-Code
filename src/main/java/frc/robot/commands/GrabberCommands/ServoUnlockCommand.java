package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberSubsystem;

public class ServoUnlockCommand extends Command {
    
    final GrabberSubsystem grabber;

    public ServoUnlockCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");
        
    }

    @Override
    public void initialize() {

        grabber.ServoUnlock();

    }

    @Override
    public boolean isFinished() {

        return true;

    }

}
