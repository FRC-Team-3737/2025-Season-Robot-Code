package frc.robot.commands.ClimbCommands;

import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbRotateCommand extends Command {

    final ClimbSubsystem climb;
    double desiredAngle;
    double deadzone;
    
    public ClimbRotateCommand(SubsystemList subsystems, double angle, double tolerance) {

        climb = (ClimbSubsystem) subsystems.getSubsystem("climb");
        desiredAngle = angle;
        deadzone = tolerance;

        addRequirements(climb);

    }

    @Override
    public void initialize() {

        climb.SetDesiredAngle(desiredAngle);
        climb.SetTolerance(deadzone);
        climb.ActivateRotation();

    }

    @Override
    public void execute() {

        climb.Pivot();

    }

    @Override
    public boolean isFinished() {

        return climb.GetIsReady();

    }

    @Override
    public void end(boolean interrupted) {

        climb.Stop();

    }

}
