package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class PairedMotors {
    
    public final Motor mainMotor;
    public final Motor slaveMotor;

    public PairedMotors(MotorInfo main, MotorInfo slave, encoderType encoder, boolean inverted) {
        
        this.mainMotor = new Motor(main, encoder, inverted);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }

    public PairedMotors(MotorInfo main, MotorInfo slave) {
        
        this.mainMotor = new Motor(main, encoderType.None, false);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }
    
    public void SetRampRate(double rate) {

        mainMotor.motorConfig.closedLoopRampRate(rate);
        slaveMotor.motorConfig.closedLoopRampRate(rate);

    }

    public double GetTemperature() {

        return Math.max(mainMotor.motor.getMotorTemperature(), slaveMotor.motor.getMotorTemperature());

    }

    public double GetCurrent() {

        return Math.max(mainMotor.motor.getOutputCurrent(), slaveMotor.motor.getOutputCurrent());

    }

    public double GetVelocity() {

        return (mainMotor.inBuiltEncoder.getVelocity() + slaveMotor.inBuiltEncoder.getVelocity())/2;

    }

    public void Spin(double speed) {
        
        mainMotor.motor.set(speed);
        slaveMotor.motor.set(-speed);

    }

    public String[] GetDebuggingInformation(String motorName) {

        /*  Please keep motor name spaced out and all words start capitalized.  */

        String[] info = {
            "Main " + motorName + " ID: " + String.valueOf(mainMotor.motor.getDeviceId()), 
            "Main " + motorName + " Motor Type: " + String.valueOf(mainMotor.motor.getMotorType()), 
            "Main " + motorName + " Firmware Version: " + String.valueOf(mainMotor.motor.getFirmwareVersion()), 
            "Main " + motorName + " Bus Voltage: " + String.valueOf(mainMotor.motor.getBusVoltage()), 
            "Main " + motorName + " Absolute Encoder: " + String.valueOf(mainMotor.motor.getAbsoluteEncoder()), 
            "Main " + motorName + " Analog Encoder: " + String.valueOf(mainMotor.motor.getAnalog()), 
            "Main " + motorName + " Sticky Faults: " + String.valueOf(mainMotor.motor.getStickyFaults()), 
            "Main " + motorName + " Faults: " + String.valueOf(mainMotor.motor.getFaults()), 
            "Main " + motorName + " Sticky Warnings: " + String.valueOf(mainMotor.motor.getStickyWarnings()), 
            "Main " + motorName + " Warnings: " + String.valueOf(mainMotor.motor.getWarnings()),

            "Slave " + motorName + " ID: " + String.valueOf(mainMotor.motor.getDeviceId()), 
            "Slave " + motorName + " Motor Type: " + String.valueOf(mainMotor.motor.getMotorType()), 
            "Slave " + motorName + " Firmware Version: " + String.valueOf(mainMotor.motor.getFirmwareVersion()), 
            "Slave " + motorName + " Bus Voltage: " + String.valueOf(mainMotor.motor.getBusVoltage()), 
            "Slave " + motorName + " Absolute Encoder: " + String.valueOf(mainMotor.motor.getAbsoluteEncoder()), 
            "Slave " + motorName + " Analog Encoder: " + String.valueOf(mainMotor.motor.getAnalog()), 
            "Slave " + motorName + " Sticky Faults: " + String.valueOf(mainMotor.motor.getStickyFaults()), 
            "Slave " + motorName + " Faults: " + String.valueOf(mainMotor.motor.getFaults()), 
            "Slave " + motorName + " Sticky Warnings: " + String.valueOf(mainMotor.motor.getStickyWarnings()), 
            "Slave " + motorName + " Warnings: " + String.valueOf(mainMotor.motor.getWarnings())
        };

        return info;

    }

}
