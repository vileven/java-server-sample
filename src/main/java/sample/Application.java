package sample;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {

    public static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        //Создаем сервер jetty. Сначала идет конфигурирование, запуск только после вызова start()
        final Server server = new Server(PORT);
        //В доках написано, что этот класс должен был бы называться ServletContext, но это название было занято уже раньше
        //По смыслу же это контейнер для сервлетов, который умеет привязывать сервлет к определенной маске
        final ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //Адаптер над классом Servlet. Нужен, потому что Servlet - это javax.servlet.http.Servlet. Т.е. входит в java API,
        // а не в библиотеку jetty
        final ServletHolder servletHolder = new ServletHolder(new UserServlet());
        servletContextHandler.addServlet(servletHolder, "/api/user");
        server.setHandler(servletContextHandler);
        server.start();
        //Усыпляет главный поток до тех пор, пока не закончат выполнения все потоки jetty, т.е. до конца выполнения программы
        server.join();
    }
}
