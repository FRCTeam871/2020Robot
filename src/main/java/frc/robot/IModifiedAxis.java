package frc.robot;

import com.team871.hid.IAxis;

/**
 * IModifiedAxis
 */
public interface IModifiedAxis extends IAxis {

    @Override
    public default double getRaw() {
        return 0;
    }
  
    @Override
    public abstract double getValue();
    
}