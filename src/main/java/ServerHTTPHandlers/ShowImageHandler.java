package ServerHTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowImageHandler implements HttpHandler {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            return;
        }

        executor.submit(() -> {
            try {
                //Note delete this class
                //Todo json to DTO CommandDTO, factory call by cmdtype,answer-send answer
                //Todo return html with image from command or payload if no image
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
