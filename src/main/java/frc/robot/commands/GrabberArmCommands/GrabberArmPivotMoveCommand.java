package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmPivotMoveCommand extends Command {

    final GrabberArmSubsystem grabberArm;
    double desiredAngle;
    double desiredExtension;
    double extensionSpeed;
    double deadzone;

    public GrabberArmPivotMoveCommand(SubsystemList subsystems, double angle, double deadzone, double extension, double speed) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        desiredAngle = angle;
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.ActivatePivot();
        grabberArm.SetTolerance(deadzone);
        grabberArm.SetDesiredAngle(desiredAngle);
        grabberArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        grabberArm.Pivot();
        grabberArm.Move(extensionSpeed);

    }

    @Override
    public boolean isFinished() {

        return Math.abs(grabberArm.GetCurrentExtension() - grabberArm.GetDesiredExtension()) < 0.5 && grabberArm.GetIsReady();

    }

    @Override
    public void end(boolean interrupted) {

        grabberArm.PivotStop();
        grabberArm.ExtensionStop();

    }
    
}
