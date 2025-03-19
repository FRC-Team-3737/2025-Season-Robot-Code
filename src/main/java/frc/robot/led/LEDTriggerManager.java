package frc.robot.led;

import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.patternType;
import frc.robot.utils.SubsystemList;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class LEDTriggerManager {

    final LEDSubsystem led;
    final GrabberSubsystem grabber;
    final ClawSubsystem claw;
    private double batteryVoltage;

    public LEDTriggerManager(SubsystemList subsystems) {

        led = (LEDSubsystem) subsystems.getSubsystem("led");
        grabber = (GrabberSubsystem) subsystems.getSubsystem("grabber");
        claw = (ClawSubsystem) subsystems.getSubsystem("claw");

        AlgaeAcquiredTrigger();
        CoralAcquiredTrigger();
        AlgaeCoralAcquiredTrigger();

    }

    private void AlgaeAcquiredTrigger() {

        new Trigger(AlgaeAcquiredSupplier()).onTrue(led.RunPattern(led.SetPattern(led.SetColors("#00E0AF"), patternType.solid)));

    }

    private void CoralAcquiredTrigger() {

        new Trigger(CoralAcquiredSupplier()).onTrue(led.RunPattern(led.SetPattern(led.SetColors("#AAAAAA"), patternType.solid)));

    }

    private void AlgaeCoralAcquiredTrigger() {

        new Trigger(AlgaeCoralAcquiredSupplier()).onTrue(led.RunPattern(led.SetPattern(led.SetColors("#00E0AF", "#AAAAAA"), patternType.steps)));

    }

    private BooleanSupplier AlgaeAcquiredSupplier() {

        return () -> grabber.GetAlgeaIn();

    }

    private BooleanSupplier CoralAcquiredSupplier() {

        return () -> claw.GetCoralIn();

    }

    private BooleanSupplier AlgaeCoralAcquiredSupplier() {

        return () -> grabber.GetAlgeaIn() && claw.GetCoralIn();

    }
    
}