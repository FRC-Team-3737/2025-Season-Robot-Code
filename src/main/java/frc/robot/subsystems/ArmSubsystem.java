package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.LayoutType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.MotorInfo;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ArmSubsystem extends SubsystemBase {

    private final Motors pivotMotor;
    private final PIDController pivotPID;
    private final ArmFeedforward pivotFeedforward;
    private double desiredAngle;
    private double tolerance;
    private boolean pivotActive;
    protected double minAngle; // Set per arm
    protected double maxAngle; // Set per arm
    protected double pivotDirection; // Set per arm
    protected double maxSpeedClamp; // Set per arm
    protected double minSpeedClamp; // Set per arm

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

    private double stopIncrement = 0;

    /** 
     * Set the motors and PIDs for the arm.
     * 
     * @param pivotID The ID of the pivot motor
     * @param extensionID The ID of the extension motor
     * @param encoder The encoder type used
     * @param inverted Whether the encoder is inverted
     * @param m_pivotPID The PID values for the pivot
     * @param m_pivotFeedforward The feedforward values for the pivot
     * @param m_extensionPID The PID values for the extension
     */
    public ArmSubsystem(MotorInfo pivotID, MotorInfo extensionID, encoderType encoder, boolean inverted, double[] m_pivotPID, double[] m_pivotFeedforward, double[] m_extensionPID) {

        pivotMotor = new Motors(pivotID, encoder, inverted); // Both pivot motors have a through bore encoder on it. One, both or none could be inverted.
        extensionMotor = new Motors(extensionID);
        //pivotPID = new PID(getName(), m_pivotPID[0], m_pivotPID[1], m_pivotPID[2]); // Each arm will have seperate PID so we require it as the parameter.
        pivotPID = new PIDController(m_pivotPID[0], m_pivotPID[1], m_pivotPID[2]);
        pivotFeedforward = new ArmFeedforward(m_pivotFeedforward[0], m_pivotFeedforward[1], m_pivotFeedforward[2]);
        extensionPID = new PID(getName() + "Extension", m_extensionPID[0], m_extensionPID[1], m_extensionPID[2]); // For part of the safety check. PID will only run on retracting the arm during rotation when above limit.

    }

    /**
     * Gets the true length of the arm and mechanism relative to its rotation.
     * 
     * @return The true length of the arm and mechanism
     */
    private double GetTrueLength() {

        /*  The true length of the arm is determined by the length of the mechanism and the arm. As the arm rotates, a different part is the outer most part.
            Because the ends of the mechanism is offset from the arm center, we require this code, in order to prevent fouls.  */

        if (pivotMotor.motor.getAbsoluteAngle() > 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.getPosition(true), 2) + Math.pow(lowerMechanismLength, 2)));
        } else if (pivotMotor.motor.getAbsoluteAngle() < 90) {
            return (Math.sqrt(Math.pow(extensionMotor.motor.getPosition(true), 2) + Math.pow(upperMechanismLength, 2)));
        } else {
            return extensionMotor.motor.getPosition(true) + lowerMechanismLength;
        }

    }

    /**
     * Gets the limit of the extension via the angle and the max limit we can extend.
     * 
     * @return The extension limit
     */
    private double GetExtensionLimit() {

        /*  Because the pivot point, the point we can't exceed and the end of the mechanism are three points that make a triangle, we use trigonometry.  */

        // The entire system is currently represented in inches, not the value of the extension motor.

        double a = Math.tan(pivotMotor.motor.getAbsoluteAngle()) * 18;

        if (GetCurrentAngle() < 30) {
            extensionLimit = 0;
        } else if (GetCurrentAngle() < 135) {
            extensionLimit = Math.sqrt(Math.pow(a, 2) + Math.pow(18*6.6, 2));
        } else {
            extensionLimit = maxExtension;
        }
        
        return extensionLimit;

    }

    /**
     * Sets the tolerance of the pivot around it's desired angle.
     * 
     * @param m_tolerance The tolerance in degrees
     */
    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;
        pivotPID.setTolerance(m_tolerance);

    }

    /**
     * Sets the target angle for the arm.
     *
     * @param angle The target angle for the arm in degrees
     */
    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    /**
     * Sets the target extension for the arm.
     * 
     * @param extension The target extension for the arm in inches
     */    
    public void SetDesiredExtension(double extension) {

        desiredExtension = extension;

    }

    /**
     * Gets the current angle of the pivot in degrees.
     * 
     * @return The current angle in degrees
     */
    public double GetCurrentAngle() {
        
        return pivotMotor.motor.getAbsoluteAngle();

    }

    /**
     * Gets the current extension of the arm.
     * 
     * @return The current extension in inches
     */
    public double GetCurrentExtension() {

        return extensionMotor.motor.getPosition(false);

    }
    
    /**
     * Gets the set desired extension of the arm.
     *
     * @return The desired extension in inches
     */
    public double GetDesiredExtension() {

        return desiredExtension;

    }

    /**
     * Gets if the pivot is in a deadzone where based on a set tolerance. Also checks if the speed is slow enough to stop it.
     * 
     * @return Whether the arm meets the conditions
     */
    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && pivotMotor.GetVelocity() < 1000 && pivotPID.atSetpoint();

    }

    /**
     * Activates the pivot in order for it to move. Is a safe measure to make sure it doesn't move when not supposed to.
     */
    public void ActivatePivot() {

        /*  This is needed as a safe measure so that our arm doesn't break itself.  */

        pivotActive = true;

    }

    /**
     * Pivots the arm to the set desired angle.
     */
    public void Pivot() {

        double radianConversion = 3.14159/180;

        if (!pivotActive) return;

        if (GetCurrentAngle() <= minAngle) {
            pivotMotor.Spin(-0.05*pivotDirection);
        }else if (GetCurrentAngle() >= maxAngle) {
            pivotMotor.Spin(0.05*pivotDirection);
        }

        double pidVal = pivotPID.calculate(GetCurrentAngle()*radianConversion, desiredAngle*radianConversion);

        double pid = Math.signum(pidVal)*MathUtil.clamp(Math.abs(pidVal), minSpeedClamp, maxSpeedClamp);
        double feedforward = pivotFeedforward.calculate((desiredAngle-90)*radianConversion, 0.1*((desiredAngle-GetCurrentAngle())*radianConversion));
        pivotMotor.Spin(pid*pivotDirection + feedforward);
        
    }

    public void Hold() {

        double radianConversion = 3.14159/180;

        if (!pivotActive) return;

        double feedforward = pivotFeedforward.calculate((desiredAngle-90)*radianConversion, 0.5*((desiredAngle-GetCurrentAngle())*radianConversion)*pivotDirection);

        pivotMotor.Spin(feedforward);

    }

    /**
     * Stops the pivot and deactivates it.
     */
    public void PivotStop() {

        stopIncrement++;
        pivotActive = false;
        pivotPID.reset();
        pivotMotor.Spin(0);

    }

    /**
     * Extends the arm to its set desired position.
     * 
     * @param speed The speed the arm will move at
     */
    public void Move(double speed) {

        // if (GetTrueLength() >= GetExtensionLimit()) {
        //     extensionMotor.Spin(-0.05);
        //     return;
        // } else if (GetTrueLength() <= minExtension) {
        //     extensionMotor.Spin(0.05);
        //     return;
        // }

        if (extensionMotor.motor.getPosition(false) < desiredExtension - 1.5) {
            extensionMotor.Spin(Math.abs(speed)); 
        } else if (extensionMotor.motor.getPosition(false) > desiredExtension + 1.5) {
            extensionMotor.Spin(-Math.abs(speed));
        } else {
            ExtensionStop();
        }

        // else if (extensionMotor.motor.getPosition(false) < desiredExtension - 0.25) {
        //     extensionMotor.Spin(Math.abs(speed/2)); 
        // } else if (extensionMotor.motor.getPosition(false) > desiredExtension + 0.25) {
        //     extensionMotor.Spin(-Math.abs(speed/2));
        // }

    }

    /**
     * Stops arm extension.
     */
    public void ExtensionStop() {

        extensionMotor.Spin(0);

    }

    /**
     * Displays variable values for debugging.
     */
    public void DisplayDebuggingInfo() {

        ShuffleboardTab tab = Shuffleboard.getTab(getName());
        tab.addDouble("desired extension", () -> desiredExtension);
        tab.addDouble("current extension", () -> extensionMotor.motor.getPosition(false));
        tab.addDouble("desired angle", () -> desiredAngle);
        tab.addDouble("current angle", () -> pivotMotor.motor.getAbsoluteAngle());
        tab.addBoolean("reached extension", () -> Math.abs(GetCurrentExtension() - GetDesiredExtension()) < 3);
        tab.addBoolean("reached angle", () -> GetIsReady());
        tab.addDouble("stop increment", () -> stopIncrement);

    }

}
