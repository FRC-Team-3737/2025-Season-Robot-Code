package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.Motors;
import frc.robot.Constants;
import frc.robot.motor.Motor.encoderType;

public class ClimbSubsystem extends SubsystemBase {

    private final Motors motor;
    private boolean rotationActive;
    private double desiredAngle;

    public ClimbSubsystem() {

        setName("climb");

        motor = new Motors(Constants.CLIMB, encoderType.Absolute, true);
        rotationActive = false;

    }

    public double GetCurrentAngle() {

        return motor.motor.getAbsoluteAngle();

    }

    private boolean IsInDesiredZone (double deadzone) {

        return desiredAngle > this.GetCurrentAngle() - deadzone && desiredAngle < this.GetCurrentAngle() + deadzone;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean IsReady(double deadzone) {

        return IsInDesiredZone(deadzone) && motor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot(double speed) {

        if (!rotationActive) return;

        double minAngle = 0;
        double maxAngle = 180;

        if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
            Stop();
            return;
        }

        if (GetCurrentAngle() < desiredAngle - 0.5) {
            motor.Spin(Math.abs(speed));
        } else if (GetCurrentAngle() > desiredAngle + 0.5) {
            motor.Spin(-Math.abs(speed));
        } else { 
            Stop();
        }

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }
    
}
