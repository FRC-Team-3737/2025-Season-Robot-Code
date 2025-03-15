package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.Constants.subsystemType;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;

public class AlgaeNetCommand extends SequentialCommandGroup {

    public AlgaeNetCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmPivotCommand(subsystems, subsystemType.GRABBER_ARM, 170, 1),
            new ArmPivotHoldCommand(subsystems, subsystemType.GRABBER_ARM)

        );

    }

}
