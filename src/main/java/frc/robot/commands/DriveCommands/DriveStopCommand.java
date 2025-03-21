package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.SubsystemList;

public class DriveStopCommand extends Command {
    
    final DriveSubsystem drive;

    public DriveStopCommand(SubsystemList subsystems) {

        drive = (DriveSubsystem) subsystems.getSubsystem("drive");

        addRequirements(drive);

    }

    @Override
    public void initialize() {

        drive.stop();
        
    }

}