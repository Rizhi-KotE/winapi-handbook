package client.gui;

import org.apache.log4j.Logger;

import java.beans.EventHandler;

public class ErrorHandler {
    static Logger log = Logger.getLogger(EventHandler.class);
    private final WinApiHandbookReactor reactor;

    public ErrorHandler(WinApiHandbookReactor reactor) {
        this.reactor = reactor;
        reactor.exceptionEventSource.subscribe(e -> log.debug("", e));
    }
}
