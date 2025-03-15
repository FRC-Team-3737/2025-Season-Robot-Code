package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.utils.SubsystemList;

public class ArmPivotHoldCommand extends Command {

    final ArmSubsystem arm;

    /**
     * Makes the arm pivot through a PID.
     * 
     * @param subsystems The SubsystemList
     * @param type The armType
     */
    public ArmPivotHoldCommand(SubsystemList subsystems, subsystemType type) {

        arm = (ArmSubsystem) subsystems.getSubsystem(type.name());

    }

    @Override
    public void initialize() {

        arm.ActivatePivot();

    }

    @Override
    public void execute() {

        arm.Hold();

    }

    @Override
    public boolean isFinished() {

        return true;

    }

}
