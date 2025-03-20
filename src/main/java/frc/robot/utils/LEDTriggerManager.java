package frc.robot.utils;

import edu.wpi.first.wpilibj.util.Color;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.patternType;

public class LEDTriggerManager {

    private final SubsystemList subsystems;
    private final LEDSubsystem led;

    public LEDTriggerManager(SubsystemList subsystemList) {

        subsystems = subsystemList;
        led = (LEDSubsystem) subsystems.getSubsystem("led");
        setupLEDTriggers();

    }
    
    public void setupLEDTriggers() {

        autoStartTrigger();
        // anotherTrigger();
        // yetAnotherTrigger();

    }

    //#region AutoStart

    private void autoStartTrigger() {

        new Trigger(autoStartSupplier()).onTrue(autoStartCommand());

    }

    private BooleanSupplier autoStartSupplier() {

        return (BooleanSupplier) () -> DriverStation.isAutonomousEnabled();

    }

    private Command autoStartCommand() {

        return led.RunPattern(autoStartPattern());

    }

    private LEDPattern autoStartPattern() {

        return led.SetPattern(patternType.solid, Color.kOrange);

    }

    //#endregion AutoStart

}
