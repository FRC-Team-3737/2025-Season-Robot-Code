package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClawSubsystem;

public class ClawStopCommand extends Command {
    final ClawSubsystem claw;

    public ClawStopCommand(SubsystemList subsystems) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");

        addRequirements(claw);
    }

    @Override
    public void initialize() {

        claw.ClawStop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }
}
