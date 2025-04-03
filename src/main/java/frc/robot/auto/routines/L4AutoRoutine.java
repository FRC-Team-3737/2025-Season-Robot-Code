package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.utils.AutoPicker.AutoSide;
import frc.robot.auto.commands.ClawOpenAutoCommand;
import frc.robot.auto.commands.CoralLevelAutoCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.ButtonCommands.Safety.CancelCommand;
import frc.robot.commands.ClawCommands.WristIdleCommand;
import frc.robot.commands.DriveCommands.AutoMoveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;

public class L4AutoRoutine extends SequentialCommandGroup {

    public L4AutoRoutine(SubsystemList subsystems, AutoSide side) {

        switch (side) {

            case Center:
                setName("Center - Level 4 Auto");
                addCommands(
                    new InstantCommand(() -> DriveSubsystem.resetGyro(180)),
                    new CoralLevelAutoCommand(subsystems, 4),
                    new AutoMoveCommand(subsystems, 0.35, 90, .15, 0, 0).raceWith(
                        new ArmPivotHoldCommand(subsystems, armType.claw),
                        new WristIdleCommand(subsystems),
                        new WaitCommand(2.5)),
                    new WaitCommand(0.5),
                    new ClawOpenAutoCommand(subsystems)
                );
                break;

            default:
                addCommands(
                    new CancelCommand(subsystems));

        }

    }


}
