package frc.robot.commands.ButtonCommands.Safety;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.GrabberSubsystem;

import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class StowCommand extends SequentialCommandGroup {

    public StowCommand(SubsystemList subsystems, armType type) {

        GrabberSubsystem grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

        switch (type) {

            case grabber:
                addCommands(
                    new ArmMoveCommand(subsystems, type, 0, 1.00).alongWith(
                        new GrabberStopCommand(subsystems)),
                    new ArmPivotCommand(subsystems, type, 55, 1.0),
                    new ArmPivotHoldCommand(subsystems, type).alongWith(
                        new WaitCommand(0.5)).unless(() -> grabber.GetAlgeaOut()),
                    new ArmFullStopCommand(subsystems, type).unless(() -> grabber.GetAlgeaIn())
                );

            case claw:
                addCommands(
                    new ArmMoveCommand(subsystems, type, 0, 1.00),
                    new ArmPivotCommand(subsystems, type, 55, 1.0).alongWith(
                        new WristPivotCommand(subsystems, 100, 0.5)),
                    new ArmFullStopCommand(subsystems, type).alongWith(
                        new WristStopCommand(subsystems))
                );

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, type)
                );

        }

    }

}
