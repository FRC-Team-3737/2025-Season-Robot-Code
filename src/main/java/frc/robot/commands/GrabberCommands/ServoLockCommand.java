package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.SubsystemList;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.GrabberSubsystem;

public class ServoLockCommand extends Command {
    
    final GrabberSubsystem grabber;

    public ServoLockCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem(subsystemType.GRABBER.name());
        
    }

    @Override
    public void initialize() {

        grabber.ServoLock();

    }

    @Override
    public boolean isFinished() {

        return true;

    }

}
