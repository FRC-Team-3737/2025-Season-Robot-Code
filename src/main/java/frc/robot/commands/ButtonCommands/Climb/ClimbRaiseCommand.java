package frc.robot.commands.ButtonCommands.Climb; 

import frc.robot.utils.SubsystemList;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ClimbRaiseCommand extends SequentialCommandGroup {
    public ClimbRaiseCommand(SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, 15, 90, 0.5).raceWith(new WaitCommand(1)),
            new ClimbStopCommand(subsystems)
        );
    }
}
