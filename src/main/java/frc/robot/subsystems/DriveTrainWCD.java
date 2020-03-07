package frc.robot.subsystems;

import com.team871.hid.IAxis;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.configs.JoystickConfig;
import frc.robot.configs.RobotConfig;

public class DriveTrainWCD{

   private SpeedController leftMotors;
   private SpeedController rightMotors;

   private NetworkTableEntry speedLeftEntry;
   private NetworkTableEntry speedRightEntry;

    public DriveTrainWCD(RobotConfig config) {
       this.leftMotors  = config.getLeftMotors();
       this.rightMotors = config.getRightMotors();

       this.speedLeftEntry   = config.getNetworkTab("driveTrain").getEntry("speedLeft");
       this.speedRightEntry  = config.getNetworkTab("driveTrain").getEntry("speedRight");
    }

    /**
     * Drives the robot with an axis to control the speed and an axis to control the rotation. 
     * @param controller
     */
    public void carDrive(JoystickConfig controller) {
        double speed = controller.getDriveSpeedAxis().getValue();               //The fancy math that makes this controllable is done in the DefaulfConfig as a lambda
        double rotation = controller.getDriveRotationAxis().getValue();         //The fancy math that makes this controllable is done in the DefaulfConfig as a lambda
        double rightRotation = 0;
        double leftRotation = 0;

        rightRotation = rotation;
        leftRotation = rotation * -1;
       
        if(Math.abs(speed) + Math.abs(rotation) > 1){
           double overflow = Math.abs(speed) + Math.abs(rotation) - 1;

           leftRotation = leftRotation + overflow;
           rightRotation = rightRotation + overflow;
        }
        
        speedLeftEntry.setDouble(speed + leftRotation);
        speedRightEntry.setDouble(speed + rightRotation);
        leftMotors.set(speed + leftRotation);
        rightMotors.set(speed + rightRotation);
    }

    /**
     * Drives the robot with an axis to control the speed of the left side of the robot and an axis to control the speed of the right side of the robot.
     * Please don't use as it is bad.  See {@link #carDrive}.
     * @param driveSpeedLeftAxis
     * @param driveSpeedRightAxis
     */
    public void tankDrive(IAxis driveSpeedLeftAxis, IAxis driveSpeedRightAxis){
        leftMotors.set(driveSpeedLeftAxis.getValue());
        rightMotors.set(driveSpeedRightAxis.getValue());
    }
}