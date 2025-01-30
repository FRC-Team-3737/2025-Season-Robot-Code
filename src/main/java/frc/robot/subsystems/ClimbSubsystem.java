package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.SubsystemBase;

import frc.robot.motor.Motors;
import frc.robot.Constants;
import frc.robot.motor.Motor.encoderType;

public class ClimbSubsystem extends SubsystemBase {

    private final Motors motor;
    private boolean rotationActive;
    private double desiredAngle;

    public ClimbSubsystem() {

        motor = new Motors(Constants.CLIMB, encodertype.Absolute, true);
        rotationActive = false;

        desiredAngle = 0;
        rotationActive = false;

    }

    public getCurrentAngle() {

        return motor.mainMotor.getAbsoluteAngle();

    }

    private boolean IsInDesiredZone (double deadzone) {

        return desiredAngle > this.GetCurrentPosition() - deadzone && desiredAngle < this.GetCurrentPosition() + deadzone;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean IsReady(double deadzone) {

        return IsInDeadzone(deadzone) && motor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Rotate() {

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
