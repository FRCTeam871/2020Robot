package frc.robot.utils;

import com.team871.hid.IAxis;

/**
 * IModifiedAxis
 * 
 * NO thanks.  Delete me please.
 */
public interface IModifiedAxis extends IAxis {

    @Override
    public default double getRaw() {
        return getValue();
    }
  
    @Override
    public abstract double getValue();
    
}