package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class Motors {
    
    public final Motor motor;

    /*  The following is method overloading, allowing different inputs to be possible by using different parameters with a same name method.  */
    
    /**
     * The constructor for a new motor that has an encoder attached to it.
     * 
     * @param motor The MotorInfo for the motor
     * @param encoder The encoder for the motor
     * @param inverted Whether the encoder is inverted
     */
    public Motors(MotorInfo motor, encoderType encoder, boolean inverted) {

        this.motor = new Motor(motor, encoder, inverted);

    }

    /**
     * The constructor for a new motor that has no encoder attached to it.
     * 
     * @param motor The MotorInfo for the motor
     */
    public Motors(MotorInfo motor) {

        this.motor = new Motor(motor, encoderType.None, false);

    }

    /**
     * Sets the closed loop ramp rate of the motor so that it goes to the set speed over a set period of time.
     * 
     * @param rate The seconds it should take to get up to speed
     */
    public void SetRampRate(double rate) {

        motor.motorConfig.closedLoopRampRate(rate);

    }

    /**
     * Gets the temperature of the motor, in degrees Fahrenheit, useful for preventing overheating.
     * 
     * @return The fahrenheit of the motor
     */
    public double GetTemperature() {

        return (motor.motor.getMotorTemperature()*(9/5)+32);

    }

    /**
     * Gets the output current of the motor, in amps, useful for preventing stress.
     * 
     * @return The amperage of the motor
     */
    public double GetCurrent() {

        return motor.motor.getOutputCurrent();

    }

    /**
     * Gets the velocity of the motor directly in revolutions per minute.
     * 
     * @return The RPM of the motor
     */
    public double GetVelocity() {

        return motor.motor.getEncoder().getVelocity();

    }

    /**
     * Sets the speed of the motor to what the user inputs. Only -1 to 1 values are allows because it is a percentage value.
     * 
     * @param speed The speed the motor should run at
     */
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
