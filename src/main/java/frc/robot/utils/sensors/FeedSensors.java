package frc.robot.utils.sensors;

import java.util.ArrayList;
import java.util.Arrays;

import com.team871.io.sensor.DigitalSensor;

public class FeedSensors {

    private ArrayList<DigitalSensor> feedDetectors = new ArrayList<DigitalSensor>();

    public FeedSensors(DigitalSensor... feedBallDetectors){
        this.feedDetectors.addAll(Arrays.asList(feedBallDetectors));
    } 

    public boolean getFull(){
        boolean isFull = true;
        for(DigitalSensor feedDetector : feedDetectors){
            isFull &= feedDetector.get();
        }
        return isFull;   
    }

    public int getBallCount(){
        int count = 0;
        for(DigitalSensor feedDetector : feedDetectors){
            if(feedDetector.get()){
                count++;
            }
        }
        return count;
    }

    public boolean getEmpty(){
        boolean isEmpty = true;
        for(DigitalSensor feedDetector : feedDetectors){
            isEmpty &= !feedDetector.get();
        }
        return isEmpty;   
    }

    public boolean isBallAtTop(){
        return feedDetectors.get(feedDetectors.size() - 1).get();
    }
}