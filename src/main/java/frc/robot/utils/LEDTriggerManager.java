package frc.robot.utils;

import java.util.function.BooleanSupplier;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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

        defaultTrigger();

        // Match State
        autoStartTrigger();
        teleopStartTrigger();
        endgameStartTrigger();

        // Gameplay
        coralAcquiredTrigger();
        algaeAcquiredTrigger();
        bothAcquiredTrigger();

        // Battery

        // Errors

    }

    private void defaultTrigger() {

        new Trigger(defaultSupplier()).onFalse(defaultCommand());

    }

    private BooleanSupplier defaultSupplier() {

        return () -> (grabber.GetAlgaeState() || claw.GetCoralState());

    }

    private Command defaultCommand() {

        return led.RunPattern(defaultPattern());

    }

    private LEDPattern defaultPattern() {

        return led.SetPattern(led.SetColors("#00FF00"), patternType.solid);

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

        return () -> claw.GetCoralState() && !grabber.GetAlgaeState();

    }

    private BooleanSupplier algaeAcquiredSupplier() {

        return () -> grabber.GetAlgaeState() && !claw.GetCoralState();

    }

    private BooleanSupplier bothAcquiredSupplier() {

        return () -> (grabber.GetAlgaeState() && claw.GetCoralState());

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

        return led.SetPattern(led.SetColors("#00E053"), patternType.solid);

    }

    private LEDPattern bothAcquiredPattern() {

        return led.SetAlternatingStepPattern(led.SetColors("#00E053", "#FFFFFF"), 2);

    }

    //#endregion Gamepieces

    //#region Match State

    private void autoStartTrigger() {

        new Trigger(autoStartSupplier()).onTrue(autoStartCommand());

    }

    private void teleopStartTrigger() {

        new Trigger(teleopStartSupplier()).onTrue(teleopStartCommand());

    }

    private void endgameStartTrigger() {

        new Trigger(endgameStartSupplier()).onTrue(endgameStartCommand());

    }

    private BooleanSupplier autoStartSupplier() {

        return () -> DriverStation.isAutonomousEnabled();

    }

    private BooleanSupplier teleopStartSupplier() {

        return () -> DriverStation.isTeleopEnabled();

    }

    private BooleanSupplier endgameStartSupplier() {

        return () -> DriverStation.isTeleopEnabled() && DriverStation.getMatchTime() <= 20;

    }

    private Command autoStartCommand() {

        return led.RunPattern(autoStartPattern());

    }

    private Command teleopStartCommand() {

        return led.RunPattern(teleopStartPattern());

    }

    private Command endgameStartCommand() {

        return led.RunPattern(endgameStartPattern());

    }

    private LEDPattern autoStartPattern() {

        return led.SetPattern(led.SetColors("#E06000", "E03000"), patternType.gradient).scrollAtAbsoluteSpeed(led.SetVelocity(2), led.GetLEDLength(19));

    }

    private LEDPattern teleopStartPattern() {

        return led.SetPattern(led.SetColors("#00FF00", "#00AA00"), patternType.gradient).scrollAtAbsoluteSpeed(led.SetVelocity(2), led.GetLEDLength(19));

    }

    private LEDPattern endgameStartPattern() {

        return led.SetPattern(led.SetColors("#0090FF", "#0060FF", "0030FF"), patternType.gradient).scrollAtAbsoluteSpeed(led.SetVelocity(2), led.GetLEDLength(19));

    }

    //#endregion Match State

}
