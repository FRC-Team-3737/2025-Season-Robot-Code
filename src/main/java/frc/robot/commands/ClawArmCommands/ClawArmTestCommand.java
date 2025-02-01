package frc.robot.commands.ClawArmCommands;

import frc.robot.utils.SubsystemList;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.ClawArmCommands.ClawArmExtendCommand;
import frc.robot.commands.ClawArmCommands.ClawArmRetractCommand;
import frc.robot.commands.ClawArmCommands.ClawArmExtensionStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotStopCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotExtendCommand;
import frc.robot.commands.ClawArmCommands.ClawArmPivotRetractCommand;
import frc.robot.commands.ClawArmCommands.ClawArmFullStopCommand;

public class ClawArmTestCommand extends SequentialCommandGroup {

    public ClawArmTestCommand(SubsystemList subsystems) {

        addCommands(
            new ClawArmExtendCommand(subsystems, 100, 0.5).raceWith(new ClawArmCheckCommand(subsystems)),
            new ClawArmExtensionStopCommand(subsystems),
            new ClawArmRetractCommand(subsystems, 0, 0.5).raceWith(new ClawArmCheckCommand(subsystems)),
            new ClawArmExtensionStopCommand(subsystems)
        );

    }
    
}
