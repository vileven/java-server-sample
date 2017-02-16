package sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class UserServlet extends HttpServlet {

    private UserHandler handler;


    /**
     * Получаем в качестве аргументов java-представления запроса и ответа.
     * Из особенностей можно отметить только метод  HttpSession getSession() в {@link HttpServletRequest}
     * Возвращает объект, который связан с текущим сеансом связи пользователя с сервером.
     * Идентификация производится по куке JSESSIONID,
     * которую автоматически вешает jetty. С помощью этого объекта можно
     * сохранять данные между запросами (см. setAttribute())
     *
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (handler == null) {
            handler = new UserHandler();
        }
        final String msg = req.getParameter("msg");
        handler.handle(msg, resp);
    }
}