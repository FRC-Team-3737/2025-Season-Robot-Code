package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClawSubsystem;

public class ClawRetractCommand extends Command {
    final ClawSubsystem clawSubsystem;
    private final double motorSpeed;

    public ClawRetractCommand(SubsystemList subsystems, double speed) {

        clawSubsystem = (ClawSubsystem) subsystems.getSubsystem("claw");
        motorSpeed = speed;

        addRequirements(clawSubsystem);

    }

    @Override
    public void initialize() {

        clawSubsystem.ClawSpinNegative(motorSpeed);

    }

    @Override public void end(boolean interrupted) {

        clawSubsystem.ClawStop();

    }
}