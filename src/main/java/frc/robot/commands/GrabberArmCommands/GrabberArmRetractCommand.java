package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmRetractCommand extends Command {

    final GrabberArmSubsystem grabberArm;
    double desiredExtension;
    double extensionSpeed;

    public GrabberArmRetractCommand(SubsystemList subsystems, double extension, double speed) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        grabberArm.Retract(extensionSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        grabberArm.ExtensionStop();

    }
    
}
