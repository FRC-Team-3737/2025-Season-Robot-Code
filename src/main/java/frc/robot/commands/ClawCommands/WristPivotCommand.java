package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristPivotCommand extends Command {
    final ClawSubsystem claw;
    double desiredAngle;
    double deadzone;

    public WristPivotCommand(SubsystemList subsystems, double angle, double tolerance) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");
        deadzone = tolerance;
        desiredAngle = angle;

        addRequirements(claw);

    }

    @Override
    public void initialize() {

        claw.ActivateRotation();
        claw.SetDesiredAngle(desiredAngle);
        claw.SetTolerance(deadzone);

    }

    @Override
    public void execute() {

        claw.Pivot();

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
