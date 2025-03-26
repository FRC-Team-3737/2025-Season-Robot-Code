package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.PairedMotors;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

    private final PairedMotors motor;
    private boolean rotationActive;
    private double desiredAngle;
    private double tolerance;

    public ClimbSubsystem() {

        setName("climb");

        motor = new PairedMotors(Constants.Climb, Constants.ClimbSlave);
        rotationActive = false;

    }

    public void SetTolerance(double m_tolerance) {

        tolerance = m_tolerance;

    }

    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean GetIsReady() {

        return (GetCurrentAngle() > desiredAngle - tolerance && GetCurrentAngle() < desiredAngle + tolerance);

    }

    public double GetCurrentAngle() {

        return motor.mainMotor.getAbsoluteAngle();

    }

    public void ActivateRotation() {

        rotationActive = true;

    }

    public void Pivot(double speed) {

        // Only runs if the pivot is activated first, to prevent errors
        if (!rotationActive) return;

        double minAngle = -33;
        double maxAngle = 15;
        double motorSpeed;

        // Stops if past limits
        if (GetCurrentAngle() < minAngle || GetCurrentAngle() > maxAngle) {
            Stop();
            return;
        }

        // Feeds motor default value inputted
        if (motor.mainMotor.getPosition(false) < desiredAngle - 0.25) {
            motorSpeed = Math.abs(speed);
        } else if (motor.mainMotor.getPosition(false) > desiredAngle + 0.25){
            motorSpeed = -Math.abs(speed);
        } else {
            motorSpeed = 0;
            Stop();
        }

        // Holds a steady velocity so that the motor doesn't become jerky
        // if (Math.abs(motor.GetVelocity()) < 100) {
        //     if (motorSpeed > 0) {
        //         motorSpeed = motorSpeed + 0.01;
        //     } else if (motorSpeed < 0) {
        //         motorSpeed = motorSpeed - 0.01;
        //     }
        // }

        // Spins the motors
        motor.Spin(motorSpeed);

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }

    public void DisplayDebuggingInfo() {
        Shuffleboard.getTab(getName()).addNumber("Current Angle", () -> motor.mainMotor.getPosition(false));
        Shuffleboard.getTab(getName()).addNumber("Current Velocity", () -> motor.GetVelocity());
        Shuffleboard.getTab(getName()).addNumber("Current Speed", () -> motor.mainMotor.motor.get());
    }
    
}
