package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristPivotCommand extends Command {
    final ClawSubsystem clawSubsystem;
    private final double motorSpeed;

    public WristPivotCommand(SubsystemList subsystems, double speed) {

        clawSubsystem = (ClawSubsystem) subsystems.getSubsystem("claw");
        motorSpeed = speed;

        addRequirements(clawSubsystem);

    }

    @Override
    public void initialize() {

        clawSubsystem.PivotToTarget(motorSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        clawSubsystem.WristStop();

    }
}
