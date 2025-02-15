package main.java.frc.robot.commands.ButtonCommands; // this fixes the error in my computer, idk why it needs to mbe more specified - raphael

import frc.robot.utils.SubsystemList;

import edu.wpilib.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ClimbCommands.ClimbRotateCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ClimbRaiseCommand {
    public ClimbRaiseCommand(SubsystemList subsystems) {
        addCommands (
            new ClimbRotateCommand(subsystems, 90,0.5).raceWith(new WaitCommand(1)),
            new ClimbStopCommand(subsystems)
        );
    }
}
