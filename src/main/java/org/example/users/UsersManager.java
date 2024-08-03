package org.example.users;

import org.example.users.User.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersManager {
    private HashMap<String, User> users;
    private User currentUser;
    public UsersManager(){
        users = new HashMap<>();
        users.put("admin", new User("Иван", "Иванов", "admin", "qwerty",
                "+12345678900", UserType.ADMINISTRATOR));
    }
    public String signin(String login, String password){
        if (users.containsKey(login) && users.get(login).getPassword().equals(password)){
            currentUser = users.get(login);
            return "Доброго времени суток, " + currentUser.getFirstName() + ".";
        } else return "Введены неверные имя пользователя или пароль";
    }
    public String logout(){
        if (currentUser != null) {
            currentUser = null;
            return "Сессия успешно закончена";
        } else return "Нет активных сессий";
    }
    public String createUser(String firstName, String secondName,
                             String login, String password,
                             String phoneNumber, UserType userType){
        if (!users.containsKey(login))
        {
            users.put(login, new User(firstName, secondName, login, password, phoneNumber, userType));
            return "Пользователь успешно зарегестрирован";
        }
        else return "Данный логин уже занят";
    }
    public String getUserInfo(String firstName, String secondName){
        if (currentUser != null && currentUser.getUserType() != UserType.CUSTOMER){
            ArrayList<User> usersList = getUsers(firstName, secondName);
            if (!usersList.isEmpty()){
                String response = "\n";
                if (currentUser.getUserType() == UserType.ADMINISTRATOR){ //админ видит всех пользователей в списке
                    for (User user : usersList) {
                        response += "Имя: " + user.getFirstName() + "\n" +
                                "Фамилия: " + user.getSecondName() + "\n" +
                                "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                "Должность: " + user.getUserType() + "\n";
                    }
                } else if (currentUser.getUserType() == UserType.MANAGER){ //манагер видит только список покупателей
                    for (User user : usersList) {
                        if (user.getUserType() == UserType.CUSTOMER){
                            response += "Имя: " + user.getFirstName() + "\n" +
                                    "Фамилия: " + user.getSecondName() + "\n" +
                                    "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                    "Статус: " + user.getUserType() + "\n";
                        }
                    }
                }
                if (response.length() > 2) return response;
                else return "Данный пользователь не найден";

            } else return "Данный пользователь не найден";
        } else if (currentUser != null && currentUser.getUserType() == UserType.CUSTOMER){
            return "Недостаточно прав для данного действия";
        } else return "Для данного действия требуется авторизация";
    }

    private ArrayList<User> getUsers(String firstName, String secondName) {
        ArrayList<User> usersList = new ArrayList<>();
        for (User value : users.values()) {
            if (value.getFirstName().equals(firstName) && value.getSecondName().equals(secondName))
                usersList.add(value);
        }
        return usersList;
    }

    public String getUserInfo(){
        if (currentUser != null) {
            return "Ваши персональные данные: " + "\n" +
                    "Имя: " + currentUser.getFirstName() + "\n" +
                    "Фамилия: " + currentUser.getSecondName() + "\n" +
                    "Номер телефона: " + currentUser.getPhoneNumber() + "\n" +
                    "Логин: " + currentUser.getLogin() + "\n" +
                    "Пароль: " + currentUser.getPassword() + "\n";
        } else return "Для данного действия требуется авторизация";
    }
}
