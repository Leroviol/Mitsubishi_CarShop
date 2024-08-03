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
    public void signin(String login, String password){
        if (users.containsKey(login) && users.get(login).getPassword().equals(password)){
            currentUser = users.get(login);
            System.out.println("Доброго времени суток, " + currentUser.getFirstName() + ".");
        } else System.out.println("Введены неверные имя пользователя или пароль");
    }
    public void logout(){
        if (currentUser != null) {
            currentUser = null;
            System.out.println("Сессия успешно закончена");
        } else System.out.println("Нет активных сессий");
    }
    public void createUser(String firstName, String secondName,
                             String login, String password,
                             String phoneNumber, UserType userType){
        if (!users.containsKey(login))
        {
            users.put(login, new User(firstName, secondName, login, password, phoneNumber, userType));
            System.out.println("Пользователь успешно зарегестрирован");
        }
        else System.out.println("Данный логин уже занят");
    }
    public void getUserInfo(String firstName, String secondName){
        if (currentUser != null && currentUser.getUserType() != UserType.CUSTOMER){
            ArrayList<User> usersList = new ArrayList<>();
            for (User value : users.values()) {
                if (value.getFirstName().equals(firstName) && value.getSecondName().equals(secondName))
                    usersList.add(value);
            }
            if (!usersList.isEmpty()){
                String response = "\n";
                if (currentUser.getUserType() == UserType.ADMINISTRATOR){
                    for (User user : usersList) {
                        response += "Имя: " + user.getFirstName() + "\n" +
                                "Фамилия: " + user.getSecondName() + "\n" +
                                "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                "Должность: " + user.getUserType() + "\n" +
                                "Логин: " + user.getLogin() + "\n" +
                                "Пароль: " + user.getPassword() + "\n";
                    }
                } else if (currentUser.getUserType() == UserType.MANAGER){
                    for (User user : usersList) {
                        if (user.getUserType() == UserType.CUSTOMER){
                            response += "Имя: " + user.getFirstName() + "\n" +
                                    "Фамилия: " + user.getSecondName() + "\n" +
                                    "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                    "Статус: " + user.getUserType() + "\n";
                        }
                    }
                }
                if (response.length() > 2) System.out.println(response);
                else System.out.println("Данный пользователь не найден");

            } else System.out.println("Данный пользователь не найден");
        } else if (currentUser != null && currentUser.getUserType() == UserType.CUSTOMER){
            System.out.println("Недостаточно прав для данного действия");
        } else System.out.println("Для данного действия требуется авторизация");
    }
    public void getUserInfo(){
        if (currentUser != null) {
            System.out.println(("Ваши персональные данные: " + "\n" +
                    "Имя: " + currentUser.getFirstName() + "\n" +
                    "Фамилия: " + currentUser.getSecondName() + "\n" +
                    "Номер телефона: " + currentUser.getPhoneNumber() + "\n"));
        } else System.out.println("Для данного действия требуется авторизация");
    }
}
