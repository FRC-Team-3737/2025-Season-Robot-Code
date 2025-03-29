package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.auto.commands.ClawOpenAutoCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;

public class CoralFeederIntakeAutoCommand extends SequentialCommandGroup {

    public CoralFeederIntakeAutoCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmMoveCommand(subsystems, armType.claw, 0, 1),
            new ArmPivotCommand(subsystems, armType.claw, 51, 1).alongWith(
                new WristPivotCommand(subsystems, 105, 0.5)),
            new ArmMoveCommand(subsystems, armType.claw, 10, 0.5).raceWith(
                new ArmPivotHoldCommand(subsystems, armType.claw)),
            new ArmMoveCommand(subsystems, armType.claw, 0, 0.5),
            new ArmFullStopCommand(subsystems, null)

        );

    }

}
