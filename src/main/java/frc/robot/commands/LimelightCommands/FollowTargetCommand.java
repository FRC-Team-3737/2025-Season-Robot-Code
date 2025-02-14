package frc.robot.commands.LimelightCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utils.SubsystemList;
import frc.robot.utils.VectorR;

public class FollowTargetCommand extends Command {
    
    final DriveSubsystem drive;
    final LimelightSubsystem limelight;

    public FollowTargetCommand(SubsystemList subsystems) {

        drive = (DriveSubsystem) subsystems.getSubsystem("drive");
        limelight = (LimelightSubsystem) subsystems.getSubsystem("limelight");

        addRequirements(drive);

    }

    @Override
    public void execute() {

        // executeFollow();
        executeSpin();
        
    }

    public void executeSpin() {

        double turnSpeed = limelight.getTargetSpin();

        VectorR mockJoystick = VectorR.fromCartesian(0, 0);

        drive.move(mockJoystick, turnSpeed, false, false);
        
    }

    public void executeFollow() {

        double turnSpeed = limelight.getTargetSpin();
        double forwardSpeed = limelight.getTargetDistance();

        VectorR mockJoystick = VectorR.fromCartesian(0, forwardSpeed);

        drive.move(mockJoystick, turnSpeed, false, false);
        
    }

}
