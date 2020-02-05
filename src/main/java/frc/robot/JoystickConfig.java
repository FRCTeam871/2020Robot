package frc.robot;

import com.team871.hid.IAxis;
import com.team871.hid.HIDButton;

public interface JoystickConfig {
    /**
     * Retrieve axis used to control the speed of the drive train in car drive
     * @return IAxis described above
     */
    public IAxis getDriveSpeedAxis();
    
    /**
     * Retrieves axis used to control rotation of the drive train in car drive
     * @return IAxis described above
     */
    public IAxis getDriveRotationAxis();

    /**
     * Retrieves button used to deploy or retract the power cell collection mechanism
     * @return HIDbutton described above
     */
    public HIDButton getGrabDeployButton();

    /**
     * Retrieves button to activate and deactivate the power cell collection mechanism
     * @return HIDbutton described above
     */
    public HIDButton getGrabActivateButton();

    /**
     * Retrieves button used to activate and deactivate the conveyor belt
     * @return HIDbutton described above
     */
    public HIDButton getPutConveyorToggleButton();

    /**
     * Retrieves button used to release the power cells
     * @return HIDbutton described above
     */
    public HIDButton getBallReleaseButton();

    /**
     * Retrieves button to progress through the climbing sequence
     * @return HIDbutton described above
     */
    public HIDButton getClimbStateChangeButton();

    /**
     *  Retrieves button to retry the climb attempt
     * @return HIDbutton described above
     */
    public HIDButton getResetClimberButton();

    /**
     * Retrieves axis to deploy and undeploy the control panel manipulator
     * @return IAxis described above
     */
    public IAxis getPanelDeployAxis();

    /**
     * Retrieves button to spin the control panel
     * @return HIDbutton described above
     */
    public HIDButton getPanelSpinButton();

    /**
     * Retrieves button to spin the control panel to red
     * @return Hidbutton described above
     */
    public HIDButton getSpinToRed();

    /**
     * Retrieves button to spin the control panel to blue
     * @return HIDbutton described above
     */
    public HIDButton getSpinToBlue();

    /**
     * Retrieves button to spin the control panel to yellow
     * @return HIDbutton described above
     */
    public HIDButton getSpinToYellow();

    /**
     * Retrieves button to spin the control panel to green
     * @return HIDbutton described above
     */
    public HIDButton getSpinToGreen();

    /**
     * Retrieves axis used to control the left wheel of tank drive.
     * @return IAxis described above
     */
    public IAxis getLeftSpeedAxis();

    /**
     * Retrieves axis used to control the right wheel of tank drive
     * @return IAxis described above
     */
    public IAxis getRightSpeedAxis();
}