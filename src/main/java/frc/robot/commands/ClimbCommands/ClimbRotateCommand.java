package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbRotateCommand extends Command {

    final ClimbSubsystem climbSubsystem;
    private final double motorspeed;
    
    public ClimbRotateCommand(SubsystemList subsystems, double speed) {

        climbSubsystem = (ClimbSubsystem) subsystems.getSubsystem("climb");
        motorspeed = speed;

        addRequirements(climbSubsystem);

    }

    @Override
    public void initialize() {
        
        climbSubsystem.ActivateRotation();

    }

    @Override
    public void execute() {

        climbSubsystem.Pivot(motorspeed);

    }

    @Override
    public void end(boolean interrupted) {

        climbSubsystem.Stop();

    }

}
