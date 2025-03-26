package frc.robot.motor;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Constants.controllerType;

public class Motor {
    
    public final SparkBase motor;
    public final SparkBaseConfig motorConfig;
    public final MotorInfo info;
    
    public enum encoderType {
        None, Analog, Absolute;
    }

    /**
     * Creates a new Spark controller object for the code to use and control.
     * 
     * @param info The MotorInfo for the motor
     * @param encoder The encoder for the motor
     * @param inverted Whether the encoder is inverted
     */
    public Motor(MotorInfo info, encoderType encoder, boolean inverted) {

        this.info = info;

        if (info.Controller == controllerType.MAX) {
            this.motor = new SparkMax(info.ID, MotorType.kBrushless);
            this.motorConfig = new SparkMaxConfig();
            motorConfig.idleMode(IdleMode.kBrake);
        } else {
            this.motor = new SparkFlex(info.ID, MotorType.kBrushless);
            this.motorConfig = new SparkFlexConfig();
            motorConfig.idleMode(IdleMode.kBrake);
        }

        if (encoder == encoderType.Analog) {
            motorConfig.analogSensor.inverted(inverted);
            motor.configure(motorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        } else if (encoder == encoderType.Absolute) {
            motorConfig.absoluteEncoder.inverted(inverted);
            motor.configure(motorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        } else {
            motor.configure(motorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        }

    }

    /**
     * Gets the number of rotations the motor has made since start in revolutions.
     * 
     * @return The amount of rotations the motor has made
     */
    public double getPosition(boolean reverse) {

        if (reverse) {
            return -(motor.getEncoder().getPosition());
        } else {
            return motor.getEncoder().getPosition();
        }

    }

    /**
     * Gets the voltage from an analog encoder.
     * 
     * @return The encoder voltage
     */
    public double getAnalogVoltage() {

        return motor.getAnalog().getVoltage();

    }

    /**
     * Gets the voltage of the analog encoder and modifies it to return a number in degrees.
     * 
     * @return The raw angle of an analog encoder
     */
    public double getAnalogRawAngle() {
        
        double degreesPerVolt = 360/info.MaxEncoderValue;
        double encoderVoltage = motor.getAnalog().getVoltage();

        double rawAngle = degreesPerVolt * encoderVoltage;

        return rawAngle;

    }

    /**
     * Gets the raw analog angle and adds an offset for the code.
     * 
     * @return The modified angle of an analog encoder
     */
    public double getAnalogAngle() {
        
        return (getAnalogRawAngle() + info.ReferenceAngle)%360;

    }

    /**
     * Gets the angle of the absolute encoder directly.
     * 
     * @return The raw angle of an absolute encoder
     */
    public double getAbsoluteRawAngle() {

        return motor.getAbsoluteEncoder().getPosition();

    }

    /**
     * Gets the raw absolute angle and adds an offset for the code.
     * 
     * @return The modified angle of an analog encoder
     */
    public double getAbsoluteAngle() {

        return (getAbsoluteRawAngle() + info.ReferenceAngle)%360;

    }

}
