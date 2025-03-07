package frc.robot.commands.ButtonCommands.Claw;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class PlaceCoralCommand extends SequentialCommandGroup {

    public PlaceCoralCommand(SubsystemList subsystems) {

        addCommands(
            new ClawOpenCommand(subsystems, .5).raceWith(new WaitCommand(1)),
            new ClawStopCommand(subsystems)
        );

    }

}
