package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbStopCommand extends Command {

    final ClimbSubsystem climbSubsystem;

    public ClimbStopCommand(SubsystemList subsystems) {

        climbSubsystem = (ClimbSubsystem) subsystems.getSubsystem("climb");

        addRequirements(climbSubsystem);

    }

    @Override
    public void initialize() {

        climbSubsystem.Stop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }

}
