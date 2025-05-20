package ServerHTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.HealthCheck;

import java.io.IOException;
import java.io.OutputStream;

public class HealthHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean response = new HealthCheck().check();
        String ans;
        if (response){
            ans = "all ok";
        }else{
            ans="bad";
        }
        exchange.sendResponseHeaders(200, ans.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(ans.getBytes());
        }
    }
}
