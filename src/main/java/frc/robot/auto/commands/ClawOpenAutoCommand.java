package frc.robot.auto.commands;

import frc.robot.utils.SubsystemList;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClawCommands.ClawCloseCommand;
import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;

import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ClawOpenAutoCommand extends SequentialCommandGroup {

    public ClawOpenAutoCommand(SubsystemList subsystems) {

        addCommands(
            
            new ClawOpenCommand(subsystems, .30),
            new ClawStopCommand(subsystems).alongWith(
                new WaitCommand(0.25)),
            new ClawCloseCommand(subsystems, 0.06),
            new ClawStopCommand(subsystems)

        );

    }

}
