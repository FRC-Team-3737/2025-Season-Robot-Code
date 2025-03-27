package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utils.VectorR;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.SubsystemList;

public class TeleopMoveCommand extends Command {
    
    final DriveSubsystem drive;
    final XboxController controller;

    public TeleopMoveCommand(SubsystemList subsystems, XboxController m_controller) {

        drive = (DriveSubsystem) subsystems.getSubsystem("drive");
        controller = m_controller;

        addRequirements(drive);

    }

    @Override
    public void execute() {

        VectorR leftJoystick = VectorR.fromCartesian(controller.getLeftX()*0.5, controller.getLeftY()*0.5);
        double turnSpeed = 0;

        if (controller.getRightTriggerAxis() > 0.1) {
            turnSpeed += controller.getRightTriggerAxis() / 2;
        }

        if (controller.getLeftTriggerAxis() > 0.1) {
            turnSpeed -= controller.getLeftTriggerAxis() / 2;
        }

        if (controller.getRightBumperButton()) {
            leftJoystick.add(drive.getRightStrafe(0.1));
        }

        if (controller.getLeftBumperButton()) {
            leftJoystick.add(drive.getLeftStrafe(0.1));
        }

        drive.move(leftJoystick, turnSpeed, controller.getAButton(), controller.getStartButton());
        
    }

}
