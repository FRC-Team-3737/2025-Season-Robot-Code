package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;

public class LEDSubsystem extends SubsystemBase {

    private PWM blinkinled;
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;

    public LEDSubsystem() {

        blinkinled = new PWM(0); // 0 is a placeholder

    }

    public void BlinkinSetLED(double speed) {

        blinkinled.setSpeed(speed);

    }

    public LEDSubsystem(int ledLength) {

        led = new AddressableLED(0); // 0 is a placeholder

        ledBuffer = new AddressableLEDBuffer(ledLength);
        led.setLength(ledBuffer.getLength());

        led.setData(ledBuffer);
        led.start();

    }

    public Color GetColor(int r, int g, int b) {

        return new Color(r, g, b);

    }

    @Override
    public void periodic() {

        led.setData(ledBuffer);

    }

    public Command RunPattern(LEDPattern pattern) {

        return run(() -> pattern.applyTo(ledBuffer));

    }
    
}
