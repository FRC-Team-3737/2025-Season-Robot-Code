import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.motor.Motors;
import frc.robot.Constants;

public class GrabberSubsystem extends SubsystemBase {

    private final Motors motor;
    private final DigitalInput microswitch;
    
    protected double motorSpeed;

    public GrabberSubsystem() {

        setName("Grabber");
        
        motor = new Motors(Constants.EXAMPLE_INFO); //example_info placeholder

        microswitch = new DigitalInput(0);

    }

    public void SetSpeed(double speed) {
        
        motorSpeed = Math.abs(speed);

    }

    public Boolean SwitchObjectIn() {

        return !microswitch.get();

    }

    public Boolean SwitchObjectOut() {

        return microswitch.get();

    }
    
    public void PositiveMove() {
        
        motor.Spin(motorSpeed);

    }
    public void NegativeMove() {
        
        motor.Spin(-motorSpeed);

    }
    public void Stop() {
        
        motor.Spin(0);

    }
    public String[] GetDebuggingInfo() {

        return motor.GetDebuggingInformation("Grabber");

    }
}
