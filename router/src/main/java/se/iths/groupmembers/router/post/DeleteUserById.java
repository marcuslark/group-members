package se.iths.groupmembers.router.post;

import com.google.gson.Gson;
import se.iths.db.JPA;
import se.iths.groupmembers.spi.Page;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;

public class DeleteUserById implements Page {

    private final String path;

    public DeleteUserById(){
        path="deleteuserbyid";
    }

    @Override
    public void doGet(Socket socket, boolean head) {
        doPost(socket, "", head);
    }

    @Override
    public void doPost(Socket socket, String body, boolean head) {
        JPA dao = new JPA();
        Map<String, String> map = new Gson().fromJson(body, Map.class);
        dao.removeById(Integer.parseInt(map.get("id")));
        try {
            String statusString = "{\n\"success\":\"ok\"\n}";
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Content-Type: application/json");
            printStream.println("Content-Length: " + (statusString.length()));
            if (!head) {
                printStream.println();
                printStream.println(statusString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPath() {
        return path;
    }
}