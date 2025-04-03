package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.auto.commands.ClawOpenAutoCommand;
import frc.robot.auto.commands.CoralLevelAutoCommand;
import frc.robot.commands.DriveCommands.AutoMoveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class TestAutoRoutine extends SequentialCommandGroup {

    public TestAutoRoutine(SubsystemList subsystems) {

        setName("Test Auto");
        addCommands(
            new InstantCommand(() -> DriveSubsystem.resetGyro(180)),
            new CoralLevelAutoCommand(subsystems, 4),
            new AutoMoveCommand(subsystems, 0.4, 90, .15, 0, 0),
            new AutoMoveCommand(subsystems, 1, 0, 0, 0, 60).raceWith(
                new WaitCommand(3)),
            new AutoMoveCommand(subsystems, 0.1, 150, .15, 0, 60),
            new WaitCommand(0.5),
            new ClawOpenAutoCommand(subsystems)
        );

    }


}
