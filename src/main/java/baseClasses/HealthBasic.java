package baseClasses;

import interfaces.HealthInterface;

import java.awt.*;

public abstract class HealthBasic implements HealthInterface {
    public boolean isOk;
    @Override
    public boolean checkHealth() {
        return isOk;
    }
}
