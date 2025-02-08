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
    private Distance ledSpacing;

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
     * <p>{@link https://1166281274-files.gitbook.io/~/files/v0/b/gitbook-x-prod.appspot.com/o/spaces%2F-ME3KPEhFI6-MDoP9nZD%2Fuploads%2FMOYJvZmWgxCVKJhcV5fn%2FREV-11-1105-LED-Patterns.pdf?alt=media&token=e8227890-6dd3-498d-834a-752fa43413fe}
     * 
     * @param speed
     */
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

        LEDPattern.solid(new Color("00ff00")).applyTo(ledBuffer);

        led.setData(ledBuffer);
        led.start();

        ledSpacing = Feet.of(leds*ledDensity);

    }

    /**
     * Set the color(s) to be used on the LED.
     * 
     * @param color The hex value
     * @return The colors to be used
     */
    public Color[] SetColors(String... color) {

        Color[] colors = {};

        for (int i = 0; i < color.length; i++) {
            colors[i] = new Color(color[i]);
        }

        return colors;

    }

    /**
     * Sets the pattern to be used on the LEDs. If you add an array for the solid pattern, it selects the first color.
     * 
     * <p>{@link https://docs.wpilib.org/en/stable/docs/software/hardware-apis/misc/addressable-leds.html}
     * 
     * @param colors The colors to be used
     * @return The LED Pattern to use
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
            double section = 1/colors.length;
            Map<Number, Color> data = new HashMap<Number, Color>();
            for (int i = 0; i < colors.length; i++) {
                data.put(section*(i+1), colors[i]);
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
