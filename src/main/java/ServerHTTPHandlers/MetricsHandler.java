package ServerHTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.prometheus.client.exporter.common.TextFormat;
import io.prometheus.client.CollectorRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;

public class MetricsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", TextFormat.CONTENT_TYPE_004);
        exchange.sendResponseHeaders(200, 0); // 0 = chunked transfer

        try (OutputStream os = exchange.getResponseBody();
             Writer writer = new OutputStreamWriter(os)) {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        }
    }
}
