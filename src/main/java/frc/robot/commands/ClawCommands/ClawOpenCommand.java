package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.subsystems.ClawSubsystem;

public class ClawOpenCommand extends Command {
    final ClawSubsystem claw;
    private final double motorSpeed;

    public ClawOpenCommand(SubsystemList subsystems, double speed) {

        claw = (ClawSubsystem) subsystems.getSubsystem("claw");
        motorSpeed = speed;

        addRequirements(claw);

    }

    @Override
    public void initialize() {

        claw.ClawOpen(motorSpeed);

    }

    @Override
    public boolean isFinished() {

        return claw.GetClawPosition() >= 500;
        
    }

    @Override 
    public void end(boolean interrupted) {

        claw.ClawStop();

    }
}
