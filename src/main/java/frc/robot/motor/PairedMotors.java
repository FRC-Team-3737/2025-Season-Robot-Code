package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class PairedMotors {
    
    public final Motor mainMotor;
    public final Motor slaveMotor;

    /**
     * The constructor for a new pair of motors that has an encoder attached to it.
     * 
     * @param main The MotorInfo for the main motor
     * @param slave The MotorInfo for the paired motor
     * @param encoder The encoder for the main motor
     * @param inverted Whether the encoder is inverted
     */
    public PairedMotors(MotorInfo main, MotorInfo slave, encoderType encoder, boolean inverted) {
        
        this.mainMotor = new Motor(main, encoder, inverted);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }

    /**
     * The constructor for a new pair of motors that has no encoder attached to it.
     * 
     * @param main The MotorInfo for the main motor
     * @param slave The MotorInfo for the paired motor
     */
    public PairedMotors(MotorInfo main, MotorInfo slave) {
        
        this.mainMotor = new Motor(main, encoderType.None, false);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }
    
    /**
     * Sets the closed loop ramp rate of the motors so that they go to the set speed over a set period of time.
     * 
     * @param rate The seconds it should take to get up to speed
     */
    public void SetRampRate(double rate) {

        mainMotor.motorConfig.closedLoopRampRate(rate);
        slaveMotor.motorConfig.closedLoopRampRate(rate);

    }

    /**
     * Gets the temperature of the motors, in degrees Fahrenheit, useful for preventing overheating.
     * 
     * @return The fahrenheit of the hottest motor
     */
    public double GetTemperature() {

        return Math.max(mainMotor.motor.getMotorTemperature()*(9/5)+32, slaveMotor.motor.getMotorTemperature()*(9/5)+32);

    }

    /**
     * Gets the output current of the motors, in amps, useful for preventing stress.
     * 
     * @return The amperage of the most stressed motor
     */
    public double GetCurrent() {

        return Math.max(mainMotor.motor.getOutputCurrent(), slaveMotor.motor.getOutputCurrent());

    }

    /**
     * Gets the velocity of the motors directly in revolutions per minute.
     * 
     * @return The average RPM of the motor
     */
    public double GetVelocity() {

        return (mainMotor.motor.getEncoder().getVelocity() + slaveMotor.motor.getEncoder().getVelocity())/2;

    }

    /**
     * Sets the speed of the motors to what the user inputs. Only -1 to 1 values are allows because it is a percentage value.
     * 
     * <p><i>The slave motor runs in reverse.</i>
     * 
     * @param speed The speed the motor should run at
     */
    public void Spin(double speed) {
        
        mainMotor.motor.set(speed);
        slaveMotor.motor.set(-speed);

    }

    /** 
     * Grabs all the debugging information from the motor. 
     * 
     * <p>Keep all motor name inputs capitalized and spaced out for best visibility.
     * 
     * @param motorName name to be displayed on the dashboard.
     */
    public String[][] GetDebuggingInformation(String motorName) {

        String[][] info = {
            {
                "Main " + motorName + " ID: " + String.valueOf(mainMotor.motor.getDeviceId()) + "\n", 
                "Main " + motorName + " Motor Type: " + String.valueOf(mainMotor.motor.getMotorType()) + "\n", 
                "Main " + motorName + " Firmware Version: " + String.valueOf(mainMotor.motor.getFirmwareVersion()) + "\n", 
                "Main " + motorName + " Bus Voltage: " + String.valueOf(mainMotor.motor.getBusVoltage()) + "\n", 
                "Main " + motorName + " Absolute Encoder: " + String.valueOf(mainMotor.motor.getAbsoluteEncoder()) + "\n", 
                "Main " + motorName + " Analog Encoder: " + String.valueOf(mainMotor.motor.getAnalog()) + "\n", 
                "Main " + motorName + " Sticky Faults: " + String.valueOf(mainMotor.motor.getStickyFaults()) + "\n", 
                "Main " + motorName + " Faults: " + String.valueOf(mainMotor.motor.getFaults()) + "\n", 
                "Main " + motorName + " Sticky Warnings: " + String.valueOf(mainMotor.motor.getStickyWarnings()) + "\n", 
                "Main " + motorName + " Warnings: " + String.valueOf(mainMotor.motor.getWarnings()) + "\n"
            },
            {
                "Slave " + motorName + " ID: " + String.valueOf(mainMotor.motor.getDeviceId()) + "\n", 
                "Slave " + motorName + " Motor Type: " + String.valueOf(mainMotor.motor.getMotorType()) + "\n", 
                "Slave " + motorName + " Firmware Version: " + String.valueOf(mainMotor.motor.getFirmwareVersion()) + "\n", 
                "Slave " + motorName + " Bus Voltage: " + String.valueOf(mainMotor.motor.getBusVoltage()) + "\n", 
                "Slave " + motorName + " Absolute Encoder: " + String.valueOf(mainMotor.motor.getAbsoluteEncoder()) + "\n", 
                "Slave " + motorName + " Analog Encoder: " + String.valueOf(mainMotor.motor.getAnalog()) + "\n", 
                "Slave " + motorName + " Sticky Faults: " + String.valueOf(mainMotor.motor.getStickyFaults()) + "\n", 
                "Slave " + motorName + " Faults: " + String.valueOf(mainMotor.motor.getFaults()) + "\n", 
                "Slave " + motorName + " Sticky Warnings: " + String.valueOf(mainMotor.motor.getStickyWarnings()) + "\n", 
                "Slave " + motorName + " Warnings: " + String.valueOf(mainMotor.motor.getWarnings()) + "\n"
            }
        };

        return info;

    }

}
