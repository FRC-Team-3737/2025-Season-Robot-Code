package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWM;

public class LEDSubsystem extends SubsystemBase {

    private final PWM led;

    public LEDSubsystem() {

        led = new PWM(0); // 0 is a placeholder

    }

    public void SetLED(double speed) {

        led.setSpeed(speed);

    }
    
}
