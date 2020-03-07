package frc.robot.subsystems;

import com.team871.hid.IAxis;
import com.team871.hid.IButton;
import com.team871.io.sensor.DigitalSensor;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;

/**
 * ClimbingSystem
 */
public class ClimbingSystem {
    //private Solenoid deployPiston;
    private SpeedController winch;
    private DigitalSensor doneClimbingSensor;

    private NetworkTableEntry winchSpeedEntry;
    private NetworkTableEntry doneClimbingSensorEntry;
    private NetworkTableEntry deployPistonEntry;

    public ClimbingSystem(RobotConfig config){
        //this.deployPiston = config.getClimbReleasePiston();
        this.winch = config.getClimbWinchMotor();
        this.doneClimbingSensor = config.getFinishClimbSensor();

        this.winchSpeedEntry = config.getNetworkTab("climbSysytem").getEntry("winchSpeed");
        this.doneClimbingSensorEntry = config.getNetworkTab("climbSysytem").getEntry("doneClimbingSensor");
        this.deployPistonEntry = config.getNetworkTab("climbSysytem").getEntry("deployPiston");
    }

    private void deploy(IButton climbReleaseButton) {
        // BUTT ~Andy Baranec
        if(climbReleaseButton.getValue() && DriverStation.getInstance().getMatchTime() <= 30 ) {
            //deployPiston.set(false);
        }
    }
    
    private void hoist(IAxis winchAxis){
        if(/*!deployPiston.get() && */(doneClimbingSensor.get())) {
            winch.set(/**Math.min(0,*/winchAxis.getValue());
        }
    }

    public void update(JoystickConfig controller){
        deploy(controller.getClimbReleaseButton());
        hoist(controller.getClimbWinchAxis());

        winchSpeedEntry.setDouble(controller.getClimbWinchAxis().getValue());
        doneClimbingSensorEntry.setBoolean(doneClimbingSensor.get());
        deployPistonEntry.setBoolean(controller.getClimbReleaseButton().getValue());
    }
}