package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

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
    public SpeedController getFeedMotor();

    /**
     * Piston that regulates the flow of power cells out of the chute.
     * @return Solenoid of the piston that regulates the flow of power cells out of the chute.
     */
    //public Solenoid getFeedCloser();

    /**
     * Motor that drives the chain which spins the wheels that drag in the power cells.
     * @return SpeedController of the motor that spins the wheels on the Power Cell Collecting Cechanism.
     */
    public SpeedController getGrabSpinMotor();

    /**
     * Motor that deploys or retracts the Power Cell Collecting Mechanism.
     * @return SpeedController of the motor that deploys or retracts the Power Cell Collecting Mechanism.
     */
    public SpeedController getGrabDeployMotor();

    /**
     * Piston that deploys the climb extension pistons below.
     * @return Solenoid of the piston that deploys the climb extension pistons below.
     */
    //public Solenoid getClimbDeployPiston();

    /**
     * Piston that extends to reach the Shield Generator Switch.
     * @return Soleniod of the piston described above.
     */
    //public Solenoid getClimbExtensionPiston();

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
    public FeedSensors getFeedBallDetectors();

    /**
     * Optical sensor that detects the presence of a power cell in the Power Cell Collection Mechanism.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getGrabBallDetector();

    /**
     * Limit Switch that detects if the Power Cell Collection Mechanism is fully deployed.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getGrabDeployMinDetector();

    /**
     * Limit Switch that detects if the Power Cell Collection Mechanism is fully retracted.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getGrabDeployMaxDetector();

    /**
     * Limit Switch that detects if the left Extension Piston has been fully deployed.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getClimbLeftDeploymentDetector();

    /**
     * Limit Switch that detects if the right Extension Piston has been fully deployed.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getClimbRightDeploymentDetector();

    /**
     * Limit Switch that detects if the left hook has grabbed onto the Shield Generator Switch.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getClimbLeftHookDetector();

    /**
     * Limit Switch that detects if the right hook has grabbed onto the Shield Generator Switch.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getClimbRightHookDetector();

    /**
     * The Color Sensor that detects colors of the Control Panel. This is used to determine the position of the Control Panel.
     * @return AnalogInput of the sensor described above.
     */
    //public ColorSensorV3 getPanelColorDetector();

    /**
     * Limit Switch that detects if the Control Panel Manipulator has been fully deployed.
     * @return DigitalInput of the sensor described above.
     */
    public DigitalInput getPanelMaxDetector();

    /**
     * Limit Switch that detects if the Control Panel Manipulator has been fully retracted.
     * @return DigitalInput of the sensor described above
     */
    
    public DigitalInput getPanelMinDetector();

    /**
     * Potentiometer that detects the angle at which the Control Panel Manipulator has been deployed.
     * @return AnalogInput of the sensor described above.
     */
    public AnalogInput getPanelAngleDetector();
}