package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ArmFullStopCommand extends Command {

    final ArmSubsystem arm;

    /**
     * Stops the arm's rotation and extension.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     */
    public ArmFullStopCommand(SubsystemList subsystems, subsystemType type) {

        arm = (ArmSubsystem) subsystems.getSubsystem(type.name());

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.PivotStop();
        arm.ExtensionStop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
