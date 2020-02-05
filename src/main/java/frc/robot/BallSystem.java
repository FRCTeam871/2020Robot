package frc.robot;

import com.team871.hid.HIDButton;
import com.team871.hid.IButton;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class BallSystem {

    private enum CollectorState{
        UNDEPLOYED,
        DEPLOYED,
        ACTIVE;
    }

    private CollectorState currentState = CollectorState.UNDEPLOYED;

    private SpeedController grabSpinMotor, grabDeployMotor, feedMotor;

    private Solenoid feedCloser;

    private DigitalInput grabBallDetector, grabDeployMinDetector, grabDeployMaxDetector;
    private FeedSensors feedDetectors;

    public BallSystem(RobotConfig config){
        this.grabSpinMotor = config.getGrabSpinMotor();
        this.grabDeployMotor = config.getGrabDeployMotor();
        this.feedMotor = config.getFeedMotor();
        //this.feedCloser = config.getFeedCloser();
        this.grabBallDetector = config.getGrabBallDetector();
        this.grabDeployMaxDetector = config.getGrabDeployMaxDetector();
        this.grabDeployMinDetector = config.getGrabDeployMinDetector();
        this.feedDetectors = config.getFeedBallDetectors();
    }

 
    public void operateCollector(IButton toggleGrabDeployment, IButton toggleGrabSpin){
       switch(currentState){
        case UNDEPLOYED:{
            if (toggleGrabDeployment.getValue()){
                currentState = CollectorState.DEPLOYED;
            }
            grabDeployMotor.set(grabDeployMaxDetector.get() ? 0 : -0.3 );
            break;
        }
        case DEPLOYED:{
            if (toggleGrabSpin.getValue()){
                currentState = CollectorState.ACTIVE;
            }
            if (toggleGrabDeployment.getValue()){
                currentState = CollectorState.UNDEPLOYED;
            }
            grabDeployMotor.set(grabDeployMinDetector.get() ? 0 : 0.3);
            break;
        }
        case ACTIVE:{
            grabSpinMotor.set(0.5);
            if (toggleGrabSpin.getValue() || (grabBallDetector.get() && feedDetectors.getFull())){
                currentState = CollectorState.DEPLOYED;
            }
            break;
        }
      }
   }

   public void toggleConveyerSpeed(IButton conveyorSpeedToggle){
       feedMotor.set(conveyorSpeedToggle.getValue() ? 0.3 : 0);
   }

   public void ballRelease(IButton ballReleaseButton){
       feedCloser.set(ballReleaseButton.getValue());
    }

 }