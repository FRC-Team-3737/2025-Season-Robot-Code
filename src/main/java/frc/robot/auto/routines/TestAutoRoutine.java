package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.auto.commands.ClawOpenAutoCommand;
import frc.robot.auto.commands.CoralLevelAutoCommand;
import frc.robot.commands.DriveCommands.AutoMoveCommand;

public class TestAutoRoutine extends SequentialCommandGroup {

    public TestAutoRoutine(SubsystemList subsystems) {

        setName("Test Auto");

        addCommands(
            new CoralLevelAutoCommand(subsystems, 4),
            new AutoMoveCommand(subsystems, 0.35, 93, .15, 0, 0),
            new WaitCommand(0.5),
            new ClawOpenAutoCommand(subsystems)
        );

    }


}
