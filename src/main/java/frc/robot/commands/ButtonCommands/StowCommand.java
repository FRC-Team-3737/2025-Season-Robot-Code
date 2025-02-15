package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.GrabberSubsystem;

import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;

import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class StowCommand extends SequentialCommandGroup {

    public StowCommand(SubsystemList subsystems, armType type) {

        GrabberSubsystem grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");

        if (grabber.GetAlgeaIn() && type == armType.grabber) { // if grabber arm and algae is in

            addCommands(

                new ArmPivotMoveCommand(subsystems, type, 25, 1, 0, .3),
                new ArmPivotStopCommand(subsystems, type)

            );

        } else if (type == armType.grabber) { // if grabber arm

            addCommands(
                
                new ArmPivotMoveCommand(subsystems, type, 15, 1, 0, .3),
                new ArmPivotStopCommand(subsystems, type)

            );

        } else { // if claw arm

            addCommands(
                
                new ArmPivotMoveCommand(subsystems, type, 10, 1, 0, .3).alongWith(new WristPivotCommand(subsystems, 0, 1)),
                new ArmPivotStopCommand(subsystems, type).alongWith(new WristStopCommand(subsystems))

            );

        }

    }

}
