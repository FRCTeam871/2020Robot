package frc.robot.configs;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;
import com.team871.io.actuator.DoubleSolenoid871;
import com.team871.io.actuator.ISolenoid;
import com.team871.io.sensor.DigitalInputSensor;
import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.utils.sensors.ColorSensor;

public class DefaultRobotConfig implements RobotConfig {

    private final SpeedController leftMotors;
    private final SpeedController rightMotors;
    private final SpeedController conveyorMotor;
    // private final Solenoid feedCloser;
    private final DoubleSolenoid871 climbReleasePiston;
    private final SpeedController climbWinchMotor;
    private final DigitalSensor finishClimbSensor;
    private final WPI_TalonSRX panelDeployMotor;
    private final SpeedController panelSpinMotor;
    private final DigitalSensor ballDetector0;
    private final DigitalSensor ballDetector1;
    private final ColorSensor panelColorDetector;
    // private final FeedSensors feedDetectors;
    private final SpeedController grabbyMotor;
    private final Relay relayReleaseSol;
    private Compressor compressor;

    /**
     * ___ // _|_|_ // ('_') // --(:)-- // ( : ) // ~ Jagger, Anthony, Sebastian,
     * cauiutl/dinb :), Rosie
     */

    private NetworkTableInstance tabInst;

    public DefaultRobotConfig() {

        tabInst = NetworkTableInstance.getDefault();
        tabInst.setServerTeam(871);
        tabInst.startServer();

        /**
         * Drive Train Hardware required for the drive train
         */

        // SpeedControllers and Solenoids
        leftMotors = new SpeedControllerGroup(new CANSparkMax(1, MotorType.kBrushless),
                new CANSparkMax(2, MotorType.kBrushless));
        rightMotors = new SpeedControllerGroup(new CANSparkMax(3, MotorType.kBrushless),
                new CANSparkMax(4, MotorType.kBrushless));
        leftMotors.setInverted(true);
        /**
         * Feeding Mechanism Hardware required for the feeding of balls through the
         * chute
         */

        // SpeedControllers and Solenoids
        conveyorMotor = new WPI_VictorSPX(7);

        /**
         * Grabbing Mechanism Hardware require for collecting the power cells
         */

        // SpeedControllers
        grabbyMotor = new WPI_VictorSPX(9);

        // Sensors
        ballDetector0 = new DigitalInputSensor(0);
        ballDetector1 = new DigitalInputSensor(1);

        /**
         * Climbing Mechanism Hardware required for the robot to extend and hook onto
         * the shield generator switch
         */

        // SpeedControllers and Solenoids
        climbReleasePiston = new DoubleSolenoid871(6, 7);
        relayReleaseSol = new Relay(0);
        climbWinchMotor = new WPI_TalonFX(8);
        finishClimbSensor = new DigitalInputSensor(13);

        /**
         * Control Panel Manipulator Hardware required for spinning and detecting colors
         */

        // Sensors
        panelColorDetector = new ColorSensor(new ColorSensorV3(Port.kOnboard));

        // SpeedControllers and Solenoids

        panelDeployMotor = new WPI_TalonSRX(5);
        panelDeployMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);
        panelDeployMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen);

        panelSpinMotor = new WPI_VictorSPX(6);

        
        compressor = new Compressor();
        compressor.start();
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