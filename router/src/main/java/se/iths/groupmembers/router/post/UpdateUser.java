package se.iths.groupmembers.router.post;

import com.google.gson.Gson;
import se.iths.db.JPA;
import se.iths.groupmembers.router.LoadHandler;
import se.iths.groupmembers.router.Status;
import se.iths.groupmembers.spi.Page;
import se.iths.groupmembers.spi.Path;

import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Path(path="updateuser")
public class UpdateUser implements Page {


    @Override
    public void doGet(Socket socket, boolean head, PrintStream printStream, Gson gson, JPA dao) {
        doPost(socket, "", head, printStream, gson, dao);
    }

    @Override
    public void doPost(Socket socket, String body, boolean head, PrintStream printStream, Gson gson, JPA dao) {
        Map<String, String> map = gson.fromJson(body, Map.class);
        dao.updateByFirstLast(
                map.get("firstName"),
                map.get("lastName"),
                map.get("newFirstName"),
                map.get("newLastName")
        );
        byte[] output = "{\n\"success\":\"ok\"\n}".getBytes(StandardCharsets.UTF_8);

        LoadHandler.print(printStream, output, Status.OK, "application/json", head);
    }



}
