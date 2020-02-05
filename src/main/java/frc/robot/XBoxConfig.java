package frc.robot;

import java.util.Arrays;

import com.team871.hid.ButtonTypes;
import com.team871.hid.GenericJoystick;
import com.team871.hid.IAxis;
import com.team871.hid.HIDButton;
import com.team871.hid.IAxis;
import com.team871.hid.joystick.XBoxAxes;
import com.team871.hid.joystick.XBoxButtons;

public class XBoxConfig implements JoystickConfig {
    private GenericJoystick<XBoxButtons, XBoxAxes> driveController, panelController; 
    private IAxis speedAxis;
    private IAxis rotationAxis;

    public XBoxConfig(){
        driveController = new GenericJoystick<>(0, Arrays.asList(XBoxButtons.values()), Arrays.asList(XBoxAxes.values()));
        panelController = new GenericJoystick<>(1, Arrays.asList(XBoxButtons.values()), Arrays.asList(XBoxAxes.values()));
        panelController.getButton(XBoxButtons.LBUMPER).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.RBUMPER).setMode(ButtonTypes.RISING);
        driveController.getButton(XBoxButtons.X).setMode(ButtonTypes.TOGGLE);
        driveController.getButton(XBoxButtons.A).setMode(ButtonTypes.MOMENTARY);
        driveController.getButton(XBoxButtons.START).setMode(ButtonTypes.RISING);
        driveController.getButton(XBoxButtons.BACK).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.RIGHTSTICK).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.B).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.Y).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.X).setMode(ButtonTypes.RISING);
        panelController.getButton(XBoxButtons.A).setMode(ButtonTypes.RISING);
        
        driveController.getAxis(XBoxAxes.LEFTY).setDeadband(.16);
        driveController.getAxis(XBoxAxes.RIGHTY).setDeadband(.0);
        driveController.getAxis(XBoxAxes.RIGHTX).setDeadband(.15);

        speedAxis =  (IModifiedAxis) () -> { return Math.pow(driveController.getAxis(XBoxAxes.LEFTY).getValue(), 3); };
        rotationAxis = (IModifiedAxis) () -> { return ((0.5 - 0.4 * Math.sqrt(Math.abs(speedAxis.getValue()))) * driveController.getAxis(XBoxAxes.RIGHTX).getValue()); };
    }
    @Override
    public IAxis getDriveSpeedAxis() {
        return speedAxis;
    }

    @Override
    public IAxis getDriveRotationAxis() {
        return rotationAxis; 
    }

    @Override
    public HIDButton getGrabDeployButton() {
        return panelController.getButton(XBoxButtons.LBUMPER);
    }

    @Override
    public HIDButton getGrabActivateButton() {
        return panelController.getButton(XBoxButtons.RBUMPER);
    }

    @Override
    public HIDButton getPutConveyorToggleButton() {
        return driveController.getButton(XBoxButtons.X);
    }

    @Override
    public HIDButton getBallReleaseButton() {
        return driveController.getButton(XBoxButtons.A);
    }

    @Override
    public HIDButton getClimbStateChangeButton() {
        return driveController.getButton(XBoxButtons.START);
    }

    @Override
    public HIDButton getResetClimberButton() {
        return driveController.getButton(XBoxButtons.BACK);
    }

    @Override
    public IAxis getPanelDeployAxis() {
        return panelController.getAxis(XBoxAxes.TRIGGER);
    }

    @Override
    public HIDButton getPanelSpinButton() {
        return panelController.getButton(XBoxButtons.RIGHTSTICK);
    }

    @Override
    public HIDButton getSpinToRed() {
        return panelController.getButton(XBoxButtons.B);
    }

    @Override
    public HIDButton getSpinToBlue() {
        return panelController.getButton(XBoxButtons.X);
    }

    @Override
    public HIDButton getSpinToYellow() {
        return panelController.getButton(XBoxButtons.Y);
    }

    @Override
    public HIDButton getSpinToGreen() {
        return panelController.getButton(XBoxButtons.A);
    }

    @Override
    public IAxis getLeftSpeedAxis() {
        return driveController.getAxis(XBoxAxes.LEFTY);
    }

    @Override
    public IAxis getRightSpeedAxis() {
        return driveController.getAxis(XBoxAxes.RIGHTY);
    }
}