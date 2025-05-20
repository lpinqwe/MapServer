package baseClasses;

import interfaces.HealthInterface;

import java.awt.*;

abstract class HealthBasic implements HealthInterface {
    boolean isOk;
    @Override
    public boolean checkHealth() {
        return isOk;
    }
}
