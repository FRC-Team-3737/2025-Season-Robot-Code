package frc.robot.motor;

import frc.robot.Constants.controllerType;

public class MotorInfo {
    
    public final int ID;
    public final controllerType Controller;
    public final double MaxEncoderValue;
    public final double ReferenceAngle;
    // public final double ConversionFactor;

    /**
     * Sets up the MotorInfo for a motor to be used later in the code for declaring a spark controller.
     * 
     * @param MotorID The ID of the motor controller
     * @param Controller The controller type
     * @param MaxEncoderValue The max value for analog encoders
     * @param ReferenceAngle The angle that the arm is actually at
     */
    public MotorInfo(int MotorID, controllerType Controller, double MaxEncoderValue, double ReferenceAngle) {
        
        this.ID = MotorID;
        this.Controller = Controller;
        this.MaxEncoderValue = MaxEncoderValue;
        this.ReferenceAngle = ReferenceAngle;
        
    }

    /**
     * Sets up the MotorInfo for a motor to be used later in the code for declaring a spark controller.
     * 
     * @param MotorID The ID of the motor controller
     * @param Controller The controller type
     * @param ReferenceAngle The angle that the arm is actually at
     */
    public MotorInfo(int MotorID, controllerType Controller, double ReferenceAngle) {
        
        this.ID = MotorID;
        this.Controller = Controller;
        this.MaxEncoderValue = 0;
        this.ReferenceAngle = ReferenceAngle;
        
    }

    /**
     * Sets up the MotorInfo for a motor to be used later in the code for declaring a spark controller.
     * 
     * @param MotorID The ID of the motor controller
     * @param Controller The controller type
     */
    public MotorInfo(int MotorID, controllerType Controller) {
        
        this.ID = MotorID;
        this.Controller = Controller;
        this.MaxEncoderValue = 0;
        this.ReferenceAngle = 0;
        
    }

}
