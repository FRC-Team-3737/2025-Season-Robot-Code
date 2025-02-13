package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.Motors;
import frc.robot.Constants;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ClimbSubsystem extends SubsystemBase {

    private final Motors motor;
    private final PID pid;
    private boolean rotationActive;
    private double desiredAngle;
    private double tolerance;

    public ClimbSubsystem() {

        setName("climb");

        motor = new Motors(Constants.CLIMB, encoderType.Absolute, true);
        pid = new PID(0.001, 0.001, 0);
        rotationActive = false;

    }

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && motor.GetVelocity() < 100;

    }

    public double GetCurrentAngle() {

        return motor.motor.getAbsoluteAngle();

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot() {

        if (!rotationActive) return;

        double minAngle = 0;
        double maxAngle = 180;

        // if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
        //     Stop();
        //     return;
        // }

        double pidVal = pid.GetPIDValue(GetCurrentAngle(), desiredAngle);
        motor.Spin(pidVal);

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }
    
}
