package ru.naumkin.java.basic.http.server;

public class MainApplication {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(Integer.parseInt((String) System.getProperties().getOrDefault("port", "8189")));
        server.start();
    }
}
