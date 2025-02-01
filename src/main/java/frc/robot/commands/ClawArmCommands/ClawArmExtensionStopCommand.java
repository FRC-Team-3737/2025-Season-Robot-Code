package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmExtensionStopCommand extends Command {

    final ClawArmSubsystem clawArm;

    public ClawArmExtensionStopCommand(SubsystemList subsystems) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.ExtensionStop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
