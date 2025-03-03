package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmPivotMoveCommand extends Command {

    final ArmSubsystem arm;
    double desiredAngle;
    double desiredExtension;
    double extensionSpeed;
    double deadzone;

    /**
     * Roatates and extends/retracts the arm based on all inputted variables.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     * @param angle The angle setpoint of the arm
     * @param tolerance The angle tolerance of the arm
     * @param extension The extension setpoint of the arm
     * @param speed The extension speed of the arm
     */
    public ArmPivotMoveCommand(SubsystemList subsystems, armType type, double angle, double tolerance, double extension, double speed) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

        desiredAngle = angle;
        desiredExtension = extension;
        extensionSpeed = speed;
        deadzone = tolerance;

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.ActivatePivot();
        arm.SetTolerance(deadzone);
        arm.SetDesiredAngle(desiredAngle);
        arm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        arm.Pivot();
        arm.Move(extensionSpeed);

    }

    @Override
    public boolean isFinished() {

        return Math.abs(arm.GetCurrentExtension() - arm.GetDesiredExtension()) < 0.5;

    }

    @Override
    public void end(boolean interrupted) {

        arm.ExtensionStop();

    }
    
}
