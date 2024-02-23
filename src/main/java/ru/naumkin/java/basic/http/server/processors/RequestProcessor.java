package ru.naumkin.java.basic.http.server.processors;

import ru.naumkin.java.basic.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest httpRequest, OutputStream output) throws IOException;
}
