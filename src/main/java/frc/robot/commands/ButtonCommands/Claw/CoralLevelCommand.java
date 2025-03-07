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
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5))
                );
                break;

            case 2:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5))
                );
                break;

            case 3:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 130, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 100, 0.5)),
                    new ArmPivotHoldCommand(subsystems, armType.claw)
                );
                break;

            case 4:
                addCommands(
                    new ArmMoveCommand(subsystems, armType.claw, 0, 1.00),
                    new ArmPivotCommand(subsystems, armType.claw, 0, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 90, 0.5))
                );
                break;
        
            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, armType.claw)  
                );
            
        }

    }

}
