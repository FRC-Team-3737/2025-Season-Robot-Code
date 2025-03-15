package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.utils.SubsystemList;

public class WristStopCommand extends Command {

    final ClawSubsystem claw;

    public WristStopCommand(SubsystemList subsystems) {

        claw = (ClawSubsystem) subsystems.getSubsystem(subsystemType.CLAW.name());

        addRequirements(claw);

    }

    @Override
    public void initialize() {

        claw.WristStop();

    }

    @Override
    public boolean isFinished() {

        return true;
        
    }
}
