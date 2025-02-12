package frc.robot.subsystems;

import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

public class GrabberArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {1/360, 1/360, 1/3600};
    private static final double[] extensionPID = {1/360, 1/360, 1/3600};

    /**
     * Sets the grabber arm up with all the safety variables necessary.
     */
    public GrabberArmSubsystem() {

        super(Constants.GRABBER_ARM, Constants.GRABBER_ARM_EXT, encoderType.Absolute, false, pivotPID, extensionPID);
        setName("grabberArm");

        /*  Following values in degrees and inches  */

        minAngle = 0; // Resting position | 135 degrees from vertical
        maxAngle = 135; // Max rotation needed | 0 degrees from vertical

        minExtension = 0.25; // Prevents slamming
        maxExtension = 6.5; // Prevents overextending

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
