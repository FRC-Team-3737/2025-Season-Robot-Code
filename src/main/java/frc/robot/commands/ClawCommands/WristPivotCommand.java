package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristPivotCommand extends Command {
    final ClawSubsystem claw;
    private final double motorSpeed;
    double desiredAngle;
    double deadzone;

    public WristPivotCommand(SubsystemList subsystems, double speed, double angle, double tolerance) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");
        motorSpeed = speed;
        deadzone = tolerance;
        desiredAngle = angle;

        addRequirements(claw);

    }

    @Override
    public void initialize() {

        claw.ActivateRotation();
        claw.SetDesiredAngle(desiredAngle);
        claw.SetTolerance(deadzone);
        claw.Pivot(motorSpeed);

    }

    @Override
    public boolean isFinished() {

        return claw.GetIsReady();

    }

    @Override
    public void end(boolean interrupted) {

        claw.WristStop();

    }
}
