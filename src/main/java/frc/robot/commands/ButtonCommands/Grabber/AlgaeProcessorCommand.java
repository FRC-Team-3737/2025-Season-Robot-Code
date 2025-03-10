package frc.robot.commands.ButtonCommands.Grabber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.utils.SubsystemList;
import frc.robot.commands.ArmCommands.ArmPivotCommand;
import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmPivotStopCommand;
import frc.robot.commands.GrabberCommands.GrabberIntakeCommand;
import frc.robot.commands.GrabberCommands.GrabberShootCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.ArmCommands.ArmPivotHoldCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem.armType;

public class AlgaeProcessorCommand extends SequentialCommandGroup {

    public AlgaeProcessorCommand(SubsystemList subsystems) {

        addCommands(
            new ArmPivotCommand(subsystems, armType.grabber, 55, 1),
            new ArmPivotHoldCommand(subsystems, armType.grabber)
        );

    }

}
