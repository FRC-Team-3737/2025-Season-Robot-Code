package frc.robot.subsystems;

import frc.robot.subsystems.ArmSubsystem;

import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

@SuppressWarnings("unused")
public class GrabberArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {1/360, 1/360, 1/360};
    private static final double[] extensionPID = {1/360, 1/360, 1/360};

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
