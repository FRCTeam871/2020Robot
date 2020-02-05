package frc.robot;

import com.revrobotics.ColorSensorV3;
import com.team871.hid.HIDAxis;
import com.team871.hid.HIDButton;
import com.team871.hid.IAxis;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ControlPanel{
    private SpeedController panelDeployMotor;
    private SpeedController panelSpeedMotor;
    private DigitalInput panelMaxDetector;
    private DigitalInput panelMinDetector;
    private ColorSensorV3 panelColorDetector;
    private AnalogInput panelAngleDetector;
    private int numSpins;
    private Colors desiredColor;

    public ControlPanel(RobotConfig config) {
        this.panelDeployMotor = config.getPanelDeployMotor();
        this.panelSpeedMotor = config.getPanelSpinMotor();
        this.panelMaxDetector = config.getPanelMaxDetector();
        this.panelMinDetector = config.getPanelMinDetector();
        //this.panelColorDetector = config.getPanelColorDetector();
        this.panelAngleDetector = config.getPanelAngleDetector();
    }

    public void setManipulator(IAxis panelDeployAxis){
        double deployAxisValue = panelDeployAxis.getValue();
        panelDeployMotor.set((panelMaxDetector.get() && deployAxisValue > 0) || (panelMinDetector.get() && deployAxisValue < 0) ? 0 : deployAxisValue);
    }

    public void setDesiredColor(Colors color){
        this.desiredColor = color;
    }

    public void setNumSpins(int numSpins){
        this.numSpins = numSpins;
    }

    public void controlWheelSpin(){

    }

}