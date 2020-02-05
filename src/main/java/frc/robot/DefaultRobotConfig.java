package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.I2C.Port;

public class DefaultRobotConfig implements RobotConfig {
    
    private final SpeedController leftMotors;
    private final SpeedController rightMotors;
    private final SpeedController feedMotor;
    //private final Solenoid feedCloser;
    private final SpeedController grabSpinMotor;
    private final SpeedController grabDeployMotor;
    //private final Solenoid climbDeployPiston;
    //private final Solenoid climbExtensionPiston;
    private final SpeedController panelDeployMotor;
    private final SpeedController panelSpinMotor;
    private final DigitalInput grabBallDetector;
    private final DigitalInput grabDeployMinDetector;
    private final DigitalInput grabDeployMaxDetector;
    private final DigitalInput climbLeftDeploymentDetector;
    private final DigitalInput climbRightDeploymentDetector;
    private final DigitalInput climbLeftHookDetector;
    private final DigitalInput climbRightHookDetector;
    //private final ColorSensorV3 panelColorDetector;
    private final DigitalInput panelMaxDetector;
    private final DigitalInput panelMinDetector;
    private final AnalogInput panelAngleDetector;
    private final FeedSensors feedDetectors; 

    public DefaultRobotConfig() {

        /**
         * Drive Train
         * Hardware required for the drive train
         */

         //SpeedControllers and Solenoids
        leftMotors = new SpeedControllerGroup(new CANSparkMax(1, MotorType.kBrushless), new CANSparkMax(2, MotorType.kBrushless));
        rightMotors = new SpeedControllerGroup(new CANSparkMax(3, MotorType.kBrushless), new CANSparkMax(4, MotorType.kBrushless));
        leftMotors.setInverted(true);
        /**
         * Feeding Mechanism
         * Hardware required for the feeding of balls through the chute
         */

         //SpeedControllers and Solenoids
         feedMotor = new DummySpeedController();
         //feedCloser = new Solenoid(1);

         //Sensors 
        feedDetectors = new FeedSensors(new DigitalInput(0), new DigitalInput(1), new DigitalInput(2), new DigitalInput(3));
         
        /**
          * Grabbing Mechanism
          * Hardware require for collecting the power cells 
          */

         //SpeedControllers and Solenoids
         grabSpinMotor = new DummySpeedController();
         grabDeployMotor = new DummySpeedController();
         
         //Sensors 
         grabBallDetector = new DigitalInput(4);
         grabDeployMaxDetector = new DigitalInput(5);
         grabDeployMinDetector = new DigitalInput(6);


        /**
         * Climbing Mechanism
         * Hardware required for the robot to extend and hook onto the shield generator switch
         */

         //SpeedControllers and Solenoids
         //climbDeployPiston = new Solenoid(2);
         //climbExtensionPiston = new Solenoid(3);

         //Sensors 
         climbLeftHookDetector = new DigitalInput(7);
         climbLeftDeploymentDetector = new DigitalInput(8);
         climbRightDeploymentDetector = new DigitalInput(9);
         climbRightHookDetector = new DigitalInput(10);
         
         /**
          * Control Panel Manipulator
          * Hardware required for spinning and detecting colors
          */

          //SpeedControllers and Solenoids
          panelDeployMotor = new DummySpeedController();
          panelSpinMotor = new DummySpeedController();
          

          //Sensors 
          panelMaxDetector = new DigitalInput(11);
          panelMinDetector = new DigitalInput(12);
          panelAngleDetector = new AnalogInput(0);
          //panelColorDetector = new ColorSensorV3(Port.kOnboard);
        }

    @Override
    public SpeedController getLeftMotors() {
        return leftMotors;
    }

    @Override
    public SpeedController getRightMotors() {
        return rightMotors;
    }

    @Override
    public SpeedController getFeedMotor() {
        return feedMotor;
    }

    // @Override
    // public Solenoid getFeedCloser() {
    //     return feedCloser;
    // }

    @Override
    public SpeedController getGrabSpinMotor() {
        return grabSpinMotor;
    }

    @Override
    public SpeedController getGrabDeployMotor() {
        return grabDeployMotor;
    }

    // @Override
    // public Solenoid getClimbDeployPiston() {
    //     return climbDeployPiston;
    // }

    // @Override
    // public Solenoid getClimbExtensionPiston() {
    //     return climbExtensionPiston;
    // }

    @Override
    public SpeedController getPanelDeployMotor() {
        return panelDeployMotor;
    }

    @Override
    public SpeedController getPanelSpinMotor() {
        return panelSpinMotor;
    }

    @Override
    public FeedSensors getFeedBallDetectors() {
        return feedDetectors;
    }

    @Override
    public DigitalInput getGrabBallDetector() {
        return grabBallDetector;
    }

    @Override
    public DigitalInput getGrabDeployMinDetector() {
        return grabDeployMinDetector;
    }

    @Override
    public DigitalInput getGrabDeployMaxDetector() {
        return grabDeployMaxDetector;
    }

    @Override
    public DigitalInput getClimbLeftDeploymentDetector() {
        return climbLeftDeploymentDetector;
    }

    @Override
    public DigitalInput getClimbRightDeploymentDetector() {
        return climbRightDeploymentDetector;
    }

    @Override
    public DigitalInput getClimbLeftHookDetector() {
        return climbLeftHookDetector;
    }

    @Override
    public DigitalInput getClimbRightHookDetector() {
        return climbRightHookDetector;
    }

    // @Override
    // public ColorSensorV3 getPanelColorDetector() {
    //     return panelColorDetector;
    // }

    @Override
    public DigitalInput getPanelMaxDetector() {
        return panelMaxDetector;
    }

    @Override
    public AnalogInput getPanelAngleDetector() {
        return panelAngleDetector;
    }

    @Override
    public DigitalInput getPanelMinDetector() {
        return getPanelMinDetector();
    }
}