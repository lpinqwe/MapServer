package utils;

public class MetricsCheckService {
    private static volatile MetricsCheckService instance;
    private static MetricsCheck metricsCheck;

    private MetricsCheckService() {
        this.metricsCheck = new MetricsCheck();
    }

    public MetricsCheckService getInstance() {

        if (instance == null) {
            synchronized (MetricsCheckService.class) {
                if (instance == null) {
                    instance = new MetricsCheckService();
                }
            }
        }
        return instance;

    }

    public static void incRequest() {
        metricsCheck.incRequest();
    }

    public static void incError() {
        metricsCheck.incError();
    }
}
