package frc.robot.commands.ButtonCommands.Claw;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class CoralFeederIntakeCommand extends SequentialCommandGroup {

    public CoralFeederIntakeCommand(SubsystemList subsystems) {

        addCommands(
            new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
            new ArmPivotCommand(subsystems, armType.claw, 51, 1).alongWith(
                new WristPivotCommand(subsystems, 105, 0.5)),
            new ArmMoveCommand(subsystems, armType.claw, 17, 0.5).alongWith(
                new ArmPivotHoldCommand(subsystems, armType.claw)),
            new ArmMoveCommand(subsystems, armType.claw, 0, 0.5),
            new ArmFullStopCommand(subsystems, armType.claw)
        );

    }
}
