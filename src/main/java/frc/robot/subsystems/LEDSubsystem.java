package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;

import static edu.wpi.first.units.Units.Feet;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

import java.util.Map;

public class LEDSubsystem extends SubsystemBase {

    private PWM blinkinled;
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;
    private Distance ledSpacing;

    public enum patternType {
        solid, gradient, discontgradient, steps
    }

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
     * Set the color(s) to be used on the LED.
     * 
     * @param color The RGB value
     * @return The colors to be used
     */
    public Color[] SetColors(int[]... color) {

        Color[] colors = {};

        for (int i = 0; i < color.length; i++) {

            int r = color[i][0];
            int g = color[i][1];
            int b = color[i][2];
            colors[i] = new Color(r, g, b);

        }

        return colors;

    }

    /**
     * Sets the pattern to be used on the LEDs.
     * 
     * <p>{@link} https://docs.wpilib.org/en/stable/docs/software/hardware-apis/misc/addressable-leds.html
     * 
     * @param colors The colors to be used
     * @return The LED Pattern to use
     */
    public LEDPattern SetPattern(Color[] colors, patternType pattern) {

        LEDPattern selectedPattern;

        if (pattern == patternType.solid) {
            selectedPattern = LEDPattern.solid(colors[0]);
        } else if (pattern == patternType.gradient) {
            selectedPattern = LEDPattern.gradient(LEDPattern.GradientType.kContinuous, colors[0]);
        } else if (pattern == patternType.discontgradient) {
            selectedPattern = LEDPattern.gradient(LEDPattern.GradientType.kDiscontinuous, colors[0]);
        } else if (pattern == patternType.steps) {
            selectedPattern = LEDPattern.steps(Map.of(0.5, colors[0]));
        } else {
            selectedPattern = LEDPattern.solid(new Color(0, 0, 0));
        }

        return selectedPattern;

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
