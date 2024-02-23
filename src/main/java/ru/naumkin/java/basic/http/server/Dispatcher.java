package ru.naumkin.java.basic.http.server;

import ru.naumkin.java.basic.http.server.processors.HelloWorldRequestProcessor;
import ru.naumkin.java.basic.http.server.processors.OperationAddRequestProcessor;
import ru.naumkin.java.basic.http.server.processors.RequestProcessor;
import ru.naumkin.java.basic.http.server.processors.UnknownRequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private RequestProcessor unknownRequestProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.router.put("/add", new OperationAddRequestProcessor());
        this.router.put("/hello_world", new HelloWorldRequestProcessor());
        this.unknownRequestProcessor = new UnknownRequestProcessor();
    }

    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        if (!router.containsKey(httpRequest.getUri())) {
            unknownRequestProcessor.execute(httpRequest, output);
            return;
        }
        router.get(httpRequest.getUri()).execute(httpRequest, output);
    }
}
