package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.utils.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {

    private final double maxRotationalSpeed = -0.035 * Math.PI;
    private final double maxSpeed = -0.3;

    public LimelightSubsystem() {

        setName("limelight");

    }

    /** 
     * Returns the angular power needed to center itself onto the target
     * 
     * @see https://github.com/LimelightVision/limelight-examples/blob/28cb4c8f9b68cea62bef010ab793960f8d2b7a53/java-wpilib/swerve-aim-and-range/src/main/java/frc/robot/Robot.java#L36
     */

    public double getTargetSpin() {

        return LimelightHelpers.getTX("limelight") * maxRotationalSpeed;

    }

    /** 
     * Returns the directional power needed to keep a set distance from the target
     * 
     * @see https://github.com/LimelightVision/limelight-examples/blob/28cb4c8f9b68cea62bef010ab793960f8d2b7a53/java-wpilib/swerve-aim-and-range/src/main/java/frc/robot/Robot.java#L61
     */
    
    public double getTargetDistance() {

        return LimelightHelpers.getTY("limelight") * maxSpeed;

    }
}
