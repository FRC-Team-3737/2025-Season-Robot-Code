package frc.robot.subsystems;

import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

public class GrabberArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {0.625, 0.1, 0}; // kP, kI, kD
    private static final double[] pivotFeedforward = {0, -0.0725, 1}; // kS, kG, kV
    private static final double[] extensionPID = {1/360, 1/360, 1/3600};

    /**
     * Sets the grabber arm up with all the safety variables necessary.
     */
    public GrabberArmSubsystem() {

        super(Constants.GrabberArm, Constants.GrabberArmExt, encoderType.Absolute, true, pivotPID, pivotFeedforward, extensionPID);
        setName("grabberArm");

        /*  Following values in degrees and inches  */

        minAngle = 45; // Resting position | 135 degrees from vertical
        maxAngle = 180; // Max rotation needed | 0 degrees from vertical
        pivotDirection = -1; // Which direction the arm moves
        maxSpeedClamp = 1; // Max speed of the pivot
        minSpeedClamp = 0.05; // Min speed of the pivot

        minExtension = 0.25; // Prevents slamming
        maxExtension = 6.5*6.6; // Prevents overextending

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
