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

        return motor.inBuiltEncoder.getVelocity();

    }

    public void Spin(double speed) {

        motor.motor.set(speed);

    }
    
    public String[] GetDebuggingInformation(String motorName) {

        /*  Please keep motor name spaced out and all words start capitalized.  */

        String[] info = {
            motorName + " ID: " + String.valueOf(motor.motor.getDeviceId()), 
            motorName + " Motor Type: " + String.valueOf(motor.motor.getMotorType()), 
            motorName + " Firmware Version: " + String.valueOf(motor.motor.getFirmwareVersion()), 
            motorName + " Bus Voltage: " + String.valueOf(motor.motor.getBusVoltage()), 
            motorName + " Absolute Encoder: " + String.valueOf(motor.motor.getAbsoluteEncoder()), 
            motorName + " Analog Encoder: " + String.valueOf(motor.motor.getAnalog()), 
            motorName + " Sticky Faults: " + String.valueOf(motor.motor.getStickyFaults()), 
            motorName + " Faults: " + String.valueOf(motor.motor.getFaults()), 
            motorName + " Sticky Warnings: " + String.valueOf(motor.motor.getStickyWarnings()), 
            motorName + " Warnings: " + String.valueOf(motor.motor.getWarnings())
        };

        return info;

    }

}
