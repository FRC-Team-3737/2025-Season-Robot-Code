package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristPivotCommand extends Command {
    final ClawSubsystem claw;
    private final double motorSpeed;

    public WristPivotCommand(SubsystemList subsystems, double speed) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");
        motorSpeed = speed;

        addRequirements(claw);

    }

    @Override
    public void initialize() {

        claw.ActivateRotation();
        claw.PivotToTarget(motorSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        claw.WristStop();

    }
}
