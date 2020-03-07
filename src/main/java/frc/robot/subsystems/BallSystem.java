package frc.robot.subsystems;

import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;

public class BallSystem {

    private SpeedController conveyorMotor;
    private SpeedController grabbyMotor;

    private DigitalSensor ballSensor;

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
        this.ballSensor = config.getGrabBallDetector();
        //this.feedCloser = config.getFeedCloser();

        this.conveyorSpeedEntry = config.getNetworkTab("powerCellSystem").getEntry("speedConveyor");
        this.ballSensorEntry = config.getNetworkTab("powerCellSystem").getEntry("ballDetector");
        this.nomNomMotorEntry = config.getNetworkTab("powerCellSystem").getEntry("nomNomEntry");
    }

    /**
     * This sets the speed of grabby and putty
     * @param controller used on the robot
     */
    public void update(JoystickConfig controller) {
       conveyorMotor.set((controller.getVomitButton().getValue()/* || ballSensor.get()*/) ? YES : OFF);
       grabbyMotor.set((controller.getNomNomButton().getValue() ? YES : OFF));

       ballSensorEntry.setBoolean(ballSensor.get());
       conveyorSpeedEntry.setDouble(conveyorMotor.get());
       nomNomMotorEntry.setDouble(grabbyMotor.get());
   }
}
//59 lines murdered :-)
//Press F to pay respects (F)