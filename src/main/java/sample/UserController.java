package sample;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * Данный метод вызывается с помощью reflection'a, поэтому Spring позволяет инжектить в него аргументы.
     * Подробнее можно почитать в сорцах к аннотации {@link RequestMapping}. Там описано как заинжектить различные атрибуты http-запроса.
     * Возвращаемое значение можно так же варьировать. Н.п. Если отдать InputStream, можно стримить музыку или видео
     */
    @RequestMapping(path = "/api/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    public String getMsg(@PathVariable(name = "UserId") int userId) {
        return "{\"userId\":" + userId + '}';
    }

    /**
     * Конструктор тоже будет вызван с помощью reflection'а. Другими словами, объект создается через ApplicationContext.
     * Поэтому в нем можно использовать DI. Подробнее про это расскажу на лекции.
     */

    public UserController() {
    }


    private String name = null;

    @RequestMapping(path = "/api/hi/{UserName}", method = RequestMethod.POST, produces = "application/json")
    public String regUser(@PathVariable(name = "UserName") String userName) {
        this.name = userName;
        return "{\"name\":" + userName + "}";
    }

    @RequestMapping(path = "/api/who_i_am", method = RequestMethod.GET, produces = "application/json")
    public String answerName() {
        if (name == null) {
            return "{\"name\":null, \"msg\":Who are you?\"}";
        } else {
            return "{\"name\":\""+ name +"\", \"msg\":\"Hi, " + name + ". It's a pleasure to java with you\"}";
        }
    }
}