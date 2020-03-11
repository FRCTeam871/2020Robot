package frc.robot.subsystems;

import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;

public class BallSystem {

    private SpeedController conveyorMotor;
    private SpeedController grabbyMotor;

    private DigitalSensor ballSensor0;
    private DigitalSensor ballSensor1;

    private NetworkTableEntry conveyorSpeedEntry;
    private NetworkTableEntry ballSensorEntry;
    private NetworkTableEntry nomNomMotorEntry;

    /**
     * Vital code
     */
    private final double YES = 1.;
    private final double OFF = .0;

    //private Solenoid feedCloser;

    public BallSystem(RobotConfig config) {
        this.conveyorMotor = config.getConveyorMotor();
        this.grabbyMotor = config.getGrabbyMotor();
        this.ballSensor0 = config.getGrabBallDetector0();
        this.ballSensor1 = config.getGrabBallDetector1();

        this.conveyorSpeedEntry = config.getNetworkTab("powerCellSystem").getEntry("speedConveyor");
        this.ballSensorEntry = config.getNetworkTab("powerCellSystem").getEntry("ballDetector");
        this.nomNomMotorEntry = config.getNetworkTab("powerCellSystem").getEntry("nomNomEntry");
    }

    /**
     * This sets the speed of grabby and putty
     * @param controller used on the robot
     */
    public void update(JoystickConfig controller) {
        boolean ballDetected = (ballSensor0.get() || ballSensor1.get());
        double conveyorSpeed = 0;
        double grabbySpeed = 0;

        // Prioritize spitting balls out.  
        if(controller.getVomitButton().getRaw()) {
            conveyorSpeed = YES;
        }  else {
            // If we're collecting, run the nom nom.
            // If there's a ball detected, run at 1/3 and also run the chute to enqueue more balls
            if(controller.getNomNomButton().getRaw()) {        
                conveyorSpeed = (ballDetected ? .4 : 0);
                grabbySpeed = (ballDetected ? .4 : .6);
            }
        }

        // If we're un jamming, run stuff backwards based on the button
        if(controller.getUnJamButton().getRaw()) {
            conveyorSpeed = controller.getVomitButton().getRaw() ? -.5 : 0;
            grabbySpeed = controller.getNomNomButton().getRaw() ? -.5 : 0;
        }
        
        conveyorMotor.set(conveyorSpeed);
        grabbyMotor.set(grabbySpeed);

       ballSensorEntry.setBoolean(ballDetected);
       conveyorSpeedEntry.setDouble(conveyorMotor.get());
       nomNomMotorEntry.setDouble(grabbyMotor.get());
   }
}
//59 lines murdered :-)
//Press F to pay respects (F)