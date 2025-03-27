package frc.robot.commands.ButtonCommands.Claw;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class CoralFeederPrepCommand extends SequentialCommandGroup {

    public CoralFeederPrepCommand(SubsystemList subsystems) {

        addCommands(
            new ArmMoveCommand(subsystems, armType.claw, 0, 1.0),
            new ArmPivotCommand(subsystems, armType.claw, 140, 1).alongWith(
                new WristPivotCommand(subsystems, 90, 0.5)),
            new ArmPivotHoldCommand(subsystems, armType.claw)
        );

    }
    
}
