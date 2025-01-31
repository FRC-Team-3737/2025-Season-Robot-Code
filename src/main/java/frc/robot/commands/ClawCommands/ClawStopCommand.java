package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClawSubsystem;

public class ClawStopCommand extends Command {
    final ClawSubsystem clawSubsystem;

    public ClawStopCommand(SubsystemList subsystems) {

        clawSubsystem = (ClawSubsystem) subsystems.getSubsystem("claw");

        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {

        clawSubsystem.ClawStop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }
}
