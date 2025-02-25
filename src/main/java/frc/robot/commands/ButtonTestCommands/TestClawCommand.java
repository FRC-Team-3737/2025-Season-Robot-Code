package frc.robot.commands.ButtonTestCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawCloseCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class TestClawCommand extends SequentialCommandGroup {

    public TestClawCommand(SubsystemList subsystems, double speed) {

        addCommands(
            new ClawOpenCommand(subsystems, speed), // 0.3
            new ClawStopCommand(subsystems),
            new WaitCommand(3),
            new ClawCloseCommand(subsystems, speed) // 0.03
        );

    }

}
