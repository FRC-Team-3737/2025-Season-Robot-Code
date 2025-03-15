package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbStopCommand extends Command {

    final ClimbSubsystem climb;

    public ClimbStopCommand(SubsystemList subsystems) {

        climb = (ClimbSubsystem) subsystems.getSubsystem(subsystemType.CLIMB.name());

        addRequirements(climb);

    }

    @Override
    public void initialize() {

        climb.Stop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }

}
