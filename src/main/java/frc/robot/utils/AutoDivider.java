package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoDivider {
    
    private final Command[] commands;

    public AutoDivider(Command ...autoCommands ) {

        commands = autoCommands;

    }

    public SendableChooser<Command> populateCommands(SendableChooser<Command> sendableChooser) {
        
        sendableChooser.close();

        for (Command command : commands) {
            String name = command.getName();
            sendableChooser.addOption(name, command);
        }

        return sendableChooser;

    }

}
