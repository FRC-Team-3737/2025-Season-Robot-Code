package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class ArmMoveCommand extends Command {

    final ArmSubsystem arm;
    double desiredExtension;
    double extensionSpeed;

    /**
     * Makes the arm extend/retract based on values inputted.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     * @param extension The extension setpoint in inches
     * @param speed The speed of the extension
     */
    public ArmMoveCommand(SubsystemList subsystems, armType type, double extension, double speed) {

        if (type == armType.claw) {
            arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");
        } else {
            arm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");
        }

        desiredExtension = extension;
        extensionSpeed = speed;

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.SetDesiredExtension(desiredExtension);

    }

    @Override
    public void execute() {

        arm.Move(extensionSpeed);

    }

    @Override
    public boolean isFinished() {

        return Math.abs(arm.GetCurrentExtension() - arm.GetDesiredExtension()) < 1;

    }

    @Override
    public void end(boolean interrupted) {

        arm.ExtensionStop();

    }
    
}
