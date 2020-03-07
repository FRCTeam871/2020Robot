package frc.robot.utils;

import java.util.EnumMap;
import java.util.Map;

import com.team871.hid.ButtonTypes;
import com.team871.hid.IButton;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * ColorButton
 */
public class ColorButton implements IButton {
    private IButton colorButton;
    private Map<Colors, Colors> fieldToRobotConvert;

    public ColorButton(IButton colorButton) {
        this.colorButton = colorButton;
        this.fieldToRobotConvert = new EnumMap<Colors, Colors>(Colors.class);

        fieldToRobotConvert.put(Colors.GREEN,  Colors.YELLOW);
        fieldToRobotConvert.put(Colors.RED,    Colors.BLUE);
        fieldToRobotConvert.put(Colors.YELLOW, Colors.GREEN);
        fieldToRobotConvert.put(Colors.BLUE,   Colors.RED);
        fieldToRobotConvert.put(Colors.NONE,   Colors.NONE);
    }

    @Override
    public boolean getValue() {
        return colorButton.getValue();
    }

    public Colors getButtonColor() {
        Colors buttonColor = Colors.NONE;
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0){
            switch (gameData.charAt(0)){
                case 'B' : {
                    buttonColor = Colors.BLUE;
                    break;
                }
                case 'G' : {
                    buttonColor = Colors.GREEN;
                    break;
                }
                case 'R' : {
                    buttonColor = Colors.RED;
                    break;
                }
                case 'Y' : {
                    buttonColor = Colors.YELLOW;
                    break;
                }
                default : {
                    buttonColor = Colors.NONE;
                    break;
                }
            }
        }
        return fieldToRobotConvert.get(buttonColor);
    }

    @Override
    public ButtonTypes getMode() {
        return colorButton.getMode();
    }

    @Override
    public boolean getRaw() {
        return colorButton.getRaw();
    }

    @Override
    public void setMode(ButtonTypes mode) {
        colorButton.setMode(mode);
    }
}