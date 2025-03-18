package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class PurgeSchedulerCommand extends Command {
    
    public PurgeSchedulerCommand() {}

    public void execute() {

        CommandScheduler.getInstance().cancelAll();

    }

    public boolean isFinished() {

        return true;
        
    }

}
