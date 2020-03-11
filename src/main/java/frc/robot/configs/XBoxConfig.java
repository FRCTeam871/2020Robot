package frc.robot.configs;

import java.util.Arrays;

import com.team871.hid.ButtonTypes;
import com.team871.hid.GenericJoystick;
import com.team871.hid.IAxis;
import com.team871.hid.IButton;
import com.team871.hid.joystick.XBoxAxes;
import com.team871.hid.joystick.XBoxButtons;

import frc.robot.utils.ColorButton;
import frc.robot.utils.IModifiedAxis;

    //This was originally set to the Composite Trigger Axis, but was changed because it is broken
    //TODO: Fix the Composite Trigger Axis in the RobotLib CompoundAxis Class

public class XBoxConfig implements JoystickConfig {
    private GenericJoystick<XBoxButtons, XBoxAxes> driveController, systemsController;
    private IAxis speedAxis;
    private IAxis rotationAxis;
    private IAxis conveyorAxis;
    private ColorButton colorButton;

    public XBoxConfig() {
        driveController = new GenericJoystick<>(0, Arrays.asList(XBoxButtons.values()),
                Arrays.asList(XBoxAxes.values()));
        systemsController = new GenericJoystick<>(1, Arrays.asList(XBoxButtons.values()),
                Arrays.asList(XBoxAxes.values()));
        systemsController.getButton(XBoxButtons.LBUMPER).setMode(ButtonTypes.TOGGLE);
        systemsController.getButton(XBoxButtons.RBUMPER).setMode(ButtonTypes.RISING);
        systemsController.getButton(XBoxButtons.START).setMode(ButtonTypes.TOGGLE);
        systemsController.getButton(XBoxButtons.BACK).setMode(ButtonTypes.RISING);
        systemsController.getButton(XBoxButtons.RIGHTSTICK).setMode(ButtonTypes.RISING);
        systemsController.getButton(XBoxButtons.B).setMode(ButtonTypes.TOGGLE);
        systemsController.getButton(XBoxButtons.Y).setMode(ButtonTypes.RISING);
        systemsController.getButton(XBoxButtons.X).setMode(ButtonTypes.MOMENTARY);
        colorButton = new ColorButton(systemsController.getButton(XBoxButtons.A));
        colorButton.setMode(ButtonTypes.RISING);

        
        systemsController.getAxis(XBoxAxes.LEFTY).setDeadband(.15);
        systemsController.getAxis(XBoxAxes.RIGHTY).setDeadband(0.1);
        
        // Unjam button
        driveController.getButton(XBoxButtons.LBUMPER).setMode(ButtonTypes.MOMENTARY);

        // Vomit Button
        driveController.getButton(XBoxButtons.RBUMPER).setMode(ButtonTypes.MOMENTARY);
        driveController.getButton(XBoxButtons.X).setMode(ButtonTypes.TOGGLE);

        // Nom Nom button
        driveController.getButton(XBoxButtons.A).setMode(ButtonTypes.MOMENTARY);
        driveController.getAxis(XBoxAxes.LEFTY).setDeadband(.16);
        driveController.getAxis(XBoxAxes.RIGHTY).setDeadband(.0);
        driveController.getAxis(XBoxAxes.RIGHTX).setDeadband(.15);

        speedAxis = (IModifiedAxis) () -> {
            return Math.pow(driveController.getAxis(XBoxAxes.LEFTY).getValue(), 3);
        };
        rotationAxis = (IModifiedAxis) () -> {
            return ((0.5 - 0.4 * Math.sqrt(Math.abs(speedAxis.getValue())))
                    * driveController.getAxis(XBoxAxes.RIGHTX).getValue());
        };
        conveyorAxis = (IModifiedAxis) () -> {
            return (driveController.getAxis(XBoxAxes.RTRIGGER).getValue()) - (driveController.getAxis(XBoxAxes.LTRIGGER).getValue());
        };

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
    public IAxis getConveyorAxis() {
        return conveyorAxis;
    }
    
    @Override
    public IButton getVomitButton() {
        return driveController.getButton(XBoxButtons.RBUMPER);
    }

    @Override
    public IButton getClimbReleaseButton() {
        return systemsController.getButton(XBoxButtons.START);
    }

    @Override
    public IAxis getClimbWinchAxis() {
        return systemsController.getAxis(XBoxAxes.RIGHTY);
    }

    @Override
    public IButton getPanelDeployButton() {
        return systemsController.getButton(XBoxButtons.LBUMPER);
    }

    @Override
    public IButton getPanelSpinButton() {
        return systemsController.getButton(XBoxButtons.Y);
    }

    @Override
    public ColorButton getColorButton() {
        return colorButton;
    }

    @Override
    public IButton getXtraSpinButton() {
        return systemsController.getButton(XBoxButtons.X);
    }

    @Override
    public IButton getNomNomButton() {
        return driveController.getButton(XBoxButtons.A);
        //return systemsController.getButton(XBoxButtons.B);
    }

    @Override
    public IAxis getDebugAxis() {
        return systemsController.getAxis(XBoxAxes.LEFTY);
    }

    @Override
    public IButton getUnJamButton() {
        return driveController.getButton(XBoxButtons.LBUMPER);
    }
}