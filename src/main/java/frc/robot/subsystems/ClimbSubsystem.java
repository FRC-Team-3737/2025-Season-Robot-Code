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

        motor = new Motors(Constants.CLIMB, encoderType.Absolute, true);
        rotationActive = false;

        desiredAngle = 0;
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

    public void Rotate(double speed) {

        if (!rotationActive) return;

        double angle = GetCurrentAngle();
        double minAngle = 20;
        double maxAngle = 120;

        if (angle < minAngle || angle > maxAngle) {
            Stop();
            return;
        }

        motor.Spin(speed);

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }
}
