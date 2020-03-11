package frc.robot.configs;

import com.team871.hid.IAxis;
import com.team871.hid.IButton;
import frc.robot.utils.ColorButton;

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
     * Retrieves button used to vomit power cells from the ball system
     * @return IButton described above
     */
    public IButton getVomitButton();

    /**
     * Retrieves axis to control the ball system conveyor
     * @return IAxis described above
     */
    public IAxis getConveyorAxis();

    /**
     * Retrieves button used to control the collection mechanism for the Power Cells
     * @return IButton described above
     */
    public IButton getNomNomButton();
    
    /**
     * Retrieves button to release the piston for the climbing mechanism
     * @return IButton described above
     */
    public IButton getClimbReleaseButton();

    /**
     * Retrieves button to activate the piston for the climbing winch
     * @return IButton described above
     */
    public IAxis getClimbWinchAxis();

    /**
     * Retrieves button to deploy and undeploy the control panel manipulator
     * @return IButton described above
     */
    public IButton getPanelDeployButton();

    /**
     * Retrieves button to spin the control panel
     * @return IButton described above
     */
    public IButton getPanelSpinButton();

    /**
     * Retrieves button to spin the control panel to a designated color
     * @return ColorButton described above
     */
    public ColorButton getColorButton();
    
    /**
     * Retrieves button to allow the user to manually spin the control panel
     * @return IButton described above
     */
    public IButton getXtraSpinButton();

    /**
     * Retriveves the axis used to control whatever it is assigned to for debug purposes
     * @return IAxis described above
     */
    public IAxis getDebugAxis();

    /**
     * Retreives the button to reverse the nom nom and vomit comit.
     * @return
     */
    IButton getUnJamButton();
}