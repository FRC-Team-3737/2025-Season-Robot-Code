package frc.robot.subsystems;

import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

public class ClawArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {0, 0, 0}; // kP, kI, kD
    private static final double[] pivotFeedforward = {0, 0, 0}; // kS, kG, kV
    private static final double[] extensionPID = {1/360, 1/360, 1/3600};

    /**
     * Sets the claw arm up with all the safety variables necessary.
     */
    public ClawArmSubsystem() {

        super(Constants.ClawArm, Constants.ClawArmExt, encoderType.Absolute, false, pivotPID, pivotFeedforward, extensionPID);
        setName("clawArm");

        /*  Following values in degrees and inches  */

        minAngle = 0; // Resting position | 130.5 degrees from vertical
        maxAngle = 150.5; // Max rotation needed | -20 degrees from vertical
        pivotDirection = 1; // Which direction the arm moves

        minExtension = 0; // Prevents slamming 0.25 norm
        maxExtension = 23.5; // Prevents overextending 23.5 norm

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
