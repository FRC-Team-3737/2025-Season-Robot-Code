package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.routines.L4AutoRoutine;
import frc.robot.auto.routines.LeaveAutoRoutine;
import frc.robot.auto.routines.TestAutoRoutine;

public class AutoPicker {
    
    private final SendableChooser<Command> sendableChooser = new SendableChooser<>();

    public enum AutoSide {
        Cage, Center, Processor
    }

    public AutoPicker(SubsystemList subsystems) {

        sendableChooser.setDefaultOption("Choose Auto", null);
        Command[] autoRoutines = {
            new L4AutoRoutine(subsystems, AutoSide.Center),
            new LeaveAutoRoutine(subsystems),
            new TestAutoRoutine(subsystems)
        };

        this.SetAutoRoutines(autoRoutines);

        SmartDashboard.putData(sendableChooser);

    }

    public Command GetAuto() {

        return sendableChooser.getSelected();

    }

    private void SetAutoRoutines(Command[] routines) {
        for (Command routine : routines) {
            String name = routine.getName();
            sendableChooser.addOption(name, routine);
        }
    }

}
