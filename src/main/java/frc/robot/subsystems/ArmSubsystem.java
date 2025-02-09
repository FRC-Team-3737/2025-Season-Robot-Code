package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.MotorInfo;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ArmSubsystem extends SubsystemBase {

    private final Motors pivotMotor;
    private final PID pivotPID;
    private double desiredAngle;
    private double tolerance;
    private boolean pivotActive;
    protected double minAngle; // Set per arm
    protected double maxAngle; // Set per arm

    private final Motors extensionMotor;
    private final PID extensionPID;
    private double desiredExtension;
    private double extensionLimit;
    protected double minExtension; // Set per arm
    protected double maxExtension; // Set per arm

    protected double upperMechanismLength; // Set per arm
    protected double lowerMechanismLength; // Set per arm

    public enum armType {
        claw, grabber;
    }

    public ArmSubsystem(MotorInfo pivotID, MotorInfo extensionID, encoderType encoder, boolean inverted, double[] m_pivotPID, double[] m_extensionPID) {

        pivotMotor = new Motors(pivotID, encoder, inverted); // Both pivot motors have a through bore encoder on it. One, both or none could be inverted.
        extensionMotor = new Motors(extensionID);
        pivotPID = new PID(getName(), m_pivotPID[0], m_pivotPID[1], m_pivotPID[2]); // Each arm will have seperate PID so we require it as the parameter.
        pivotPID.ContinuousInput(0, 360);
        extensionPID = new PID(getName() + "Extension", m_extensionPID[0], m_extensionPID[1], m_extensionPID[2]); // For part of the safety check. PID will only run on retracting the arm during rotation when above limit.

    }

    private double GetTrueLength() {

        /*  The true length of the arm is determined by the length of the mechanism and the arm. As the arm rotates, a different part is the outer most part.
            Because the ends of the mechanism is offset from the arm center, we require this code, in order to prevent fouls.  */

        if (pivotMotor.motor.getAbsoluteAngle() > 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.getPosition(), 2) + Math.pow(lowerMechanismLength, 2)));
        } else if (pivotMotor.motor.getAbsoluteAngle() < 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.getPosition(), 2) + Math.pow(upperMechanismLength, 2)));
        } else {
            return extensionMotor.motor.getPosition() + lowerMechanismLength;
        }

    }

    private double GetExtensionLimit() {

        /*  Because the pivot point, the point we can't exceed and the end of the mechanism are three points that make a triangle, we use trigonometry.  */

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

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public void SetDesiredExtension(double extension) {

        desiredExtension = extension;

    }

    public double GetCurrentAngle() {
        
        return pivotMotor.motor.getAbsoluteAngle();

    }

    public double GetCurrentExtension() {

        return extensionMotor.motor.getPosition();

    }
    
    public double GetDesiredExtension() {

        return desiredExtension;

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && pivotMotor.GetVelocity() < 100;

    }

    public void ActivatePivot() {

        /*  This is needed as a safe measure so that our arm doesn't break itself.  */

        pivotActive = true;

    }

    public void Pivot() {

        // if (!pivotActive) return;

        // if (GetCurrentAngle() <= minAngle || GetCurrentAngle() >= maxAngle) {
        //     pivotMotor.Spin(0);
        //     return;
        // }

        double pidVal = pivotPID.GetPIDValue(GetCurrentAngle(), desiredAngle);
        pivotMotor.Spin(pidVal);
        
    }

    public void PivotStop() {

        pivotActive = false;
        pivotMotor.Spin(0);

    }

    public void Move(double speed) {

        // if (GetTrueLength() >= GetExtensionLimit()) {
        //     extensionMotor.Spin(-0.05);
        //     return;
        // } else if (GetTrueLength() <= minExtension) {
        //     extensionMotor.Spin(0.05);
        //     return;
        // }

        if (extensionMotor.motor.getPosition() < desiredExtension - 0.5) {
            extensionMotor.Spin(Math.abs(speed)); 
        } else if (extensionMotor.motor.getPosition() > desiredExtension + 0.5) {
            extensionMotor.Spin(-Math.abs(speed));
        } else {
            ExtensionStop();
        }

    }

    public void ExtensionStop() {

        extensionMotor.Spin(0);

    }

    public void DisplayDebuggingInfo() {

        Shuffleboard.getTab(getName()).addDouble("desired extension", () -> desiredExtension);
        Shuffleboard.getTab(getName()).addDouble("current extension", () -> extensionMotor.motor.getPosition());
        Shuffleboard.getTab(getName()).addDouble("desired angle", () -> desiredAngle);
        Shuffleboard.getTab(getName()).addDouble("current angle", () -> pivotMotor.motor.getAbsoluteAngle());
        Shuffleboard.getTab(getName()).addBoolean("reached extension", () -> Math.abs(GetCurrentExtension() - GetDesiredExtension()) < 3);
        Shuffleboard.getTab(getName()).addBoolean("reached angle", () -> GetIsReady());

    }

}
