package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.SubsystemList;
import frc.robot.Constants.subsystemType;
import frc.robot.subsystems.ClawSubsystem;

public class ClawCloseCommand extends Command {
    final ClawSubsystem claw;
    private final double motorSpeed;

    public ClawCloseCommand(SubsystemList subsystems, double speed) {

        claw = (ClawSubsystem) subsystems.getSubsystem(subsystemType.CLAW.name());
        motorSpeed = speed;

        addRequirements(claw);

    }

    @Override
    public void execute() {

        claw.Close(motorSpeed);

    }

    @Override
    public boolean isFinished() {

        return claw.GetClawPosition() <= 0.25;
        
    }

    @Override 
    public void end(boolean interrupted) {

        claw.ClawStop();

    }
}