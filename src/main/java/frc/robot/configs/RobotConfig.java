package frc.robot.configs;

import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.sensors.ColorSensor;
import frc.robot.utils.sensors.FeedSensors;

public interface RobotConfig {
    /**
     * All left motors of the gearbox that drives the left wheel.
     * @return SpeedController of the motor group.
     */
    public SpeedController getLeftMotors();

    /**
     * All right motors of the gearbox that drives the right wheel.
     * @return SpeedController of the motor group.
     */
    public SpeedController getRightMotors();

    /**
     * Motor that powers the conveyer belt that drives the power cells up the chute.
     * @return SpeedController of the motor that drives the conveyer belt.
     */
    public SpeedController getConveyorMotor();

    /**
     * Piston that regulates the flow of power cells out of the chute.
     * @return Solenoid of the piston that regulates the flow of power cells out of the chute.
     */
    //public Solenoid getFeedCloser();

    /**
     * Piston that releases the climbing mechanism
     * @return Solenoid of the piston that deploys the climb extension pistons below.
     */
    public DoubleSolenoid getClimbReleasePiston();

    /**
     * Motor that activates the winch
     * @return SpeedController of the motor described above.
     */
    public SpeedController getClimbWinchMotor();

    /**
     * Optical Sensor that detects if the robot has finished climbing
     * @return DigitalSensor of the sensor descirbed above
     */
    public DigitalSensor getFinishClimbSensor();

    /**
     * Motor that deploys the Control Panel Manipulator.
     * @return SpeedController of the motor described above.
     */
    public SpeedController getPanelDeployMotor();

    /**
     * Motor attached to the Control Panel Manipulator that spins the Control Panel.
     * @return SpeedController of the motor described above.
     */
    public SpeedController getPanelSpinMotor();

    /**
     * Optical sensor that detects if the chute is full.
     * @return FeedSensors of the sensor described above.
     */
    // public FeedSensors getFeedBallDetectors();

    /**
     * Optical sensor that detects the presence of a power cell in the Power Cell Collection Mechanism.
     * @return DigitalSensor of the sensor described above.
     */
    public DigitalSensor getGrabBallDetector0();

    /**
     * Optical sensor that detects the presence of a Power Cell in the beginning of the Chute
     * @return DigitalSensor of the sensor described above
     */
    public DigitalSensor getGrabBallDetector1();

    /**
     * The Color Sensor that detects colors of the Control Panel. This is used to determine the position of the Control Panel.
     * @return AnalogInput of the sensor described above.
     */
    public ColorSensor getPanelColorDetector();

    /**
     * The motor that controls the grab mechanism
     * @return SpeedController of the motor described above
     */
    public SpeedController getGrabbyMotor();

    /**
     * Returns any network table based on the given table key 
     * @param tabKey the table key used to identify the desired table
     * @return the network table with the corresponding key
     */
    public NetworkTable getNetworkTab(String tabKey);

    /**
     * The relay that releases the block from the relay system on the winch that prevents it from driving backwards
     * @return relay described above
     */
    public Relay getReleaseRelay();
}