package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmExtensionStopCommand extends Command {

    final ArmSubsystem arm;

    /**
     * Stops the arm's extension.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     */
    public ArmExtensionStopCommand(SubsystemList subsystems, armType type) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.ExtensionStop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
