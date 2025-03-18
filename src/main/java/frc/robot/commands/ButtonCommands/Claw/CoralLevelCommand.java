package frc.robot.commands.ButtonCommands.Claw;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;

public class CoralLevelCommand extends SequentialCommandGroup {

    public CoralLevelCommand(SubsystemList subsystems, int reefLevel) {

        switch (reefLevel) {
            case 1:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 80, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 135, 0.5)),
                    new ArmMoveCommand(subsystems, armType.claw, 40, 1.00).alongWith(
                        new ArmPivotHoldCommand(subsystems, armType.claw))
                );
                break;

            case 2:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 77.5, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 12.5, 0.5)),
                    new ArmPivotHoldCommand(subsystems, armType.claw)
                );
                break;

            case 3:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 132, 1).alongWith(
                        new WristPivotCommand(subsystems, 105, 0.5)),
                    new ArmMoveCommand(subsystems, armType.claw, 5, 1.00).alongWith(
                        new ArmPivotHoldCommand(subsystems, armType.claw))
                );
                break;

            case 4:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 159, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 137, 0.5)),
                    new ArmMoveCommand(subsystems, armType.claw, 132, 1.00).alongWith(
                        new ArmPivotHoldCommand(subsystems, armType.claw))
                );
                break;

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, armType.claw)
                );
            
        }

    }

}
