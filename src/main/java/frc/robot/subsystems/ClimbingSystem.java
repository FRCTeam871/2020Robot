package frc.robot.subsystems;

import com.team871.hid.IAxis;
import com.team871.hid.IButton;
import com.team871.io.actuator.ISolenoid;
import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;

/**
 * ClimbingSystem
 */
public class ClimbingSystem {
    private ISolenoid deployPiston;
    private SpeedController winch;
    private DigitalSensor doneClimbingSensor;
    private Relay winchReleaseRelay;
    private double lastAxisVal = 0;
    private long lastSignChange = 0;

    private NetworkTableEntry winchSpeedEntry;
    private NetworkTableEntry doneClimbingSensorEntry;
    private NetworkTableEntry deployPistonEntry;

    public ClimbingSystem(RobotConfig config){
        this.deployPiston = config.getClimbReleasePiston();
        this.winch = config.getClimbWinchMotor();
        this.winchReleaseRelay = config.getReleaseRelay();
        this.doneClimbingSensor = config.getFinishClimbSensor();

        this.winchSpeedEntry = config.getNetworkTab("climbSysytem").getEntry("winchSpeed");
        this.doneClimbingSensorEntry = config.getNetworkTab("climbSysytem").getEntry("doneClimbingSensor");
        this.deployPistonEntry = config.getNetworkTab("climbSysytem").getEntry("deployPiston");
    }

    private void deploy(IButton climbReleaseButton) {
        // "BUTT" ~Andy Baranec
        //TODO: Resurect the thing below to make a time limit
        if(climbReleaseButton.getValue() /**&& DriverStation.getInstance().getMatchTime() <= 30 */){
            deployPiston.set(ISolenoid.Value.Forward);
        } else{
            deployPiston.set(ISolenoid.Value.Reverse);
        }
    }
    private void hoist(IAxis winchAxis){
        double axisVal = winchAxis.getValue();
        if(axisVal <= 0) {
            if(lastAxisVal >= 0){
                lastSignChange = System.currentTimeMillis();
            }
            if((System.currentTimeMillis() - lastSignChange) >= 1500){
                winchReleaseRelay.set(Value.kOff);
            }
            winch.set(axisVal);
        } else if(axisVal > 0){
            if(lastAxisVal <= 0){
                lastSignChange = System.currentTimeMillis();
            }

            if((System.currentTimeMillis() - lastSignChange) >= 1000){
                winch.set(winchAxis.getValue());
            }
            winchReleaseRelay.set(Value.kReverse);
        }
        lastAxisVal = axisVal;
    }

    public void update(JoystickConfig controller){
        deploy(controller.getClimbReleaseButton());
        hoist(controller.getClimbWinchAxis());

        winchSpeedEntry.setDouble(controller.getClimbWinchAxis().getValue());
        doneClimbingSensorEntry.setBoolean(doneClimbingSensor.get());
        deployPistonEntry.setBoolean(controller.getClimbReleaseButton().getValue());
    }
}