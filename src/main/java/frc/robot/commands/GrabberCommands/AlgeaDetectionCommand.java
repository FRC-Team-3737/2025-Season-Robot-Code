package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberSubsystem;

public class AlgeaDetectionCommand extends Command {
    
    final GrabberSubsystem grabber;

    public AlgeaDetectionCommand(SubsystemList subsystems) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");
        
    }

    @Override
    public boolean isFinished() {

        return grabber.GetAlgeaIn();

    }

}
