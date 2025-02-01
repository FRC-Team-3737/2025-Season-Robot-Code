package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class Motors {
    
    public final Motor motor;

    /*  The following is method overloading, allowing different inputs to be possible by using different parameters with a same name method.  */
    
    public Motors(MotorInfo motor, encoderType encoder, boolean inverted) {

        this.motor = new Motor(motor, encoder, inverted);

    }

    public Motors(MotorInfo motor) {

        this.motor = new Motor(motor, encoderType.None, false);

    }

    public void SetRampRate(double rate) {

        motor.motorConfig.closedLoopRampRate(rate);

    }

    public double GetTemperature() {

        return motor.motor.getMotorTemperature();

    }

    public double GetCurrent() {

        return motor.motor.getOutputCurrent();

    }

    public double GetVelocity() {

        return motor.motor.getEncoder().getVelocity();

    }

    public void Spin(double speed) {

        motor.motor.set(speed);

    }
    
    /** 
     * Grabs all the debugging information from the motor. 
     * 
     * <p>Keep all motor name inputs capitalized and spaced out for best visibility.
     * 
     * @param motorName name to be displayed on the dashboard.
     */
    public String[] GetDebuggingInformation(String motorName) {

        String[] info = {
            motorName + " ID: " + String.valueOf(motor.motor.getDeviceId()) + "\n", 
            motorName + " Motor Type: " + String.valueOf(motor.motor.getMotorType()) + "\n", 
            motorName + " Firmware Version: " + String.valueOf(motor.motor.getFirmwareVersion()) + "\n", 
            motorName + " Bus Voltage: " + String.valueOf(motor.motor.getBusVoltage()) + "\n", 
            motorName + " Absolute Encoder: " + String.valueOf(motor.motor.getAbsoluteEncoder()) + "\n", 
            motorName + " Analog Encoder: " + String.valueOf(motor.motor.getAnalog()) + "\n", 
            motorName + " Sticky Faults: " + String.valueOf(motor.motor.getStickyFaults()) + "\n", 
            motorName + " Faults: " + String.valueOf(motor.motor.getFaults()) + "\n", 
            motorName + " Sticky Warnings: " + String.valueOf(motor.motor.getStickyWarnings()) + "\n", 
            motorName + " Warnings: " + String.valueOf(motor.motor.getWarnings()) + "\n"
        };

        return info;

    }

}
