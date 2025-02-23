package frc.robot.commands.ButtonCommands; 

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.utils.SubsystemList;

public class ClimbGrabCommand extends SequentialCommandGroup {

    public ClimbGrabCommand (SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, -93,0.5).raceWith(new WaitCommand(1)),
            new ClimbStopCommand(subsystems)
        );
    }
}
