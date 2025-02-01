package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmCheckCommand extends Command {

    final ClawArmSubsystem clawArm;
    boolean directionalCheck;

    public ClawArmCheckCommand(SubsystemList subsystems) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");

    }

    @Override
    public void initialize() {

        if (clawArm.GetDesiredExtension() <= clawArm.GetCurrentExtension()) {
            directionalCheck = true;
        } else {
            directionalCheck = false;
        }

    }

    @Override
    public boolean isFinished() {

        if (directionalCheck) {
            return Math.abs(clawArm.GetCurrentExtension()) <= clawArm.GetDesiredExtension();
        } else {
            return Math.abs(clawArm.GetCurrentExtension()) >= clawArm.GetDesiredExtension();
        }

    }
    
}
