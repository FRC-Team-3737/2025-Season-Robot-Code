package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.routines.LeaveAutoRoutine;
import frc.robot.auto.routines.TestAutoRoutine;

public class AutoPicker {
    
    private final SendableChooser<Command> sendableChooser = new SendableChooser<>();
    private final SendableChooser<AutoDivider> categoryChooser = new SendableChooser<>();

    public AutoPicker(SubsystemList subsystems) {

        sendableChooser.setDefaultOption("Choose Auto", null);
        categoryChooser.setDefaultOption("Choose Category", null);

        categoryChooser.addOption("Cage", new AutoDivider( 
            new TestAutoRoutine(subsystems)
        ));
        categoryChooser.addOption("Center", new AutoDivider(
            new LeaveAutoRoutine(subsystems),
            new TestAutoRoutine(subsystems)
        ));
        categoryChooser.addOption("Processor", new AutoDivider( 
            new TestAutoRoutine(subsystems)
        ));
        categoryChooser.addOption("Alternate", new AutoDivider( 
            new TestAutoRoutine(subsystems)
        ));

        SmartDashboard.putData(categoryChooser);
        SmartDashboard.putData(sendableChooser);

        SetAutoRoutines();

    }

    public Command GetAuto() {

        return sendableChooser.getSelected();

    }

    private void SetAutoRoutines() {

        for () {
            if (routineGroup == categoryChooser.getSelected()) {
                routineGroup.populateCommands(sendableChooser);
            }   
        }
    }

}
