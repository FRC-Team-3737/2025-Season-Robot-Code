package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;

public class WristRightPivotCommand extends SequentialCommandGroup {

    public WristRightPivotCommand(SubsystemList subsystems) {

        addCommands(
            new WristPivotCommand(subsystems, 90, 1),
            new WristStopCommand(subsystems)
        );

    }

}
