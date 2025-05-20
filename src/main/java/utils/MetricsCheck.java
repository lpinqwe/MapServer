package utils;
import io.prometheus.client.Counter;

public class MetricsCheck {
    static final Counter requestCounter = Counter.build()
            .name("http_requests_total")
            .help("Total HTTP requests.")
            .register();
    static final Counter errorCounter = Counter.build()
            .name("http_errors_total")
            .help("Total HTTP errors.")
            .register();
    public static void incRequest() {
        requestCounter.inc();
    }
    public static void incError() {
        errorCounter.inc();
    }

}
