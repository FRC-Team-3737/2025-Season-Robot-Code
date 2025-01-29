package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmPivotStopCommand extends Command {

    final GrabberArmSubsystem grabberArm;

    public GrabberArmPivotStopCommand(SubsystemList subsystems) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.PivotStop();

    }

    @Override
    public boolean isFinished() {

        return true;

    }
    
}
