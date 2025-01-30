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
    public static final int BackRightDriveID = 6;
    public static final int BackRightSteerID = 7;
    public static final int BackLeftDriveID = 8;
    public static final int BackLeftSteerID = 9;
    public static final int FrontRightDriveID = 2;
    public static final int FrontRightSteerID = 3;
    public static final int FrontLeftDriveID = 4;
    public static final int FrontLeftSteerID = 5;
    public static final int GrabberID = 11;
    public static final int ClawArmExtID = 10;
    public static final int ClawArmID = 9;
    public static final int GrabberArmExtID = 14;
    public static final int GrabberArmID = 12;
  public static final int ClimbID = 10;
  public static final int ClawMotorID = 10;
  public static final int ClawPivotMotorID = 10;

    // Swerve
    public static final SwerveModuleInfo FrontRightSwerve = new SwerveModuleInfo(FrontRightDriveID, FrontRightSteerID, controllerType.MAX, 3.371094, 360, 82.5, -1, -1);
    public static final SwerveModuleInfo FrontLeftSwerve = new SwerveModuleInfo(FrontLeftDriveID, FrontLeftSteerID, controllerType.MAX, 3.410156, 360, 113, -1, 1);
    public static final SwerveModuleInfo BackRightSwerve = new SwerveModuleInfo(BackRightDriveID, BackRightSteerID, controllerType.MAX, 3.433594, 360, 88.25, 1, -1);
    public static final SwerveModuleInfo BackLeftSwerve = new SwerveModuleInfo(BackLeftDriveID, BackLeftSteerID, controllerType.MAX, 3.496094, 360, 98.5, 1, 1);

  public static final MonitorInfo CLIMB = new MonitorInfo(ClimbID, controllerType.MAX, 0, 0);
    // Robot
    public static final MotorInfo Grabber = new MotorInfo(GrabberID, controllerType.MAX);
    public static final MotorInfo CLAW_ARM = new MotorInfo(ClawArmID, controllerType.MAX, 0, 0);
    public static final MotorInfo CLAW_ARM_EXT = new MotorInfo(ClawArmExtID, controllerType.MAX, 0, 0);
    public static final MotorInfo GRABBER_ARM = new MotorInfo(GrabberArmID, controllerType.MAX, 0, 0);
    public static final MotorInfo GRABBER_ARM_EXT = new MotorInfo(GrabberArmExtID, controllerType.MAX, 0, 0);
  public static final MotorInfo CLAW_PIVOT_MOTOR = new MotorInfo(ClawPivotMotorID, controllerType.MAX, 0, 0);
  public static final MotorInfo CLAW_MOTOR = new MotorInfo(ClawMotorID, controllerType.MAX, 0, 0);

    public static final class Swerve {

        public static final Translation2d flModuleOffset = new Translation2d(0.28, 0.28);
        public static final Translation2d frModuleOffset = new Translation2d(0.28, -0.28);
        public static final Translation2d blModuleOffset = new Translation2d(-0.28, 0.28);
        public static final Translation2d brModuleOffset = new Translation2d(-0.28, -0.28);

        public static final double maxModuleSpeed = 4.5; // M/S

    }

}
