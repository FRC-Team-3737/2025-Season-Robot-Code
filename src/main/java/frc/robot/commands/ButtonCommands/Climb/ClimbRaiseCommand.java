package frc.robot.commands.ButtonCommands.Climb; 

import frc.robot.utils.SubsystemList;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ClimbRaiseCommand extends SequentialCommandGroup {
    public ClimbRaiseCommand(SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, 0.15, 10, 0.5).raceWith(new WaitCommand(0.5)),
            new ClimbRotateCommand(subsystems, 0.30, 10, 0.5).raceWith(new WaitCommand(2)),
            new ClimbRotateCommand(subsystems, 0.40, 10, 0.5),
            new ClimbStopCommand(subsystems)
        );
    }
}
