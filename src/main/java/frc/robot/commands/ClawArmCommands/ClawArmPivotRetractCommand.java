package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmPivotRetractCommand extends Command {

    final ClawArmSubsystem clawArm;
    double desiredAngle;
    double desiredExtension;
    double extensionSpeed;

    public ClawArmPivotRetractCommand(SubsystemList subsystems, double angle, double extension, double speed) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        desiredAngle = angle;
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.ActivatePivot();
        clawArm.SetDesiredAngle(desiredAngle);
        clawArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        clawArm.Pivot();
        clawArm.Retract(extensionSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        clawArm.PivotStop();
        clawArm.ExtensionStop();

    }
    
}
