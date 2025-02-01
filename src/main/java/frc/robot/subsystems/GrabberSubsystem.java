package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.motor.Motors;
import frc.robot.Constants;

public class GrabberSubsystem extends SubsystemBase {

    private final Motors motor;
    private final DigitalInput microswitch;

    public GrabberSubsystem() {

        setName("grabber");
        
        motor = new Motors(Constants.GRABBER);

        microswitch = new DigitalInput(0);

    }

    public Boolean GetAlgeaIn() {

        return !microswitch.get();

    }

    public Boolean GetAlgeaOut() {

        return microswitch.get();

    }
    
    public void Shoot(double speed) {
        
        motor.Spin(Math.abs(speed));

    }

    public void Intake(double speed) {
        
        motor.Spin(-Math.abs(speed));

    }

    public void Stop() {
        
        motor.Spin(0);

    }
    
    public void GetDebuggingInfo() {

        // No debugging info to provide

    }
}
