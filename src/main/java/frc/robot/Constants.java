// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.motor.MotorInfo;
import frc.robot.swervemodule.SwerveModuleInfo;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public enum controllerType {

        MAX, FLEX;

    }

    public static final double ModuleAngleKP = -0.00524;

    // Controller Port
    public static final int DriveControllerPort = 0;
    public static final int OperatorControllerPort = 1;
    public static final int ButtonBoardPort = 2;

    // Motor ID
    public static final int BackRightDriveID = 8;
    public static final int BackRightSteerID = 9;
    public static final int BackLeftDriveID = 4;
    public static final int BackLeftSteerID = 5;
    public static final int FrontRightDriveID = 2;
    public static final int FrontRightSteerID = 3;
    public static final int FrontLeftDriveID = 6;
    public static final int FrontLeftSteerID = 7;
    public static final int GrabberID = 10;
    public static final int ClawArmExtID = 14;
    public static final int ClawArmID = 15;
    public static final int GrabberArmExtID = 17;
    public static final int GrabberArmID = 16;
    public static final int ClimbID = 11;
    public static final int ClawPivotMotorID = 12;
    public static final int ClawMotorID = 13;

    // Swerve
    public static final SwerveModuleInfo FrontRightSwerve = new SwerveModuleInfo(FrontRightDriveID, FrontRightSteerID, controllerType.FLEX, 3.359589, 360, 34.25, -1, -1);
    public static final SwerveModuleInfo FrontLeftSwerve = new SwerveModuleInfo(FrontLeftDriveID, FrontLeftSteerID, controllerType.FLEX, 3.403666, 360, 220.25, -1, 1);
    public static final SwerveModuleInfo BackRightSwerve = new SwerveModuleInfo(BackRightDriveID, BackRightSteerID, controllerType.FLEX, 3.344897, 360,42.75, 1, -1);
    public static final SwerveModuleInfo BackLeftSwerve = new SwerveModuleInfo(BackLeftDriveID, BackLeftSteerID, controllerType.FLEX, 3.428153, 360, 151.5, 1, 1);

    // Robot
    public static final MotorInfo Grabber = new MotorInfo(GrabberID, controllerType.FLEX);
    public static final MotorInfo Climb = new MotorInfo(ClimbID, controllerType.FLEX);
    public static final MotorInfo ClawArm = new MotorInfo(ClawArmID, controllerType.MAX);
    public static final MotorInfo ClawArmExt = new MotorInfo(ClawArmExtID, controllerType.MAX);
    public static final MotorInfo GrabberArm = new MotorInfo(GrabberArmID, controllerType.MAX);
    public static final MotorInfo GrabberArmExt = new MotorInfo(GrabberArmExtID, controllerType.MAX);
    public static final MotorInfo Wrist = new MotorInfo(ClawPivotMotorID, controllerType.MAX, 3.325308, 0);
    public static final MotorInfo Claw = new MotorInfo(ClawMotorID, controllerType.MAX);

    public static final class Swerve {

        public static final Translation2d flModuleOffset = new Translation2d(0.28, 0.28);
        public static final Translation2d frModuleOffset = new Translation2d(0.28, -0.28);
        public static final Translation2d blModuleOffset = new Translation2d(-0.28, 0.28);
        public static final Translation2d brModuleOffset = new Translation2d(-0.28, -0.28);

        public static final double maxModuleSpeed = 4.5; // M/S

    }

}
