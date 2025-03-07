package frc.robot.commands.ButtonCommands.Claw;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;

import frc.robot.commands.ArmCommands.ArmMoveCommand;
import frc.robot.commands.ArmCommands.ArmExtensionStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawCloseCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

public class CoralFeederIntakeCommand extends SequentialCommandGroup {

    public CoralFeederIntakeCommand(SubsystemList subsystems) {

        addCommands(
            // PLACEHOLDER VALUES
            // NOT FINAL, LIMELIGHT MUST BE INCORPORATED
            new ArmPivotMoveCommand(subsystems, armType.claw, 0, 1, 1, 1).alongWith(new ClawOpenCommand(subsystems, 1)).raceWith(new WaitCommand(1)),
            new ArmPivotCommand(subsystems, armType.claw, -45,1),
            new ClawCloseCommand(subsystems, 1).alongWith(new ArmPivotCommand(subsystems, armType.claw, 45,1)),
            new ArmFullStopCommand(subsystems, armType.claw).alongWith(new ClawStopCommand(subsystems))
        );

    }
}
