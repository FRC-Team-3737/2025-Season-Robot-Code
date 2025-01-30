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

        wristMotor = new Motors(Constants.CLAW_PIVOT_MOTOR, encoderType.Absolute, false);
        clawMotor = new Motors(Constants.CLAW_MOTOR);

    }

    // Wrist section


    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    private double GetCurrentAngle() {

        return wristMotor.motor.getAbsoluteAngle();

    }

    private boolean IsInDeadzone(double deadzone) {

        return desiredAngle > GetCurrentAngle() - deadzone && desiredAngle < GetCurrentAngle() + deadzone;

    }

    private boolean IsReady(double deadzone) {

        return IsInDeadzone(deadzone) && wristMotor.GetVelocity() < 100;

    }

    private void ActivateRotation() {

        rotationActive = true;

    }



    private void WristStop() {

        wristMotor.Spin(0);
        rotationActive = false;

    }

    public void PivotToTarget(double speed) {

        if (!rotationActive) return;

        double angle = GetCurrentAngle();
        double minAngle = 5;
        double maxAngle = 175;

        if (angle < minAngle || angle > maxAngle) {
            WristStop();
            return;
        }

        wristMotor.Spin(speed);

    }

    // Claw section
    public double GetClawPosition() {

        return clawMotor.motor.getPosition();

    }

    public void ClawSpinPositive(double speed) {

        clawMotor.Spin(Math.abs(speed));

    }

    public void ClawSpinNegative(double speed) {

        clawMotor.Spin(-Math.abs(speed));

    }

    public void ClawStop() {

        clawMotor.Spin(0);

    }
}
