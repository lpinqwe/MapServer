package utils;

import interfaces.HealthInterface;

import java.util.ArrayList;

public class HealthCheck {

    ArrayList<HealthInterface> instanceToCheck = new ArrayList<HealthInterface>();

//    public void HealthInterface(HealthInterface classToRegister) {
//        this.instanceToCheck.add(classToRegister);
//    }
    public void registerClass(HealthInterface classToRegister){
        this.instanceToCheck.add(classToRegister);
    }
    public boolean check() {
        for (HealthInterface item : this.instanceToCheck) {
            if (!item.checkHealth()) {
                return false;
            }
        }
        return true;
    }
}
