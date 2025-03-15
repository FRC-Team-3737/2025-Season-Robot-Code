package frc.robot.commands.ButtonCommands.Claw;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClawArmSubsystem;
import frc.robot.Constants.subsystemType;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;

public class CoralLevelCommand extends SequentialCommandGroup {

    public CoralLevelCommand(SubsystemList subsystems, int reefLevel) {

        ClawArmSubsystem arm = (ClawArmSubsystem) subsystems.getSubsystem("clawArm");

        switch (reefLevel) {
            case 1:
                addCommands(
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1.00),
                    new ArmPivotCommand(subsystems, subsystemType.CLAW_ARM, 80, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 135, 0.5)),
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 40, 1.00).alongWith(
                        new ArmPivotHoldCommand(subsystems, subsystemType.CLAW_ARM))
                );
                break;

            case 2:
                addCommands(
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1.00),
                    new ArmPivotCommand(subsystems, subsystemType.CLAW_ARM, 77.5, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 12.5, 0.5)),
                    new ArmPivotHoldCommand(subsystems, subsystemType.CLAW_ARM)
                );
                break;

            case 3:
                addCommands(
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1.00),
                    new ArmPivotCommand(subsystems, subsystemType.CLAW_ARM, 132, 1).alongWith(
                        new WristPivotCommand(subsystems, 105, 0.5)),
                    new ArmPivotHoldCommand(subsystems, subsystemType.CLAW_ARM)
                );
                break;

            case 4:
                addCommands(
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1.00),
                    new ArmPivotCommand(subsystems, subsystemType.CLAW_ARM, 159, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 137, 0.5)),
                    new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 132, 1.00).alongWith(
                        new ArmPivotHoldCommand(subsystems, subsystemType.CLAW_ARM))
                );
                break;

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, subsystemType.CLAW_ARM)
                );
            
        }

    }

}
