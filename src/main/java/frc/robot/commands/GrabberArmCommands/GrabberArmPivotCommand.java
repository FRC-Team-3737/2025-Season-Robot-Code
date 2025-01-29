package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmPivotCommand extends Command {

    final GrabberArmSubsystem grabberArm;
    double desiredAngle;

    public GrabberArmPivotCommand(SubsystemList subsystems, double angle) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        desiredAngle = angle;

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.ActivatePivot();
        grabberArm.SetDesiredAngle(desiredAngle);

    }

    @Override
    public void execute() {

        grabberArm.Pivot();

    }

    @Override
    public void end(boolean interrupted) {

        grabberArm.PivotStop();

    }
    
}
