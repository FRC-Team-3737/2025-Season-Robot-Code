package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.SubsystemList;

import frc.robot.commands.DriveCommands.AutoMoveCommand;

public class LeaveAutoRoutine extends SequentialCommandGroup {

    public LeaveAutoRoutine(SubsystemList subsystems) {

        setName("Leave Auto");

        addCommands(
            new AutoMoveCommand(subsystems, 5, 270, .5, 0, 0)
        );
    }

}
