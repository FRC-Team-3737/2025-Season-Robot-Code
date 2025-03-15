package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.GrabberSubsystem;

public class GrabberShootCommand extends Command{
    
    final GrabberSubsystem grabber;
    private final double motorSpeed;

    public GrabberShootCommand(SubsystemList subsystems, double speed) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem(subsystemType.GRABBER.name());
        motorSpeed = speed;

        addRequirements(grabber);

    }

    @Override
    public void initialize() {

        grabber.Shoot(motorSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        grabber.Stop();

    }
    
}
