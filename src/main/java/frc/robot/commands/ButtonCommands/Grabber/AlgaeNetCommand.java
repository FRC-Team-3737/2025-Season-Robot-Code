package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;

public class AlgaeNetCommand extends SequentialCommandGroup {

    public AlgaeNetCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmPivotCommand(subsystems, armType.grabber, 170, 1),
            new ArmPivotHoldCommand(subsystems, armType.grabber)

        );

    }

}
