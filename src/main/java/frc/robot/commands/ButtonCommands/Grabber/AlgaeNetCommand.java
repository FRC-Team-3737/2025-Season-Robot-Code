package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;

public class AlgaeNetCommand extends SequentialCommandGroup {

    public AlgaeNetCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmMoveCommand(subsystems, armType.grabber, 0, 1.00),
            new ArmPivotCommand(subsystems, armType.grabber, 177, 1),
            new ArmMoveCommand(subsystems, armType.grabber, 55, 1.00).raceWith(
                new ArmPivotHoldCommand(subsystems, armType.grabber))

        );

    }

}
