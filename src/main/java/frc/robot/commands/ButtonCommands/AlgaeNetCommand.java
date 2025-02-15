package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;

import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;

public class AlgaeNetCommand extends SequentialCommandGroup {

    public AlgaeNetCommand(SubsystemList subsystems) {

        addCommands(
            
            new ArmPivotMoveCommand(subsystems, armType.grabber, 175, 1, 1, .3),
            new ArmPivotStopCommand(subsystems, armType.grabber),
            new GrabberShootCommand(subsystems, .5),
            new ArmPivotMoveCommand(subsystems, armType.grabber, 10, 1, 0, .3).alongWith(new GrabberStopCommand(subsystems)),
            new ArmPivotStopCommand(subsystems, armType.grabber)

        );

    }

}
