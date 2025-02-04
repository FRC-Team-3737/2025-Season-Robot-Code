package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmCheckCommand extends Command {

    final ClawArmSubsystem clawArm;

    public ClawArmCheckCommand(SubsystemList subsystems) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");

    }

    @Override
    public boolean isFinished() {

        return Math.abs(clawArm.GetCurrentExtension() - clawArm.GetDesiredExtension()) < 3;

    }
    
}
