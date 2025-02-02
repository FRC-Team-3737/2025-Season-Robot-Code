package frc.robot.swervemodule;

import frc.robot.utils.VectorR;
import frc.robot.Constants.controllerType;

public class SwerveModuleInfo {
    
    public final int DriveID;
    public final int SteerID;
    public final controllerType Controller;
    public final double MaxEncoderValue;
    public final double EncoderValueWhenStraight;
    public final double X;
    public final double Y;
    public final double ModuleTangentDegrees;
    public final double MaxEncoderVoltage;

    public SwerveModuleInfo(int driveID, int steerID, controllerType controller, double maxEncoderVoltage, double maxEncoderValue, double encoderValueWhenStraight, double x, double y) {
        
        DriveID = driveID;
        SteerID = steerID;
        Controller = controller;
        MaxEncoderVoltage = maxEncoderVoltage;
        MaxEncoderValue = maxEncoderValue;
        EncoderValueWhenStraight = encoderValueWhenStraight;
        X = x;
        Y = y;
        ModuleTangentDegrees = VectorR.fromCartesian(x, y).getAngle() - 90;

    }

}