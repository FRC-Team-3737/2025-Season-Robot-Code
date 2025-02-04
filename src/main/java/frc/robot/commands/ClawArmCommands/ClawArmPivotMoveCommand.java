package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmPivotMoveCommand extends Command {

    final ClawArmSubsystem clawArm;
    double desiredAngle;
    double desiredExtension;
    double extensionSpeed;
    double deadzone;

    public ClawArmPivotMoveCommand(SubsystemList subsystems, double angle, double deadzone, double extension, double speed) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        desiredAngle = angle;
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.ActivatePivot();
        clawArm.SetTolerance(deadzone);
        clawArm.SetDesiredAngle(desiredAngle);
        clawArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        clawArm.Pivot();
        clawArm.Move(extensionSpeed);

    }

    @Override
    public boolean isFinished() {

        return Math.abs(clawArm.GetCurrentExtension() - clawArm.GetDesiredExtension()) < 0.5 && clawArm.GetIsReady();

    }

    @Override
    public void end(boolean interrupted) {

        clawArm.PivotStop();
        clawArm.ExtensionStop();

    }
    
}
