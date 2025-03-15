package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ArmPivotStopCommand extends Command {

    final ArmSubsystem arm;

    /**
     * Stops the arm's roatation.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     */
    public ArmPivotStopCommand(SubsystemList subsystems, subsystemType type) {

        arm = (ArmSubsystem) subsystems.getSubsystem(type.name());

        addRequirements(arm);

    }

    @Override
    public void initialize() {

        arm.PivotStop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
