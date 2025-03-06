package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeReefIntakeCommand extends SequentialCommandGroup {

    public AlgaeReefIntakeCommand(SubsystemList subsystems, String algaeLevel) {

        switch (algaeLevel.toLowerCase()) {

            case "lower":
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 0, 0)
                );

            case "upper":
                addCommands(
                    new ArmPivotCommand(subsystems, armType.grabber, 0, 0)
                );

            default:
                addCommands(
                    new ArmFullStopCommand(subsystems, armType.grabber)
                );


        }

    }

}
