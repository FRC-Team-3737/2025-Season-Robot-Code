package frc.robot.commands.ArmCommands;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ArmSubsystem.armType;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmExtensionStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;

@SuppressWarnings("unused")
public class ClawArmTestCommand extends SequentialCommandGroup {

    public ClawArmTestCommand(SubsystemList subsystems) {

        addCommands(
            new ArmMoveCommand(subsystems, armType.claw, 100, 0.5),
            new ArmExtensionStopCommand(subsystems, armType.claw).alongWith(new WaitCommand(3)),
            new ArmMoveCommand(subsystems, armType.claw, 0, 0.5),
            new ArmExtensionStopCommand(subsystems, armType.claw).alongWith(new WaitCommand(3)),
            new ArmPivotCommand(subsystems, armType.claw, 180, 1),
            new ArmPivotStopCommand(subsystems, armType.claw).alongWith(new WaitCommand(3)),
            new ArmPivotMoveCommand(subsystems, armType.claw, 0, 1, 100, 0.25),
            new ArmFullStopCommand(subsystems, armType.claw)
        );

    }
    
}
