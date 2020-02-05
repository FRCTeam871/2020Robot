package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import java.util.ArrayList;
import java.util.Arrays;

public class FeedSensors {

    private ArrayList<DigitalInput> feedDetectors = new ArrayList<DigitalInput>();

    public FeedSensors(DigitalInput... feedBallDetectors){
        this.feedDetectors.addAll(Arrays.asList(feedBallDetectors));
    } 

    public boolean getFull(){
        boolean isFull = true;
        for(DigitalInput feedDetector : feedDetectors){
            isFull &= feedDetector.get();
        }
        return isFull;   
    }

    public int getBallCount(){
        int count = 0;
        for(DigitalInput feedDetector : feedDetectors){
            if(feedDetector.get()){
                count++;
            }
        }
        return count;
    }

    public boolean getEmpty(){
        boolean isEmpty = true;
        for(DigitalInput feedDetector : feedDetectors){
            isEmpty &= !feedDetector.get();
        }
        return isEmpty;   
    }

    public boolean isBallAtTop(){
        return feedDetectors.get(feedDetectors.size() - 1).get();
    }
}