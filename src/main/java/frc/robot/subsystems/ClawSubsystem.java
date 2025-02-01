package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;

public class ClawSubsystem extends SubsystemBase {

    private final Motors wristMotor;
    private final Motors clawMotor;

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

    private boolean IsInDeadzone(double deadzone) {

        return desiredAngle > GetCurrentAngle() - deadzone && desiredAngle < GetCurrentAngle() + deadzone;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public double GetClawPosition() {

        return clawMotor.motor.getPosition();

    }

    public boolean IsReady(double deadzone) {

        return IsInDeadzone(deadzone) && wristMotor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void PivotToTarget(double speed) {

        if (!rotationActive) return;

        double minAngle = 5;
        double maxAngle = 175;

        if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
            WristStop();
            return;
        }

        if (wristMotor.motor.getAnalogAngle() <= desiredAngle) {
            wristMotor.Spin(Math.abs(speed));
        } else {
            wristMotor.Spin(-Math.abs(speed));
        }

    }

    public void WristStop() {

        wristMotor.Spin(0);
        rotationActive = false;

    }

    public void ClawOpen(double speed) {

        clawMotor.Spin(Math.abs(speed));

    }

    public void ClawClose(double speed) {

        clawMotor.Spin(-Math.abs(speed));

    }

    public void ClawStop() {

        clawMotor.Spin(0);

    }

    public void DisplayDebuggingInfo() {

        // No debugging info to display yet

    }

}
