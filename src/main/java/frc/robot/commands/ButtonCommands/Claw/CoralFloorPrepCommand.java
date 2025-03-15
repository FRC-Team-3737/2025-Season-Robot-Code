package frc.robot.commands.ButtonCommands.Claw; 

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ArmCommands.ArmPivotMoveCommand;
import frc.robot.Constants.subsystemType;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ClawCommands.ClawOpenCommand;
import frc.robot.commands.ClawCommands.ClawStopCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;


import frc.robot.utils.SubsystemList;

public class CoralFloorPrepCommand extends SequentialCommandGroup{

    public CoralFloorPrepCommand (SubsystemList subsystems) {
        addCommands(
            //PLACEHOLDER VALUES BC I LEFT EARLY
            new ClawOpenCommand(subsystems, 1).alongWith(new ArmPivotMoveCommand(subsystems, subsystemType.CLAW_ARM, 0, 1, 1, 1)).raceWith(new WaitCommand(1)),
            new ClawStopCommand(subsystems).alongWith(new ArmFullStopCommand(subsystems, subsystemType.CLAW_ARM))
        );
    }
    
}
