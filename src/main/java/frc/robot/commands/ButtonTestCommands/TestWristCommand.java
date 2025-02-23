package frc.robot.commands.ButtonTestCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;

public class TestWristCommand extends SequentialCommandGroup {

    public TestWristCommand(SubsystemList subsystems, double angle, double tolerance) {

        addCommands(
            new WristPivotCommand(subsystems, angle, tolerance),
            new WristStopCommand(subsystems)
        );

    }

}
