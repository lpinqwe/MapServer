package utils;

import interfaces.HealthInterface;

public class HealthCheckService {
    private static volatile HealthCheckService instance;
    private final HealthCheck healthCheck;

    private HealthCheckService() {
        this.healthCheck = new HealthCheck();
    }
    public void register(HealthInterface service) {
//        healthCheck.HealthInterface(service);
        healthCheck.registerClass(service);
    }
    public boolean checkAll() {
        return healthCheck.check();
    }
    public static HealthCheckService getInstance() {
        if(instance==null){
            synchronized (HealthCheckService.class){
                if(instance==null){
                    instance=new HealthCheckService();
                }
            }
        }
        return instance;
    }
}
