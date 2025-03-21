package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class CoralGrabbed extends Command {

    final ClawSubsystem claw;

    public CoralGrabbed(SubsystemList subsystems) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");

    }

    @Override
    public void initialize() {

        claw.CoralGrabbed();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
