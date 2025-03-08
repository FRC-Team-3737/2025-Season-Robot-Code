package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

public class CoralFeederIntakePrepAutoCommand extends SequentialCommandGroup {

    public CoralFeederIntakePrepAutoCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmPivotCommand(subsystems, armType.claw, 135, 1),
            new ArmPivotHoldCommand(subsystems, armType.claw)

        );

    }

}
