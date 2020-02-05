package frc.robot;

import com.team871.hid.IAxis;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrainWCD{

   private SpeedController leftMotors, rightMotors;

   public DriveTrainWCD(RobotConfig config){
       this.leftMotors = config.getLeftMotors();
       this.rightMotors = config.getRightMotors();
       //this.leftMotors = leftMotors;
       //this.rightMotors = rightMotors;
   }

   public void carDrive(IAxis speedAxis, IAxis rotationAxis){
       double speed = modifySpeedCubed(speedAxis);
       double rotation = modifyRotationSqrt(speedAxis, rotationAxis);
       double rightRotation = 0;
       double leftRotation = 0;

        rightRotation = rotation;// * (speed < 0 ? 1 : -1);
        leftRotation = rotation * -1;// * (speed < 0 ? -1 : 1);
       
       if(Math.abs(speed) + Math.abs(rotation) > 1){
           double overflow = Math.abs(speed) + Math.abs(rotation) - 1;

           leftRotation = leftRotation + overflow;// * (speed > 0 ? -1 : 1);
           rightRotation = rightRotation + overflow;// * (speed > 0 ? -1 : 1);
       }

       leftMotors.set(speed + leftRotation);
       rightMotors.set(speed + rightRotation);
   }

   public void andyDrive(IAxis speedAx, IAxis rotationAx) {
        double speed = speedAx.getValue();
        double rotation = .35f*rotationAx.getValue();
    
        leftMotors.set(speed - rotation);
        rightMotors.set(speed + rotation);
    }


   public void tankDrive(IAxis driveSpeedLeftAxis, IAxis driveSpeedRightAxis){
       leftMotors.set(driveSpeedLeftAxis.getValue());
       rightMotors.set(driveSpeedRightAxis.getValue());
   }

   public double modifySpeedCubed(IAxis speedAxis){
       return Math.pow(speedAxis.getValue(), 3);
   }
   public double modifyRotationSqrt(IAxis speedAxis, IAxis rotationAxis){
       return (1-.9*Math.sqrt(Math.abs(modifySpeedCubed(speedAxis)))) * rotationAxis.getValue();
   }
}