package utils;

import annotations.Singleton;
import interfaces.ReadyCheckInterface;

import java.util.ArrayList;

@Singleton
public class ReadyCheck {

    ArrayList<ReadyCheckInterface> instanceToCheck = new ArrayList<ReadyCheckInterface>();

    //    public void HealthInterface(HealthInterface classToRegister) {
//        this.instanceToCheck.add(classToRegister);
//    }
    public void registerClass(ReadyCheckInterface classToRegister) {
        this.instanceToCheck.add(classToRegister);
    }

    public boolean check() {
        for (ReadyCheckInterface item : this.instanceToCheck) {
            if (!item.checkReady()) {
                return false;
            }
        }
        return true;
    }
}