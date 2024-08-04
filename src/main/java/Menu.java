import java.util.Scanner;

public class Menu {

    public static void start(){
        System.out.println("""
                    Добро пожаловать в автомагазин Mitsubishi Motors
                    
                    Выберите действие:
                    1. Регистрация
                    2. Авторизация
                    3. Список автомобилей
                    4. Выйти
                    """);
    }
    public static String registration(){
        Scanner scanner = new Scanner(System.in);
        String result = "";
        System.out.print("Введите Имя: ");
        result += scanner.nextLine() + ";";
        System.out.print("Введите Фамилию: ");
        result += scanner.nextLine() + ";";
        System.out.print("Введите Логин: ");
        result += scanner.nextLine() + ";";
        System.out.print("Введите Пароль: ");
        result += scanner.nextLine() + ";";
        System.out.print("Введите Номер телефона: ");
        result += scanner.nextLine();
        return result;
    }
}
