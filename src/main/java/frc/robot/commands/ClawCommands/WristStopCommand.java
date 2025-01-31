package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristStopCommand extends Command {

    final ClawSubsystem clawSubsystem;

    public WristStopCommand(SubsystemList subsystems) {

        clawSubsystem = (ClawSubsystem) subsystems.getSubsystem("claw");

        addRequirements(clawSubsystem);

    }

    @Override
    public void initialize() {

        clawSubsystem.WristStop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }
}
