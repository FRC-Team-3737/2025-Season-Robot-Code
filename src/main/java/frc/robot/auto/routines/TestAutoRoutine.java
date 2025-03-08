package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.SubsystemList;
import frc.robot.auto.commands.ClawOpenAutoCommand;
import frc.robot.auto.commands.CoralLevelAutoCommand;
import frc.robot.commands.DriveCommands.AutoMoveCommand;

public class TestAutoRoutine extends SequentialCommandGroup {

    public TestAutoRoutine(SubsystemList subsystems) {

        addCommands(
            
            new AutoMoveCommand(subsystems, 5, 270, .5, 0, 0),
            new CoralLevelAutoCommand(subsystems, 3),
            new ClawOpenAutoCommand(subsystems)

        );

    }


}
