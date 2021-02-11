package se.iths.groupmembers.router.get.html;

import se.iths.groupmembers.router.LoadHandler;
import se.iths.groupmembers.router.Status;
import se.iths.groupmembers.spi.Page;

import java.net.Socket;

public class Error implements Page {

    private final String path;

    public Error() {
        path = "error";
    }

    @Override
    public void load(Socket socket, boolean head) {
        LoadHandler.load(socket, path, Status.OK, head);
    }

    @Override
    public void load(Socket socket, String body, boolean head) {
        load(socket, head);
    }

    @Override
    public String getPath() {
        return path;
    }
}
