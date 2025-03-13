package frc.robot.commands.ButtonCommands.Climb; 

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.utils.SubsystemList;

public class ClimbGrabCommand extends SequentialCommandGroup {

    public ClimbGrabCommand (SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, 0.15, -30, 0.5),
            new ClimbStopCommand(subsystems)
        );
    }
}
