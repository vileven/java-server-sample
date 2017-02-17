package sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UserServlet extends HttpServlet {

    private UserHandler handler;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (handler == null) {
            try {
                //Если бы у UserHandler конструктор зависил бы от каких-то аргументов, мы бы могли
                //получить их с помощью Constructor.getParameters() и перед вызовом найти или сконструировать уже их.
                //Это называется dependency injection или, коротко, DI
                final Constructor<UserHandler> constructor = UserHandler.class.getConstructor();
                System.out.println(Arrays.toString(constructor.getParameters()));

                handler = constructor.newInstance();

            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
        final String msg = req.getParameter("msg");
        try {
            //Этот метод мы можем найти не только по имени, но и, например, через аннотацию. И здесь так же возможно использовать DI
            final Method handle = UserHandler.class.getMethod("handle", String.class, HttpServletResponse.class);
            handle.invoke(handler, msg, resp);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}