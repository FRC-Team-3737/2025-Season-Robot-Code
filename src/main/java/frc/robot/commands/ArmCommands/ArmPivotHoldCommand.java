package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmPivotHoldCommand extends Command {

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
    public ArmPivotHoldCommand(SubsystemList subsystems, armType type) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

    }

    @Override
    public void execute() {

        arm.Pivot();

    }

    @Override
    public void end(boolean interrupted) {

        arm.PivotStop();

    }

}
