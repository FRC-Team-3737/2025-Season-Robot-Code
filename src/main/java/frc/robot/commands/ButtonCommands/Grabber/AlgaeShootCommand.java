package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeShootCommand extends SequentialCommandGroup {

    public AlgaeShootCommand(SubsystemList subsystems) {

        GrabberArmSubsystem grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");

        addCommands(
            new GrabberIntakeCommand(subsystems, .25).raceWith(
                    new WaitCommand(.25)),
            new GrabberStopCommand(subsystems).alongWith(
                new ArmPivotHoldCommand(subsystems, armType.grabber)),
            new GrabberShootCommand(subsystems, 0.05).raceWith(
                new WaitCommand(.5)).unless(
                () -> grabberArm.GetCurrentAngle() > 90),
            new GrabberShootCommand(subsystems, 0.80).raceWith(
                new WaitCommand(.5)).unless(
                () -> grabberArm.GetCurrentAngle() < 90),
            new GrabberStopCommand(subsystems)
        );

    }

}
