package frc.robot.commands.ClawArmCommands;

import frc.robot.utils.SubsystemList;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ClawArmCommands.ClawArmMoveCommand;
import frc.robot.commands.ClawArmCommands.ClawArmExtensionStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotMoveCommand;
import frc.robot.commands.ClawArmCommands.ClawArmFullStopCommand;

public class ClawArmTestCommand extends SequentialCommandGroup {

    public ClawArmTestCommand(SubsystemList subsystems) {

        addCommands(
            new ClawArmMoveCommand(subsystems, 100, 0.5),
            new ClawArmExtensionStopCommand(subsystems).alongWith(new WaitCommand(3)),
            new ClawArmMoveCommand(subsystems, 0, 0.5),
            new ClawArmExtensionStopCommand(subsystems).alongWith(new WaitCommand(3)),
            new ClawArmPivotCommand(subsystems, 180, 1),
            new ClawArmPivotStopCommand(subsystems).alongWith(new WaitCommand(3)),
            new ClawArmPivotMoveCommand(subsystems, 0, 1, 100, 0.25),
            new ClawArmFullStopCommand(subsystems)
        );

    }
    
}
