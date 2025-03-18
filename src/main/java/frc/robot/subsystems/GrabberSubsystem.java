package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.motor.Motors;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Servo;

public class GrabberSubsystem extends SubsystemBase {

    private final Motors motor;
    private final DigitalInput microswitch;
    private final Servo servo;

    public GrabberSubsystem() {

        setName("grabber");
        
        motor = new Motors(Constants.Grabber);

        microswitch = new DigitalInput(Constants.GrabberSwitch);

        servo = new Servo(Constants.GrabberServo);

    }

    public Boolean GetAlgeaIn() {

        return !microswitch.get();

    }

    public Boolean GetAlgeaOut() {

        return microswitch.get();

    }
    
    public void Shoot(double speed) {
        
        motor.Spin(-Math.abs(speed));

    }

    public void Intake(double speed) {
        
        motor.Spin(Math.abs(speed));

    }

    public void Stop() {
        
        motor.Spin(0);

    }

    public void ServoLock() {
        servo.setPosition(0);
    }

    public void ServoUnlock() {
        servo.setPosition(1);
    }
    
    public void DisplayDebuggingInfo() {

        Shuffleboard.getTab(getName()).addBoolean("algae in", () -> GetAlgeaIn());
        Shuffleboard.getTab(getName()).addNumber("temp", () -> motor.GetTemperature());

    }
}
