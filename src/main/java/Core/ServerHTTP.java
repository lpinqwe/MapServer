package Core;// Java Program to Set up a Basic HTTP Server

import Fabrics.CommandFactory;
import ServerHTTPHandlers.CommandHandler;
import ServerHTTPHandlers.HealthHandler;
import ServerHTTPHandlers.MetricsHandler;
import ServerHTTPHandlers.ShowImageHandler;
import baseClasses.HealthBasic;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import commands.CommandCoconut;
import interfaces.ReadyCheckInterface;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ServerHTTP extends HealthBasic implements ReadyCheckInterface {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    CommandFactory factory;
    private HttpServer server;

    public ServerHTTP(int port) throws IOException {

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/health", new HealthHandler());
        server.createContext("/metrics", new MetricsHandler());
        //
        server.createContext("/image", new CommandHandler());
        server.createContext("/command_post", new CommandHandler());
        server.createContext("/coconut", new CommandHandler());
        server.setExecutor(executor);

        //todo make context factory
    }

    public void start() {
        server.start();
        System.out.println("Server started on http://localhost:" + server.getAddress().getPort());
    }

    private boolean ready = false;

    @Override
    public boolean checkReady() {
        return this.ready;
    }

    @Override
    public void imReady() {
        this.ready = true;
    }

    @Override
    public void notReady() {
        this.ready = false;
    }

    // define a custom HttpHandler
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
//            // handle the request
//            String response = "Hello, this is a simple HTTP server response!";
//            exchange.sendResponseHeaders(200, response.length());
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
        }
    }
}