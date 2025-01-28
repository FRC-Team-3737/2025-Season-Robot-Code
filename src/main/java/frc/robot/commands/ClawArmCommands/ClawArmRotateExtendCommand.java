package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmRotateExtendCommand extends Command {

    final ClawArmSubsystem clawArm;

    public ClawArmRotateExtendCommand(SubsystemList subsystems) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.ActivatePivot();
        clawArm.Pivot();

    }

    @Override
    public void end(boolean interrupted) {

        clawArm.PivotStop();
        clawArm.ExtensionStop();

    }
    
}
