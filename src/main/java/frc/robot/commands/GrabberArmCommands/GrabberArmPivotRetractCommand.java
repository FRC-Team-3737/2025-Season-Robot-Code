package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmPivotRetractCommand extends Command {

    final GrabberArmSubsystem grabberArm;
    double desiredAngle;
    double desiredExtension;
    double extensionSpeed;

    public GrabberArmPivotRetractCommand(SubsystemList subsystems, double angle, double extension, double speed) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        desiredAngle = angle;
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.ActivatePivot();
        grabberArm.SetDesiredAngle(desiredAngle);
        grabberArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        grabberArm.Pivot();
        grabberArm.Retract(extensionSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        grabberArm.PivotStop();
        grabberArm.ExtensionStop();

    }
    
}
