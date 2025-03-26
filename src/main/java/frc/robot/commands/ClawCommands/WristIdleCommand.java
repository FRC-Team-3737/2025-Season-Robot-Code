package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristIdleCommand extends Command {

    final ClawSubsystem claw;

    public WristIdleCommand(SubsystemList subsystems) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");

        addRequirements(claw);

    }

    @Override
    public void execute() {

        claw.Hold();

    }

    @Override
    public boolean isFinished() {

        return false;

    }
    
}
