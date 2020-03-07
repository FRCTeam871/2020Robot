package frc.robot.utils.sensors;

import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * AnalogConverter
 */
public class AnalogToDigitalSensor implements DigitalSensor{
    private AnalogInput sensor;
    private int threshold;
    private boolean isInverted;

    public AnalogToDigitalSensor(AnalogInput sensor, int threshold){
        this.sensor = sensor;
        this.threshold = threshold;
        this.isInverted = false;
    }

    @Override
    public boolean get(){
        return (sensor.getValue() <= threshold) ^ isInverted; //When isInverted is false it passes the raw value, when isInverted is true it inverts the value
        /*((sensor.getValue() <= threshold) | (isInverted))  | output
         *               false              |     false      |  false
         *               true               |     false      |  true
         *               false              |     true       |  true
         *               true               |     true       |  false
         */
    }

    public void setInverted(boolean isInverted){
        this.isInverted = isInverted;
    }
       
}