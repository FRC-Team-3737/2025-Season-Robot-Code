package frc.robot.subsystems;

import frc.robot.subsystems.ArmSubsystem;

import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

@SuppressWarnings("unused")
public class ClawArmSubsystem extends ArmSubsystem {

    private static final double[] pivotPID = {1/360, 1/360, 1/360};
    private static final double[] extensionPID = {1/360, 1/360, 1/360};

    public ClawArmSubsystem() {

        super(Constants.CLAW_ARM, Constants.CLAW_ARM_EXT, encoderType.Absolute, false, pivotPID, extensionPID);
        setName("clawArm");

        /*  Following values in degrees and inches  */

        minAngle = 0; // Resting position | 130.5 degrees from vertical
        maxAngle = 150.5; // Max rotation needed | -20 degrees from vertical

        minExtension = 0; // Prevents slamming 0.25 norm
        maxExtension = 100000; // Prevents overextending 23.5 norm

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
