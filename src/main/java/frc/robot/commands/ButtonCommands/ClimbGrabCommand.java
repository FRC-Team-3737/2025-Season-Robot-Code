package main.java.frc.robot.commands.ButtonCommands; // this fixes the error in my computer, idk why it needs to mbe more specified - raphael

import edu.wpilib.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.utils.SubsystemList;

public class ClimbGrabCommand {

    public ClimbGrabCommand (SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, 0,0.5).raceWith(new WaitCommand(1)),
            new ClimbStopCommand(subsystems)
        );
    }
}
