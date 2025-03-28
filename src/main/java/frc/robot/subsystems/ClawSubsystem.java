package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.utils.PID;

public class ClawSubsystem extends SubsystemBase {

    private final Motors wristMotor;
    private final PIDController wristPID;
    private final ArmFeedforward wristFeedforward;
    private final Motors clawMotor;
    private final DigitalInput microswitch;
    private double tolerance;
    private double desiredAngle;
    private boolean rotationActive;
    private double minSpeedClamp = 0.03;
    private double maxSpeedClamp = 0.30;
    
    public ClawSubsystem() {

        setName("claw");

        wristMotor = new Motors(Constants.Wrist, encoderType.Analog, true);
        clawMotor = new Motors(Constants.Claw);
        wristPID = new PIDController(0.4, 0.015, 0);
        wristFeedforward = new ArmFeedforward(0, -0.01, 1);

        microswitch = new DigitalInput(Constants.BucketSwitch);

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

    public Boolean GetCoralIn() {

        return !microswitch.get();

    }

    public Boolean GetCoralOut() {

        return microswitch.get();

    }

    public double GetClawPosition() {

        return clawMotor.motor.getPosition(false);

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance) && wristMotor.GetVelocity() < 1000 && wristPID.atSetpoint();

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot() {

        double radianConversion = 3.14159/180;

        if (!rotationActive) return;

        double minAngle = 5;
        double maxAngle = 175;

         if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
             WristStop();
             return;
         }

        double pidVal = wristPID.calculate(GetCurrentAngle()*radianConversion, desiredAngle*radianConversion);

        double pid = Math.signum(pidVal)*MathUtil.clamp(Math.abs(pidVal), minSpeedClamp, maxSpeedClamp);
        double feedforward = wristFeedforward.calculate((desiredAngle-90)*radianConversion, 0.05*((desiredAngle-GetCurrentAngle())*radianConversion));
        wristMotor.Spin(pid + feedforward);

    }

    public void Hold() {

        double radianConversion = 3.14159/180;

        if (!rotationActive) return;

        if (Math.abs(desiredAngle-GetCurrentAngle()) >= 0.5) {
            wristMotor.Spin(0.15*(desiredAngle-GetCurrentAngle()));
            return;
        }

        double feedforward = wristFeedforward.calculate((desiredAngle-90)*radianConversion, 0.3*((desiredAngle-GetCurrentAngle())*radianConversion));

        wristMotor.Spin(feedforward);

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

        Shuffleboard.getTab(getName()).addDouble("desired angle", () -> desiredAngle);
        Shuffleboard.getTab(getName()).addDouble("current angle", () -> GetCurrentAngle());

    }

}
