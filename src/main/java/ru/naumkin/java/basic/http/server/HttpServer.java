package ru.naumkin.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private final Logger logger = LogManager.getLogger(HttpServer.class.getName());
    private final ExecutorService threadPool;
    private Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.threadPool = Executors.newFixedThreadPool(4);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);

            while (true) {
                threadPool.execute(() -> {
                    try (Socket socket = serverSocket.accept()) {
                        byte[] buffer = new byte[8192];
                        logger.info("подключился клиент: " + "port: " + socket.getPort());
                        int n = socket.getInputStream().read(buffer);
                        String rawRequest = new String(buffer, 0, n);
                        HttpRequest httpRequest = new HttpRequest(rawRequest);
                        dispatcher.execute(httpRequest, socket.getOutputStream());
                    } catch (IOException e) {
                        logger.error(e);
                    }
                });
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
