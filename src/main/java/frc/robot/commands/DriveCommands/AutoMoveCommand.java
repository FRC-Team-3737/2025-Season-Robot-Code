package frc.robot.commands.DriveCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.MathUtil;

import frc.robot.utils.VectorR;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.SubsystemList;

public class AutoMoveCommand extends Command {
    
    private final DriveSubsystem drive;
    private VectorR moveSpeed;
    private final PIDController turnPID;
    private double position;
    private double desiredPosition; 
    private double startPosition;
    private double angle;
    private double magnitude;
    private double turnSpeed;
    private double desiredAngle;

    /**
     * The move command for auto so the robot can move autonomously without a controller input by using a fake controller.
     * 
     * @param m_distance The distance it should drive in feet
     * @param m_angle The angle of the joystick; 270 is forward
     * @param m_magnitude The speed of the drive
     * @param m_turnSpeed The speed of the turn
     * @param m_desiredAngle The angle of the robot; 0 is forward
     */
    public AutoMoveCommand(SubsystemList subsystems, double m_distance, double m_angle, double m_magnitude, double m_turnSpeed, double m_desiredAngle) {

        drive = (DriveSubsystem) subsystems.getSubsystem("drive");
        turnPID = new PIDController(0.015, 0, 0);
        turnPID.enableContinuousInput(0, 360);
        
        moveSpeed = new VectorR();
        desiredPosition = m_distance * 100; // 100 is a placeholder conversion (feet -> encoder value)
        angle = m_angle;
        magnitude = m_magnitude;
        turnSpeed = m_turnSpeed;
        desiredAngle = m_desiredAngle;

        addRequirements(drive);

    }

    @Override
    public void initialize() {

        moveSpeed.setFromPolar(magnitude, angle);
        startPosition = drivePosition();

    }

    @Override
    public void execute() {

        if (Math.abs(drive.GetGyro() - desiredAngle) > 0.5) {
            turnSpeed = MathUtil.clamp(turnPID.calculate(MathUtil.inputModulus(drive.GetGyro(), 0, 360), MathUtil.inputModulus(desiredAngle + 180, 0, 360)), -0.25, 0.25);
        } else {
            turnSpeed = 0;
        }
        
        drive.move(moveSpeed, turnSpeed, false, false);

        // One revolution is 0.5116 inches
        // One full rotation is 49.52 revolutions
        position = Math.abs(drivePosition() - startPosition);

    }

    @Override
    public boolean isFinished() {

        if (position >= desiredPosition) {
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public void end(boolean interrupted) {

        drive.stop();

    }

    private double drivePosition() {

        return drive.modules.frontRight.driveMotor.getEncoder().getPosition();

    }

}
