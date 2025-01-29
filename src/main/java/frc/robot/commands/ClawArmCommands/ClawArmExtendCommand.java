package frc.robot.commands.ClawArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ClawArmExtendCommand extends Command {

    final ClawArmSubsystem clawArm;
    double desiredExtension;
    double extensionSpeed;

    public ClawArmExtendCommand(SubsystemList subsystems, double extension, double speed) {

        clawArm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(clawArm);

    }

    @Override
    public void initialize() {

        clawArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        clawArm.Extend(extensionSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        clawArm.ExtensionStop();

    }
    
}
