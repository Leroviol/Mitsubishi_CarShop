import cars.Car;
import cars.CarsManager;
import orders.Order;
import orders.OrdersManager;
import users.User;
import users.UsersManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String MITSUBISHI_LOGO_PATH = "C:\\Users\\X\\IdeaProjects\\ylab\\Mitsubishi_CarShop\\src\\main\\resources\\Mitsubishi_logo.txt";
    static UsersManager usersManager = new UsersManager();
    static CarsManager carsManager = new CarsManager();
    static OrdersManager ordersManager = new OrdersManager();
    public static void main(String[] args) {

        //Вывод лого митубиси в консоль
        File file = new File(MITSUBISHI_LOGO_PATH);
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        Scanner scanner = new Scanner(System.in);
        String positionOnMenu = "0";
        while (!positionOnMenu.isEmpty()){
            switch (positionOnMenu){
                case "0":{
                    Menu.start();
                    positionOnMenu += scanner.nextLine();
                    break;
                }
                case "01": {
                    String[] registration = Menu.registration().split(";");
                    if (usersManager.createUser(registration[0], registration[1],
                            registration[2], registration[3], registration[4], User.UserType.CUSTOMER)) {
                        positionOnMenu = "0";
                        System.out.println();
                        break;
                    } else {
                        System.out.println("""
                                    1. Повторить попытку
                                    0. Вернуться в меню\n
                                    """);
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1":
                                break;
                            case "0": {
                                positionOnMenu = "0";
                                break;
                            }
                            default:{
                                System.out.println("Введено неверное значение");
                                break;
                            }
                        }
                    } break;
                }
                case "02": {
                    if (usersManager.getCurrentUser() == null){
                        System.out.print("Введите Логин: ");
                        String login = scanner.nextLine();
                        System.out.print("Введите пароль: ");
                        String password = scanner.nextLine();
                        usersManager.signin(login, password);
                        System.out.println();
                    }
                    if (usersManager.getUserType() == User.UserType.MANAGER){
                        System.out.println("""
                                    1. Персональные данные
                                    2. Контактные данные покупателей
                                    3. Список автомобилей
                                    4. Необработанные заказы
                                    0. Выйти из профиля
                                    """);
                        positionOnMenu += scanner.nextLine();
                    }
                    if (usersManager.getUserType() == User.UserType.CUSTOMER) {
                        System.out.println("""
                                    1. Персональные данные
                                    2. Витрина
                                    3. Заказы
                                    0. Выйти из профиля
                                    """);
                        positionOnMenu += scanner.nextLine();
                    }
                    break;
                }
                case "021":{
                    if (usersManager.getUserType() == User.UserType.MANAGER){
                        System.out.println(usersManager.getUserInfo());
                        System.out.println();
                        positionOnMenu = "02";
                        break;
                    } else if (usersManager.getUserType() == User.UserType.CUSTOMER){
                        System.out.println(usersManager.getUserInfo());
                        System.out.println();
                        positionOnMenu = "02";
                        break;
                    }
                }
                case "022":{
                    if (usersManager.getUserType() == User.UserType.MANAGER){
                        System.out.print("Введите Имя и Фамилию покупателя через пробел: ");
                        String[] names = scanner.nextLine().split(" ");
                        if (names.length != 2) {
                            System.out.println("Данные введены некоретно");
                            break;
                        } else {
                            System.out.println(usersManager.getUserInfo(names[0], names[1]));
                            positionOnMenu = "02";
                            break;
                        }
                    } else if (usersManager.getUserType() == User.UserType.CUSTOMER){
                        for (int i = 0; i < carsManager.getCarsCount(); i++) {
                            System.out.println(i + 1 + ". " + carsManager.getCarInfo(i));
                        }
                        System.out.println();
                        System.out.print("Введите номер автомобиля, чтобы оформить заказ или введите 0, чтобы вернуться в предыдущее меню: ");
                        String choice = scanner.nextLine();
                        System.out.println();
                        if (choice.equals("0")){
                            positionOnMenu = "02";
                            break;
                        } else {
                            ordersManager.addOrder(usersManager.getCurrentUser(), carsManager.getCarFromList(Integer.parseInt(choice) - 1));
                            break;
                        }
                    }
                }
                case "023": {
                    if (usersManager.getUserType() == User.UserType.MANAGER){
                        for (int i = 0; i < carsManager.getCarsCount(); i++) {
                            System.out.println(i + 1 + ". " + carsManager.getCarInfo(i));
                        }
                        System.out.println();
                        System.out.print("Введите номер автомобиля, чтобы посмотреть заявки на него или введите 0 для возврата в предыдущее меню: ");
                        String choice = scanner.nextLine();
                        System.out.println();
                        if (choice.equals("0")){
                            positionOnMenu = "02";
                            break;
                        } else {
                            ArrayList<Order> ordersByCar = ordersManager.getOrdersByCar(carsManager.getCar(Integer.parseInt(choice) - 1));
                            if (ordersByCar == null) {
                                System.out.println("На данный автомобиль нет заказов");
                                positionOnMenu = "023";
                                break;
                            } else {
                                for (int i = 0; i < ordersByCar.size(); i++) {
                                    System.out.println(i + 1 + ". " + usersManager.getUserInfo(ordersByCar.get(i).getCustomer()));
                                }
                                System.out.print("Введите номер пользователя, которому хотите продать автомобиль или введите 0" +
                                        "для вовзрата в предыдущее меню: ");
                                String numberOfCar = scanner.nextLine();
                                if (numberOfCar.equals("0")){
                                    positionOnMenu = "02";
                                    break;
                                } else if (Integer.parseInt(numberOfCar) > ordersByCar.size() || Integer.parseInt(numberOfCar) < 0) {
                                    System.out.println("Введено неверное значение");
                                    break;
                                }
                            }
                            break;
                        }
                    } else if (usersManager.getUserType() == User.UserType.CUSTOMER){
                        ArrayList<Order> orders = ordersManager.getOrdersByCustomer(usersManager.getCurrentUser());
                        if (orders != null) {
                            for (int i = 0; i < orders.size(); i++) {
                                System.out.println(i + 1 + ". " + orders.get(i).getCar().toString());
                            }
                        } else {
                            System.out.println("Нет активных заказов");
                        }
                        positionOnMenu = "02";
                        break;
                    }
                }
                case "024": {
                    if (usersManager.getUserType() == User.UserType.MANAGER){
                        ArrayList<Order> orders = ordersManager.getOrdersByCustomer(usersManager.getCurrentUser());
                        if (orders != null) {
                            for (int i = 0; i < orders.size(); i++) {
                                System.out.println(i + 1 + ". " + orders.get(i).getCar().toString());
                            }
                        } else {
                            System.out.println("Нет активных заказов");
                        }
                        positionOnMenu = "02";
                        break;
                    } else if (usersManager.getUserType() == User.UserType.CUSTOMER){

                    }
                }
                case "020": {
                    System.out.println(usersManager.logout());
                    positionOnMenu = "0";
                    break;
                }
                case "03": {
                    for (Car car : carsManager.getCarsList()) {
                        System.out.println(car);
                    }
                    positionOnMenu = "0";
                    break;
                }
                case "04": {
                    positionOnMenu = "";
                    System.out.println("Ждем Вас снова!");
                    break;
                }
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }
}