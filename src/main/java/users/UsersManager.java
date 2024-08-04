package users;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersManager {
    private HashMap<String, User> users;

    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;
    public UsersManager(){
        users = new HashMap<>();
        users.put("admin", new User("Иван", "Иванов", "admin", "qwerty",
                "+12345678900", User.UserType.ADMINISTRATOR));
        users.put("r", new User("r", "Иванов", "r", "r",
                "+12345678900", User.UserType.CUSTOMER));
        users.put("m", new User("m", "Иванов", "m", "m",
                "+12345678900", User.UserType.MANAGER));
    }
    public User.UserType getUserType(){
        if (currentUser != null) return currentUser.getUserType();
        else return null;
    }
    public boolean signin(String login, String password){
        if (users.containsKey(login) && users.get(login).getPassword().equals(password)){
            currentUser = users.get(login);
            System.out.println("Доброго времени суток, " + currentUser.getFirstName() + ".");
            return true;
        } else {
            System.out.println("Введены неверные имя пользователя или пароль");
            return false;
        }
    }
    public String logout(){
        if (currentUser != null) {
            currentUser = null;
            return "Сессия успешно закончена";
        } else return "Нет активных сессий";
    }
    public boolean createUser(String firstName, String secondName,
                             String login, String password,
                             String phoneNumber, User.UserType userType ){
        if((userType == User.UserType.MANAGER || userType == User.UserType.ADMINISTRATOR) && currentUser == null){
            System.out.println("Авторизуйтесь для данного действия");
            return false;
        }
        if (!users.containsKey(login))
        {
            users.put(login, new User(firstName, secondName, login, password, phoneNumber, userType));
            System.out.println("Пользователь успешно зарегестрирован");
            return true;
        }
        else {
            System.out.println("Данный логин уже занят");
            return false;
        }
    }
    public String getUserInfo(User searchedUser){
        if (currentUser != null && currentUser.getUserType() != User.UserType.CUSTOMER){
            ArrayList<User> usersList = getUsersList(searchedUser.getFirstName(), searchedUser.getSecondName());
            if (!usersList.isEmpty()){
                String response = "";
                if (currentUser.getUserType() == User.UserType.ADMINISTRATOR){ //админ видит всех пользователей в списке
                    for (User user : usersList) {
                        response += "Имя: " + user.getFirstName() + "\n" +
                                "Фамилия: " + user.getSecondName() + "\n" +
                                "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                "Должность: " + user.getUserType() + "\n";
                    }
                } else if (currentUser.getUserType() == User.UserType.MANAGER){ //манагер видит только список покупателей
                    for (User user : usersList) {
                        if (user.getUserType() == User.UserType.CUSTOMER){
                            response += "Имя: " + user.getFirstName() + "\n" +
                                    "Фамилия: " + user.getSecondName() + "\n" +
                                    "Номер телефона: " + user.getPhoneNumber() + "\n";
                        }
                    }
                }
                if (response.length() > 2) return response;
                else return "Данный пользователь не найден";

            } else return "Данный пользователь не найден";
        } else if (currentUser != null && currentUser.getUserType() == User.UserType.CUSTOMER){
            return "Недостаточно прав для данного действия";
        } else return "Для данного действия требуется авторизация";
    }
    public String getUserInfo(String firstName, String secondName){
        if (currentUser != null && currentUser.getUserType() != User.UserType.CUSTOMER){
            ArrayList<User> usersList = getUsersList(firstName, secondName);
            if (!usersList.isEmpty()){
                String response = "\n";
                if (currentUser.getUserType() == User.UserType.ADMINISTRATOR){ //админ видит всех пользователей в списке
                    for (User user : usersList) {
                        response += "Имя: " + user.getFirstName() + "\n" +
                                "Фамилия: " + user.getSecondName() + "\n" +
                                "Номер телефона: " + user.getPhoneNumber() + "\n" +
                                "Должность: " + user.getUserType() + "\n";
                    }
                } else if (currentUser.getUserType() == User.UserType.MANAGER){ //манагер видит только список покупателей
                    for (User user : usersList) {
                        if (user.getUserType() == User.UserType.CUSTOMER){
                            response += "Имя: " + user.getFirstName() + "\n" +
                                    "Фамилия: " + user.getSecondName() + "\n" +
                                    "Номер телефона: " + user.getPhoneNumber() + "\n";
                        }
                    }
                }
                if (response.length() > 2) return response;
                else return "Данный пользователь не найден";

            } else return "Данный пользователь не найден";
        } else if (currentUser != null && currentUser.getUserType() == User.UserType.CUSTOMER){
            return "Недостаточно прав для данного действия";
        } else return "Для данного действия требуется авторизация";
    }

    private ArrayList<User> getUsersList(String firstName, String secondName) {
        ArrayList<User> usersList = new ArrayList<>();
        for (User value : users.values()) {
            if (value.getFirstName().equals(firstName) && value.getSecondName().equals(secondName))
                usersList.add(value);
        }
        return usersList;
    }

    public String getUserInfo(){
        if (currentUser != null) {
            return "Ваши персональные данные:" + "\n" +
                    "Имя: " + currentUser.getFirstName() + "\n" +
                    "Фамилия: " + currentUser.getSecondName() + "\n" +
                    "Номер телефона: " + currentUser.getPhoneNumber() + "\n" +
                    "Логин: " + currentUser.getLogin() + "\n" +
                    "Пароль: " + currentUser.getPassword() + "\n";
        } else return "Для данного действия требуется авторизация";
    }
    
}
