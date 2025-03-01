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

import java.util.HashMap;
import java.util.Map;

public class LEDSubsystem extends SubsystemBase {

    private PWM blinkinled;
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;

    public enum patternType {
        solid, gradient, discontgradient, steps
    }

    /**
     * Defines the LED object when plugged into the Blinkin.
     */
    public LEDSubsystem() {

        setName("led");

        blinkinled = new PWM(0); // 0 is a placeholder

    }

    /**
     * Sets the color of the LED based on a value.
     * 
     * @param speed
     * @see <a href="https://1166281274-files.gitbook.io/~/files/v0/b/gitbook-x-prod.appspot.com/o/spaces%2F-ME3KPEhFI6-MDoP9nZD%2Fuploads%2FMOYJvZmWgxCVKJhcV5fn%2FREV-11-1105-LED-Patterns.pdf?alt=media&token=e8227890-6dd3-498d-834a-752fa43413fe">Blinkin Patterns</a>
     */
    public void BlinkinSetLED(double speed) {

        blinkinled.setSpeed(speed);

    }

    /**
     * Defines the Addressable LED object and sets all the data.
     * 
     * @param leds amount of LEDs in the strip
     */
    public LEDSubsystem(int leds, int ledDensity) {

        setName("led");

        led = new AddressableLED(Constants.LED_PORT);

        ledBuffer = new AddressableLEDBuffer(leds);
        led.setLength(ledBuffer.getLength());

        LEDPattern.solid(new Color("#00FF00")).applyTo(ledBuffer);

        led.setData(ledBuffer);
        led.start();

    }

    /**
     * Set the color(s) to be used on the LED.
     * 
     * @param color The hex value
     * @return The colors to be used
     */
    public Color[] SetColors(String... color) {

        Color[] colors = new Color[color.length];

        for (int i = 0; i < color.length; i++) {
            colors[i] = new Color(color[i]);
        }

        return colors;

    }

    /**
     * Sets the pattern to be used on the LEDs. If you add an array for the solid pattern, it selects the first color.
     * 
     * @param colors The colors to be used
     * @return The LED Pattern to use
     * @see <a href="https://docs.wpilib.org/en/stable/docs/software/hardware-apis/misc/addressable-leds.html">LED Patterns</a>
     */
    public LEDPattern SetPattern(Color[] colors, patternType pattern) {

        LEDPattern selectedPattern;

        if (pattern == patternType.solid) {
            selectedPattern = LEDPattern.solid(colors[0]);
        } else if (pattern == patternType.gradient) {
            selectedPattern = LEDPattern.gradient(LEDPattern.GradientType.kContinuous, colors);
        } else if (pattern == patternType.discontgradient) {
            selectedPattern = LEDPattern.gradient(LEDPattern.GradientType.kDiscontinuous, colors);
        } else if (pattern == patternType.steps) {
            Map<Number, Color> data = new HashMap<>();
            for (int i = 0; i < colors.length; i++) {
                data.put(i*(1/(double)colors.length), colors[i]);
            }
            selectedPattern = LEDPattern.steps(data);
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
     * @param ledDensity how many LEDs per foot
     * @return Length of distance between LED in feet
     */
    public Distance GetLEDLength(double ledDensity) {

        return Feet.of(1 / ledDensity);

    }

    @Override
    public void periodic() {

        led.setData(ledBuffer);

    }

    public Command RunPattern(LEDPattern pattern) {

        return run(() -> pattern.applyTo(ledBuffer));

    }
    
}
