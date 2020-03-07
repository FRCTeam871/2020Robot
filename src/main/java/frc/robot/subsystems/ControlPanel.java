package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team871.hid.IButton;
import com.team871.io.actuator.LimitedSpeedController;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;
import frc.robot.utils.ColorButton;
import frc.robot.utils.Colors;
import frc.robot.utils.sensors.ColorSensor;

public class ControlPanel {
    private enum ControlPanelState {
        SPIN_THREE_TIMES, 
        SPINTOCOLOR, 
        OFF;
    }

    private ControlPanelState panelState = ControlPanelState.OFF;
    private SpeedController panelDeployMotor;
    private SpeedController panelSpeedMotor;
    private ColorSensor panelColorDetector;
    private double numSpun;
    private Colors desiredColor;
    private Colors previousColor = Colors.NONE;

    private NetworkTableEntry deployMotorSpeedEntry;
    private NetworkTableEntry panelSpeedEntry;
    private NetworkTableEntry colorEntry;
    private NetworkTableEntry lowerLimitEntry;
    private NetworkTableEntry upperLimitEntry;

    public ControlPanel(RobotConfig config) {
        this.panelDeployMotor = config.getPanelDeployMotor();
        this.panelSpeedMotor = config.getPanelSpinMotor();
        this.panelColorDetector = config.getPanelColorDetector();

        this.deployMotorSpeedEntry = config.getNetworkTab("controlPanelSystem").getEntry("deployMotorSpeed");
        this.panelSpeedEntry = config.getNetworkTab("controlPanelSystem").getEntry("panelSpeed");
        this.colorEntry = config.getNetworkTab("controlPanelSystem").getEntry("color");
        this.lowerLimitEntry = config.getNetworkTab("controlPanelSystem").getEntry("lowerLimit");
        this.upperLimitEntry = config.getNetworkTab("controlPanelSystem").getEntry("upperLimit");
    }

    /**
     * Uses a button to toggle the deployment of the Control Panel Manipulator
     * @param panelDeployButton the button used to toggle the deployment of the Control Panel Manipulator
     */
    private void setManipulator(IButton panelDeployButton) {
        panelDeployMotor.set((panelDeployButton.getValue() ? .5 : -.5));
    }

    /**
     * Spins the control panel until the color read by the sensor is the same as the target color
     */
    private void spinToColor() {
        //The color button is translating the color we want the wheel to detect into the color the robot needs to detect :-)
        panelSpeedMotor.set(panelColorDetector.getColor() == desiredColor ? 0 : 1);
    }

    /**
     * Spins
     */
    private void spinThreeTimes() {
        if (previousColor != panelColorDetector.getColor()) { 
            numSpun += .125;
        }
        previousColor = panelColorDetector.getColor();
        panelSpeedMotor.set(numSpun < 3 ? 1 : 0); 
    }

    /**
     * Manually spins the Control Panel via a momentary button in case the spinThreeTimes method fails.
     * @param extraSpinButton the button used to control the Control Panel Manipulator
     */
    public void extraSpin(IButton extraSpinButton) {
        panelSpeedMotor.set(extraSpinButton.getValue() ? 1 : 0);
    }

    public void controlWheelSpin(IButton spinButton, ColorButton colorButton) {
        switch (panelState) {
            case OFF: {
                if(colorButton.getValue()) {
                    desiredColor = colorButton.getButtonColor();
                    panelState = ControlPanelState.SPINTOCOLOR;
                }

                if(spinButton.getValue()) {
                    numSpun = 0;
                    panelState = ControlPanelState.SPIN_THREE_TIMES;
                }
                break;
            }
            case SPIN_THREE_TIMES: {
                spinThreeTimes();
                if (colorButton.getValue()) {
                    desiredColor = colorButton.getButtonColor();
                    panelState = ControlPanelState.SPINTOCOLOR;
                }
                
                if(spinButton.getValue() || numSpun >= 3) {
                    panelState = ControlPanelState.OFF;
                }
                break;
            }

            case SPINTOCOLOR:{
                spinToColor();
                if(spinButton.getValue()) {
                    numSpun = 0;
                    panelState = ControlPanelState.SPIN_THREE_TIMES;
                }

                if(colorButton.getValue() || (desiredColor == panelColorDetector.getColor())) {
                    panelState = ControlPanelState.OFF;
                }
                break;
            } 
        }
    }

    public void update(JoystickConfig controller){
        setManipulator(controller.getPanelDeployButton());
        extraSpin(controller.getXtraSpinButton());
        controlWheelSpin(controller.getPanelSpinButton(), controller.getColorButton());

        deployMotorSpeedEntry.setDouble(panelDeployMotor.get());
        panelSpeedEntry.setDouble(panelSpeedMotor.get());
        colorEntry.setString(panelColorDetector.getColor().name());
        //lowerLimitEntry.setBoolean(((WPI_TalonSRX)panelDeployMotor).);
        //upperLimitEntry.setBoolean(((WPI_TalonSRX)panelDeployMotor).getPositiveLimit().get());
        
        SensorCollection sc = ((WPI_TalonSRX)panelDeployMotor).getSensorCollection();
        // System.out.println("Fwd: " + sc.isFwdLimitSwitchClosed() + " Rev: " + sc.isRevLimitSwitchClosed());
    }
}