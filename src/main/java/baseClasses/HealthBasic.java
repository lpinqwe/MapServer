package baseClasses;

import interfaces.HealthInterface;
import utils.HealthCheck;

import java.awt.*;


public class HealthBasic implements HealthInterface {
    public HealthBasic(){
        new HealthCheck().registerClass(this);
    }
    public boolean isOk;
    @Override
    public boolean checkHealth() {
        return isOk;
    }
}
