package it.unibo.exceptions.fakenetwork.impl;

import java.io.IOException;

public class NetworkException extends IOException {
    // default costructor
    public NetworkException() {
        super("Network error: no response");
    }

    // costructor with message
    public NetworkException(final String message) {
        super("Network error while sending message: " + message);
    }
}
