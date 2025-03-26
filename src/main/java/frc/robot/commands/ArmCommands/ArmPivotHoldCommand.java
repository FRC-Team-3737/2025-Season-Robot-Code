package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmPivotHoldCommand extends Command {

    final ArmSubsystem arm;

    /**
     * Makes the arm pivot through a PID.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     */
    public ArmPivotHoldCommand(SubsystemList subsystems, armType type) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

    }

    @Override
    public void initialize() {

        arm.ActivatePivot();

    }

    @Override
    public void execute() {

        arm.PivotHold();

    }

    @Override
    public boolean isFinished() {

        return Math.abs(arm.GetCurrentExtension() - arm.GetDesiredExtension()) < 0.5 && arm.GetIsReady();

    }

}
