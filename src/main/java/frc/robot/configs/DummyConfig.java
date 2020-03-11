package frc.robot.configs;

import com.revrobotics.ColorSensorV3;
import com.team871.io.actuator.ISolenoid;
import com.team871.io.sensor.DigitalInputSensor;
import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.dummyquarantine.DummySpeedController;
import frc.robot.utils.sensors.ColorSensor;

public class DummyConfig implements RobotConfig {
    
    private final SpeedController leftMotors;
    private final SpeedController rightMotors;
    private final SpeedController conveyorMotor;
    // private final Solenoid feedCloser;
    private final ISolenoid climbReleasePiston;
    private final SpeedController climbWinchMotor;
    private final DigitalSensor finishClimbSensor;
    private final SpeedController panelDeployMotor;
    private final SpeedController panelSpinMotor;
    private final DigitalSensor ballDetector0;
    private final DigitalSensor ballDetector1;
    private final ColorSensor panelColorDetector;
    // private final FeedSensors feedDetectors;
    private final SpeedController grabbyMotor;
    private final Relay relayReleaseSol;

    /**
     * ___ // _|_|_ // ('_') // --(:)-- // ( : ) // ~ Jagger, Anthony, Sebastian,
     * cauiutl/dinb :), Rosie
     */

    private NetworkTableInstance tabInst;

    public DummyConfig() {

        tabInst = NetworkTableInstance.getDefault();
        tabInst.setServerTeam(871);
        tabInst.startServer();

        /**
         * Drive Train Hardware required for the drive train
         */

        // SpeedControllers and Solenoids
        leftMotors = new DummySpeedController();
        rightMotors = new DummySpeedController();

        /**
         * Feeding Mechanism Hardware required for the feeding of balls through the
         * chute
         */

        // SpeedControllers and Solenoids
        conveyorMotor = new DummySpeedController();
        
        // Sensors

        /**
         * Grabbing Mechanism Hardware require for collecting the power cells
         */

        // SpeedControllers
        grabbyMotor = new DummySpeedController();

        // Sensors
        ballDetector0 = new DigitalInputSensor(0);
        ballDetector1 = new DigitalInputSensor(1);

        /**
         * Climbing Mechanism Hardware required for the robot to extend and hook onto
         * the shield generator switch
         */

        // SpeedControllers and Solenoids
        climbReleasePiston = new ISolenoid() {
			@Override
			public Value get() {
				return Value.Off;
			}

			@Override
			public void set(Value arg0) {	
			}

        };
        
        relayReleaseSol = new Relay(0);
        climbWinchMotor = new DummySpeedController();

        finishClimbSensor = new DigitalInputSensor(13);

        /**
         * Control Panel Manipulator Hardware required for spinning and detecting colors
         */

        // Sensors
        panelColorDetector = new ColorSensor(new ColorSensorV3(Port.kOnboard));

        // SpeedControllers and Solenoids
        panelDeployMotor = new DummySpeedController();
        panelSpinMotor = new DummySpeedController();
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
    public SpeedController getConveyorMotor() {
        return conveyorMotor;
    }

    @Override
    public SpeedController getPanelDeployMotor() {
        return panelDeployMotor;
    }

    @Override
    public SpeedController getPanelSpinMotor() {
        return panelSpinMotor;
    }

    @Override
    public DigitalSensor getGrabBallDetector0() {
        return ballDetector0;
    }

    @Override
    public ColorSensor getPanelColorDetector() {
        return panelColorDetector;
    }

    @Override
    public ISolenoid getClimbReleasePiston() {
        return climbReleasePiston;
    }

    @Override
    public SpeedController getClimbWinchMotor() {
        return climbWinchMotor;
    }

    @Override
    public DigitalSensor getFinishClimbSensor() {
        return finishClimbSensor;
    }

    @Override
    public SpeedController getGrabbyMotor() {
        return grabbyMotor;
    }

    @Override
    public NetworkTable getNetworkTab(String tabKey) {
        return tabInst.getTable(tabKey);
    }

    @Override
    public Relay getReleaseRelay() {
        return relayReleaseSol;
    }

    @Override
    public DigitalSensor getGrabBallDetector1() {
        return ballDetector1;
    }
}