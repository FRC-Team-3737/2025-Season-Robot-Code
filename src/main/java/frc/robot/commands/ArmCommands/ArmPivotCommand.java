package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ArmSubsystem;
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
    public ArmPivotCommand(SubsystemList subsystems, subsystemType type, double angle, double tolerance) {

        arm = (ArmSubsystem) subsystems.getSubsystem(type.name());

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
