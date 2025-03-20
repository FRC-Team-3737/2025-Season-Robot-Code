package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.utils.SubsystemList;

public class DefaultLeds extends Command {
    
    final LEDSubsystem led;

    public DefaultLeds(SubsystemList subsystems) {

        led = (LEDSubsystem) subsystems.getSubsystem("led");

        addRequirements(led);

    }

    @Override
    public void initialize() {

        led.Default();;

    }

    @Override
    public boolean isFinished() {

        return false;

    }

    @Override
    public boolean runsWhenDisabled() {

        return true;

    }

}
