import java.io.*;
import java.util.Scanner;

public class Main {
    static final String MITSUBISHI_LOGO_PATH = "C:\\Users\\X\\IdeaProjects\\ylab\\Mitsubishi_CarShop\\src\\main\\resources\\Mitsubishi_logo.txt";
    public static void main(String[] args) {
        File file = new File(MITSUBISHI_LOGO_PATH);
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Добро пожаловать в автомагазин Mitsubishi Motors");
        System.out.println("Выберите действие:");
        System.out.println("""
                    1. Регистрация
                    2. Авторизация
                    3. Список автомобилей
                    4. Выйти
                    """);
        Scanner scanner = new Scanner(System.in);

    }
}