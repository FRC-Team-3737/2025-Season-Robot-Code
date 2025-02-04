package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;

public class ClawSubsystem extends SubsystemBase {

    private final Motors wristMotor;
    private final Motors clawMotor;
    private double tolerance;
    private double desiredAngle;
    private boolean rotationActive;

    
    public ClawSubsystem() {

        setName("claw");

        wristMotor = new Motors(Constants.CLAW_PIVOT_MOTOR, encoderType.Absolute, false);
        clawMotor = new Motors(Constants.CLAW_MOTOR);

    }

    private double GetCurrentAngle() {

        return wristMotor.motor.getAbsoluteAngle();

    }

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public double GetClawPosition() {

        return clawMotor.motor.getPosition();

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && wristMotor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot(double speed) {

        if (!rotationActive) return;

        double minAngle = 5;
        double maxAngle = 175;

        if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
            WristStop();
            return;
        }

        if (wristMotor.motor.getAnalogAngle() < desiredAngle - 0.5) {
            wristMotor.Spin(Math.abs(speed));
        } else if (wristMotor.motor.getAnalogAngle() > desiredAngle + 0.5) {
            wristMotor.Spin(-Math.abs(speed));
        } else {
            WristStop();
        }

    }

    public void WristStop() {

        wristMotor.Spin(0);
        rotationActive = false;

    }

    public void Open(double speed) {

        clawMotor.Spin(Math.abs(speed));

    }

    public void Close(double speed) {

        clawMotor.Spin(-Math.abs(speed));

    }

    public void ClawStop() {

        clawMotor.Spin(0);

    }

    public void DisplayDebuggingInfo() {

        // No debugging info to display yet

    }

}
