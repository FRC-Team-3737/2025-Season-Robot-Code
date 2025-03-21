package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ClawSubsystem extends SubsystemBase {

    private final Motors wristMotor;
    private final PID wristPID;
    private final Motors clawMotor;
    private double tolerance;
    private double desiredAngle;
    private boolean rotationActive;
    
    public ClawSubsystem() {

        setName("claw");

        wristMotor = new Motors(Constants.Wrist, encoderType.Analog, true);
        clawMotor = new Motors(Constants.Claw);
        wristPID = new PID(getName(), 0.01, 0.001, 0);
        wristPID.ContinuousInput(0, 360);

    }

    private double GetCurrentAngle() {

        return wristMotor.motor.getAnalogAngle();

    }

    private double GetCurrentPosition() {
        
        return clawMotor.motor.getPosition(false);

    }

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public double GetClawPosition() {

        return clawMotor.motor.getPosition(false);

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && wristMotor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot() {

        if (!rotationActive) return;

        double minAngle = 5;
        double maxAngle = 175;

         if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
             WristStop();
             return;
         }

        double pidVal = wristPID.GetPIDValue(GetCurrentAngle(), desiredAngle);
        ActivateRotation();
        wristMotor.Spin(pidVal);

    }

    public void WristStop() {

        wristMotor.Spin(0);
        rotationActive = false;

    }

    public void Open(double speed) {

        double maxPull = 4.5;

        if (GetCurrentPosition() >= maxPull) {
            clawMotor.Spin(0);
            return;
        }

        clawMotor.Spin(Math.abs(speed));

    }

    public void Close(double speed) {

        if (GetCurrentPosition() <= 0) {
            clawMotor.Spin(.03);
            return;
        }

        clawMotor.Spin(-Math.abs(speed));

    }

    public void ClawStop() {

        clawMotor.Spin(0);

    }

    public void DisplayDebuggingInfo() {

        SmartDashboard.putNumber("encoder voltage", wristMotor.motor.getAnalogVoltage());
        SmartDashboard.putNumber("encoder angle", wristMotor.motor.getAnalogAngle());
        // Shuffleboard.getTab(getName()).addDouble("desired angle", () -> desiredAngle);
        // Shuffleboard.getTab(getName()).addDouble("current angle", () -> GetCurrentAngle());
        // Shuffleboard.getTab(getName()).addDouble("set speed", () -> wristMotor.motor.motor.get());

    }

}
