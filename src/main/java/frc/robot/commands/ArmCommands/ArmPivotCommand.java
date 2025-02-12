package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmPivotCommand extends Command {

    final ArmSubsystem arm;
    double desiredAngle;
    double deadzone;

    /**
     * Makes the arm pivot through a PID.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     * @param angle The angle setpoint
     * @param tolerance The tolerance the pivot
     */
    public ArmPivotCommand(SubsystemList subsystems, armType type, double angle, double tolerance) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

        desiredAngle = angle;
        deadzone = tolerance;

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.ActivatePivot();
        arm.SetTolerance(deadzone);
        arm.SetDesiredAngle(desiredAngle);

    }

    @Override
    public void execute() {

        arm.Pivot();

    }

    @Override
    public boolean isFinished() {

        return arm.GetIsReady();

    }

    @Override
    public void end(boolean interrupted) {

        arm.PivotStop();

    }
    
}
