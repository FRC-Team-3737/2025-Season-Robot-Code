package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;

import static edu.wpi.first.units.Units.Feet;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

public class LEDSubsystem extends SubsystemBase {

    private PWM blinkinled;
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;
    private Distance ledSpacing;

    public LEDSubsystem() {

        setName("led");

        blinkinled = new PWM(0); // 0 is a placeholder

    }

    public void BlinkinSetLED(double speed) {

        blinkinled.setSpeed(speed);

    }

    /**
     * Defines the Addressable LED object and sets all the data.
     * 
     * @param leds amount of LEDs in the strip
     * @param ledDensity how many LEDs per foot
     */
    public LEDSubsystem(int leds, int ledDensity) {

        setName("led");

        led = new AddressableLED(Constants.LED_PORT);

        ledBuffer = new AddressableLEDBuffer(leds);
        led.setLength(ledBuffer.getLength());

        led.setData(ledBuffer);
        led.start();

        ledSpacing = Feet.of(leds*ledDensity);

    }

    /**
     * Set the color to be used on the LED.
     * 
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return The color to be used
     */
    public Color SetColor(int r, int g, int b) {

        return new Color(r, g, b);

    }

    /**
     * <b> WORK IN PROGRESS </b>
     * 
     * <p>{@link} https://docs.wpilib.org/en/stable/docs/software/hardware-apis/misc/addressable-leds.html
     * 
     * @return The LED Pattern to use
     */
    public LEDPattern SetPattern() {

        LEDPattern pattern = LEDPattern.solid(null);

        return pattern;

    }

    /**
     * Sets the frequency that an action will happen.
     * 
     * @param seconds The time an operation should be repeated
     * @return Time in seconds
     */
    public Time SetFrequency(double seconds) {

        return Seconds.of(seconds);

    }

    /**
     * Sets the linear velocity at which an action should operate at.
     * 
     * @param inchesPerSecond The velocity an operation should run at
     * @return Inches per second
     */
    public LinearVelocity SetVelocity(double inchesPerSecond) {

        return Inches.per(Seconds).of(inchesPerSecond);

    }
    
    /**
     * Used to get the length of an LED for use in an action.
     * 
     * @return Length of LED in feet
     */
    public Distance GetLEDLength() {

        return ledSpacing;

    }

    @Override
    public void periodic() {

        led.setData(ledBuffer);

    }

    public Command RunPattern(LEDPattern pattern) {

        return run(() -> pattern.applyTo(ledBuffer));

    }
    
}
