package frc.robot.commands.ButtonCommands.Claw; 

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ClawCommands.ClawCloseCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.ArmSubsystem.armType;

import frc.robot.utils.SubsystemList;

public class CoralFloorIntakeCommand extends SequentialCommandGroup{

    public CoralFloorIntakeCommand (SubsystemList subsystems) {
        addCommands(
            //PLACEHOLDER VALUES BC I LEFT EARLY
            new ClawCloseCommand(subsystems, 1).alongWith(new ArmPivotMoveCommand(subsystems, armType.claw, 0, 45, 1, 1)).raceWith(new WaitCommand(1)),
            new ClawStopCommand(subsystems).alongWith(new ArmFullStopCommand(subsystems, armType.claw))
        );
    }
    
}
