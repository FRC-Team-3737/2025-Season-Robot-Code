package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ClawCommands.WristPivotCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;

public class CoralLevelCommand extends SequentialCommandGroup {

    public CoralLevelCommand(SubsystemList subsystems, int reefLevel) {

        if (reefLevel == 1) {

            addCommands(
                new ArmPivotMoveCommand(subsystems, armType.claw, 45, 1, .1, .5),
                new ArmPivotStopCommand(subsystems, armType.claw)
            );

        } else if (reefLevel == 2) {

            addCommands(
                new ArmPivotMoveCommand(subsystems, armType.claw, 90, 1, .1, .5),
                new ArmPivotStopCommand(subsystems, armType.claw)
            );

        } else if (reefLevel == 3) {

            addCommands(
                new ArmPivotMoveCommand(subsystems, armType.claw, 125, 1, .1, .3),
                new ArmPivotStopCommand(subsystems, armType.claw)
            );

        }  else {

            addCommands(
                new ArmPivotMoveCommand(subsystems, armType.claw, 150, 1, .1, .5),
                new ArmPivotStopCommand(subsystems, armType.claw)
            );

        }

    }

}
