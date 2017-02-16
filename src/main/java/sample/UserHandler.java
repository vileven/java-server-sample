package sample;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserHandler {

    public void handle(String msg, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print("{\"msg\": " + msg + "}");
    }
}
