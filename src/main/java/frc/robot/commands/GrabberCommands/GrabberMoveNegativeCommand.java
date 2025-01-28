package frc.robot.commands.GrabberCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberSubsystem;

public class GrabberMoveNegativeCommand extends Command{
    
    final GrabberSubsystem grabber;
    private final double motorSpeed;

    public GrabberMoveNegativeCommand(SubsystemList subsystems, double speed) {

        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");
        motorSpeed = speed;

        addRequirements(grabber);

    }

    @Override
    public void initialize() {

        grabber.SetSpeed(motorSpeed);
        grabber.PositiveMove();

    }

    @Override
    public void end(boolean interrupted) {

        grabber.Stop();

    }
    
}
