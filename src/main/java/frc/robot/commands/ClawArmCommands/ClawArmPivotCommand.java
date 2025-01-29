package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmPivotCommand extends Command {

    final ClawArmSubsystem clawArm;
    double desiredAngle;

    public ClawArmPivotCommand(SubsystemList subsystems, double angle) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        desiredAngle = angle;

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.ActivatePivot();
        clawArm.SetDesiredAngle(desiredAngle);

    }

    @Override
    public void execute() {

        clawArm.Pivot();

    }

    @Override
    public void end(boolean interrupted) {

        clawArm.PivotStop();

    }
    
}
