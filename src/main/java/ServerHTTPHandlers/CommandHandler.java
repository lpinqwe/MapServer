package ServerHTTPHandlers;

import ClassesDTO.CommandDTO;
import ClassesDTO.ImageRequestDTO;
import Fabrics.CommandFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.ResponseWrapper;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandHandler implements HttpHandler {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            return;
        }

        executor.submit(() -> {
            try {
                //Todo json to DTO CommandDTO, factory call by cmdtype,answer-send answer
                ObjectMapper mapper = new ObjectMapper();
                CommandDTO dto = mapper.readValue(exchange.getRequestBody(), CommandDTO.class);
                CommandFactory command = new CommandFactory();
                ResponseWrapper resp = command.createCommand(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
