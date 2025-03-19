package frc.robot.commands.ButtonCommands.Safety;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.ArmFullStopCommand;
import frc.robot.commands.ClawCommands.WristStopCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.GrabberCommands.GrabberStopCommand;
import frc.robot.commands.GrabberCommands.ServoUnlockCommand;
import frc.robot.subsystems.ArmSubsystem.armType;
import frc.robot.utils.SubsystemList;

public class CancelCommand extends SequentialCommandGroup {
    
    public CancelCommand(SubsystemList subsystems) {

        addCommands(
            new ArmFullStopCommand(subsystems, armType.claw).alongWith(
                new ArmFullStopCommand(subsystems, armType.grabber),
                new ClimbStopCommand(subsystems),
                new WristStopCommand(subsystems),
                new GrabberStopCommand(subsystems),
                new ServoUnlockCommand(subsystems)
            )
        );

    }

}
