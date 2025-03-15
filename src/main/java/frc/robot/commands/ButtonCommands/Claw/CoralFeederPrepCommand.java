package frc.robot.commands.ButtonCommands.Claw;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.subsystemType;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.utils.SubsystemList;

public class CoralFeederPrepCommand extends SequentialCommandGroup {

    public CoralFeederPrepCommand(SubsystemList subsystems) {

        addCommands(
            new ArmMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1.0).alongWith(
                new WristPivotCommand(subsystems, 90, 0.5)),
            new ArmPivotCommand(subsystems, subsystemType.CLAW_ARM, 140, 1),
            new ArmPivotHoldCommand(subsystems, subsystemType.CLAW_ARM)
        );

    }
    
}
