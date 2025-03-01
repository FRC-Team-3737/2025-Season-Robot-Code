package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.PairedMotors;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

    private final PairedMotors motor;
    private boolean rotationActive;
    private double desiredAngle;
    private double tolerance;

    public ClimbSubsystem() {

        setName("climb");

        motor = new PairedMotors(Constants.Climb, Constants.ClimbSlave);
        rotationActive = false;

    }

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance);

    }

    public double GetCurrentAngle() {

        return motor.mainMotor.getAbsoluteAngle();

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot(double speed) {

        if (!rotationActive) return;

        double minAngle = -95;
        double maxAngle = 15;

        if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
            Stop();
            return;
        }

        if (motor.mainMotor.getPosition(false) < desiredAngle - 3) {
            motor.Spin(Math.abs(speed));
        } else if (motor.mainMotor.getPosition(false) > desiredAngle + 3){
            motor.Spin(-Math.abs(speed));
        } else {
            Stop();
        }

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }

    public void DisplayDebuggingInfo() {
        Shuffleboard.getTab(getName()).addNumber("Current Angle", () -> motor.mainMotor.getPosition(false));
    }
    
}
