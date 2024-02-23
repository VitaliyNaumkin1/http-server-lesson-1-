package ru.naumkin.java.basic.http.server.processors;

import ru.naumkin.java.basic.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\n\r\n<html><body><h1>Получен неизвестный запрос</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
