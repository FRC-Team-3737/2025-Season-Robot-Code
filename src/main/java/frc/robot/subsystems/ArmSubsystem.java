package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.MotorInfo;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ArmSubsystem extends SubsystemBase {

    protected final Motors pivotMotor;
    protected final PID pivotPID;
    protected double desiredAngle;
    protected boolean pivotActive;
    protected double minAngle; // currently 0 or -129 from vertical for both arms
    protected double maxAngle; // currently 160 or 31 from vertical for both arms

    protected final Motors extensionMotor;
    protected final PID extensionPID;
    protected double desiredExtension;
    protected double extensionLimit;
    protected double minExtension;
    protected double maxExtension;

    protected double upperMechanismLength;
    protected double lowerMechanismLength;

    public ArmSubsystem(MotorInfo pivotID, MotorInfo extensionID, encoderType encoder, boolean inverted, double[] m_pivotPID, double[] m_extensionPID) {

        pivotMotor = new Motors(pivotID, encoder, inverted);
        extensionMotor = new Motors(extensionID);
        pivotPID = new PID(m_pivotPID[0], m_pivotPID[1], m_pivotPID[2]);
        extensionPID = new PID(m_extensionPID[0], m_extensionPID[1], m_extensionPID[2]);

    }

    protected double GetTrueLength() {

        if (pivotMotor.motor.getAbsoluteAngle() > 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.inBuiltEncoder.getPosition(), 2) + Math.pow(lowerMechanismLength, 2)));
        } else if (pivotMotor.motor.getAbsoluteAngle() < 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.inBuiltEncoder.getPosition(), 2) + Math.pow(upperMechanismLength, 2)));
        } else {
            return extensionMotor.motor.inBuiltEncoder.getPosition() + lowerMechanismLength;
        }

    }

    protected double GetExtensionLimit() {

        // The entire system is currently represented in inches, not the value of the extension motor.

        double a = Math.tan(pivotMotor.motor.getAbsoluteAngle()) * 18;

        if (GetCurrentAngle() < 30) {
            extensionLimit = 0;
        } else if (GetCurrentAngle() < 135) {
            extensionLimit = Math.sqrt(Math.pow(a, 2) + Math.pow(18, 2));
        } else {
            extensionLimit = maxExtension;
        }
        
        return extensionLimit;

    }

    protected double GetCurrentAngle() {
        
        return pivotMotor.motor.getAbsoluteAngle();

    }

    protected void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    protected void SetDesiredExtension(double extension) {

        desiredExtension = extension;

    }

    protected void ActivatePivot() {

        pivotActive = true;

    }

    protected void Pivot() {

        if (!pivotActive) return;

        if (GetCurrentAngle() <= minAngle || GetCurrentAngle() >= maxAngle) pivotMotor.Spin(0);

        pivotMotor.Spin(pivotPID.GetPIDValue(GetCurrentAngle(), desiredAngle));
        
    }

    protected void PivotStop() {

        pivotActive = false;
        pivotMotor.Spin(0);

    }

    protected void Extend(double speed) {

        if (GetTrueLength() >= GetExtensionLimit()) Retract(0.05);

        extensionMotor.Spin(Math.abs(speed));

    }

    protected void Retract(double speed) {

        if (GetTrueLength() <= minExtension) extensionMotor.Spin(0);

        extensionMotor.Spin(-Math.abs(speed));

    }

    protected void ExtensionStop() {

        extensionMotor.Spin(0);

    }

    protected void DebuggingInfo() {/*return String[]*/}

}
