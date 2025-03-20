package frc.robot.utils;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.patternType;

public class LEDTriggerManager {

    private final SubsystemList subsystems;
    private final LEDSubsystem led;
    private final GrabberSubsystem grabber;
    private final ClawSubsystem claw;

    public LEDTriggerManager(SubsystemList subsystemList) {

        subsystems = subsystemList;
        led = (LEDSubsystem) subsystems.getSubsystem("led");
        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");
        claw = (ClawSubsystem) subsystems.getSubsystem("claw");

        setupLEDTriggers();

    }
    
    public void setupLEDTriggers() {

        // Match State
        autoStartTrigger();

        // Gameplay
        coralAcquiredTrigger();
        algaeAcquiredTrigger();
        bothAcquiredTrigger();

        // Battery

        // Errors

    }

    //#region Gamepieces

    private void coralAcquiredTrigger() {

        new Trigger(coralAcquiredSupplier()).onTrue(coralAcquiredCommand().unless(algaeAcquiredSupplier()));

    }

    private void algaeAcquiredTrigger() {

        new Trigger(algaeAcquiredSupplier()).onTrue(algaeAcquiredCommand().unless(coralAcquiredSupplier()));

    }

    private void bothAcquiredTrigger() {

        new Trigger(bothAcquiredSupplier()).onTrue(bothAcquiredCommand());

    }

    private BooleanSupplier coralAcquiredSupplier() {

        return () -> claw.GetCoralState();

    }

    private BooleanSupplier algaeAcquiredSupplier() {

        return () -> grabber.GetAlgaeState();

    }

    private BooleanSupplier bothAcquiredSupplier() {

        return () -> grabber.GetAlgaeState() && claw.GetCoralState();

    }

    private Command coralAcquiredCommand() {

        return led.RunPattern(coralAcquiredPattern());

    }

    private Command algaeAcquiredCommand() {

        return led.RunPattern(algaeAcquiredPattern());

    }

    private Command bothAcquiredCommand() {

        return led.RunPattern(bothAcquiredPattern());

    }

    private LEDPattern coralAcquiredPattern() {

        return led.SetPattern(led.SetColors("#FFFFFF"), patternType.solid);

    }

    private LEDPattern algaeAcquiredPattern() {

        return led.SetPattern(led.SetColors("#00E073"), patternType.solid);

    }

    private LEDPattern bothAcquiredPattern() {

        return led.SetPattern(led.SetColors("#00E073", "#FFFFFF"), patternType.steps);

    }

    //#endregion Gamepieces

    //#region AutoStart

    private void autoStartTrigger() {

        new Trigger(autoStartSupplier()).onTrue(autoStartCommand());

    }

    private BooleanSupplier autoStartSupplier() {

        return () -> DriverStation.isAutonomousEnabled();

    }

    private Command autoStartCommand() {

        return led.RunPattern(autoStartPattern());

    }

    private LEDPattern autoStartPattern() {

        return led.SetPattern(led.SetColors("#E06000"), patternType.solid);

    }

    //#endregion AutoStart

}
