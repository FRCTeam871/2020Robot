package frc.robot.utils.sensors;

import java.text.DecimalFormat;
import java.util.EnumMap;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.utils.Colors;

/**
 * ColorSensor
 */
public class ColorSensor {
    EnumMap<Colors, Color> colorMap;
    DecimalFormat format = new DecimalFormat("#.000");

    private final ColorSensorV3 colorSensor;
    private long lastColorChange;

    private double[] sdvals = new double[3];

    private Colors currentColor = Colors.NONE;

    public ColorSensor(final ColorSensorV3 colorSensor){
        this.colorSensor = colorSensor;
        this.lastColorChange = System.currentTimeMillis();
        colorMap = new EnumMap<>(Colors.class);
        colorMap.put(Colors.RED, new Color(.399, .414, .187));
        colorMap.put(Colors.GREEN, new Color(.212, .537, .251));
        colorMap.put(Colors.BLUE, new Color(.175, .453, .372));
        colorMap.put(Colors.YELLOW, new Color(.305, .542, .154));
        colorMap.put(Colors.NONE, new Color(0, 0, 0));
    }
    
    /**
     * Compares the RGB values that the sensor is reading to a sample of
     * values. From this it determines the error between them and chooses
     * the color with the closest RGB values as its current color. This is
     * regulated to only change the color value after a set time so that the
     * color sensor doesn't stop on the border between colors on the
     * Color Wheel.
     * @return the color with the closest RGB values to the sample values.
     */
    public Colors getColor(){
        final double redReading = colorSensor.getColor().red;
        final double greenReading = colorSensor.getColor().green;
        final double blueReading = colorSensor.getColor().blue;

        Colors theColor = Colors.NONE;
        double smallestError = Double.MAX_VALUE;
        for(Colors color : Colors.values()) {
            Color currentColor = colorMap.get(color);
            double error = Math.abs(redReading - currentColor.red) + Math.abs(greenReading - currentColor.green) + Math.abs(blueReading - currentColor.blue);

            if(error < smallestError) {
                smallestError = error;
                theColor = color;
            }
        }

        if(theColor != Colors.NONE && theColor != currentColor && System.currentTimeMillis() - lastColorChange > 500) {
            lastColorChange = System.currentTimeMillis();
            currentColor = theColor;
        }

        sdvals[0] = colorSensor.getRed();
        sdvals[1] = colorSensor.getGreen();
        sdvals[2] = colorSensor.getBlue();
        SmartDashboard.putNumberArray("ColorRaw", sdvals);
        sdvals[0] = redReading;
        sdvals[1] = greenReading;
        sdvals[2] = blueReading;
        SmartDashboard.putNumberArray("ColorProc", sdvals);
        SmartDashboard.putString("DetectedColor", currentColor.toString());
        return theColor;
    }
}