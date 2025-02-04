package frc.robot.commands.GrabberArmCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.GrabberArmSubsystem;
import frc.robot.utils.SubsystemList;

public class GrabberArmPivotMoveCommand extends Command {

    final GrabberArmSubsystem grabberArm;

    public GrabberArmPivotMoveCommand(SubsystemList subsystems) {

        grabberArm = (GrabberArmSubsystem) subsystems.getSubsystem("grabberArm");

        addRequirements(grabberArm);

    }

    @Override
    public void initialize() {

        grabberArm.ActivatePivot();
        grabberArm.Pivot();

    }

    @Override
    public void end(boolean interrupted) {

        grabberArm.PivotStop();
        grabberArm.ExtensionStop();

    }
    
}
