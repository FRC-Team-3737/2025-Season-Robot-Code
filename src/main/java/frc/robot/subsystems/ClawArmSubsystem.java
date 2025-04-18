package frc.robot.subsystems;

import frc.robot.motor.Motor.encoderType;

import frc.robot.Constants;

public class ClawArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {0.4, 0.1, 0}; // kP, kI, kD
    private static final double[] pivotFeedforward = {0, 0.0765, 1}; // kS, kG, kV
    private static final double[] extensionPID = {1/360, 1/360, 1/3600};

    /**
     * Sets the claw arm up with all the safety variables necessary.
     */
    public ClawArmSubsystem() {

        super(Constants.ClawArm, Constants.ClawArmExt, encoderType.Absolute, false, pivotPID, pivotFeedforward, extensionPID);
        setName("clawArm");

        /*  Following values in degrees and inches  */

        minAngle = 44; // Resting position
        maxAngle = 210; // Max rotation needed
        pivotDirection = 1; // Which direction the arm moves
        maxSpeedClamp = 0.35; // Max speed of pivot
        minSpeedClamp = 0.05; // Min speed of pivot

        minExtension = 0; // Prevents slamming
        maxExtension = 23.5; // Prevents overextending

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
