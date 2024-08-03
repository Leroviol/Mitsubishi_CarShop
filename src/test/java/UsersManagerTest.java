import org.junit.jupiter.api.Test;
import users.User;
import users.UsersManager;

import static org.junit.jupiter.api.Assertions.*;

class UsersManagerTest {

    UsersManager usersManager = new UsersManager();
    @Test
    void signin() {
        String exceptionLogin = "Введены неверные имя пользователя или пароль";
        String acceptLogin = "Доброго времени суток, Иван.";
        assertEquals(exceptionLogin, usersManager.signin("2134", "trewf"));
        assertEquals(exceptionLogin, usersManager.signin("admin", "124431"));
        assertEquals(exceptionLogin, usersManager.signin("aDmin", "qWerty"));
        assertEquals(acceptLogin, usersManager.signin("admin", "qwerty"));
    }

    @Test
    void logout() {
        String noSession = "Нет активных сессий";
        String sessionDone = "Сессия успешно закончена";
        assertEquals(noSession, usersManager.logout());
        usersManager.signin("admin", "qwerty");
        assertEquals(sessionDone, usersManager.logout());
        assertEquals(noSession, usersManager.logout());
    }

    @Test
    void createUser() {
        String success = "Пользователь успешно зарегестрирован";
        String bad = "Данный логин уже занят";
        assertEquals(success, usersManager.createUser("123", "132", "root", "qwertyy",
                "4565732637", User.UserType.MANAGER));
        assertEquals(bad, usersManager.createUser("gfsa", "1rha32", "root", "qwerthjryy",
                "456575332637", User.UserType.CUSTOMER));
    }

    @Test
    void getUserInfo() {
        String userAdmin = """
                Ваши персональные данные:
                Имя: Иван
                Фамилия: Иванов
                Номер телефона: +12345678900
                Логин: admin
                Пароль: qwerty
                """;
        usersManager.signin("admin", "qwerty");
        assertEquals(userAdmin, usersManager.getUserInfo());
        assertEquals("Сессия успешно закончена", usersManager.logout());
        assertEquals("Для данного действия требуется авторизация", usersManager.getUserInfo());

    }

    @Test
    void testGetUserInfo() {
//        for (int i = 0; i < 9; i++) {
//            String firstName = "first";
//            String secondName = "second";
//            if (i % 2 == 0){
//                usersManager.createUser(firstName, firstName, "user" + i, "password" + i,
//                        "12345678900", User.UserType.ADMINISTRATOR);
//            } else if (i % 3 == 0){
//                usersManager.createUser(firstName, firstName, "user" + i, "password" + i,
//                        "12345678900", User.UserType.MANAGER);
//            } else {
//                usersManager.createUser(firstName, firstName, "user" + i, "password" + i,
//                        "12345678900", User.UserType.CUSTOMER);
//            }
//        }
//        usersManager.signin("user2", "password2");
//        String userInfoSuccess =   "Имя: " + "first" + "\n" +
//                            "Фамилия: " + "second" + "\n" +
//                            "Номер телефона: " + "12345678900" + "\n" +
//                            "Статус: " +  + "\n";
//        assertEquals();
    }
}