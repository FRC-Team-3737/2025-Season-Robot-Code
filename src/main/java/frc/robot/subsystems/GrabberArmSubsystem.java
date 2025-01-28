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

        minAngle = 0; // Resting position
        maxAngle = 160; // Max rotation we need

        minExtension = 0.25; // Prevents slamming
        maxExtension = 100000; // No limit currently

        upperMechanismLength = 1; // Unknown length
        lowerMechanismLength = 1; // Unknown length

    }
    
}
