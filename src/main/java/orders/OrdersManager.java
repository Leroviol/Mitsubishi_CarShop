package orders;

import cars.Car;
import users.User;

import java.util.ArrayList;

public class OrdersManager {
    private ArrayList<Order> ordersList = new ArrayList<>();
    public boolean addOrder(User customer, Car car){
        if (customer != null && car != null){
            ordersList.add(new Order(car, customer));
            System.out.println("Заказ успешно создан. Требуется подтверждение от менеджера");
            return true;
        } else {
            System.out.println("Ошибка при создании заказа");
            return false;
        }
    }
    public ArrayList<Order> getOrdersByCar(Car car){
        ArrayList<Order> ordersByCar = new ArrayList<>();
        for (Order order : ordersList) {
            if (order.getCar().equals(car)) ordersByCar.add(order);
        }
        if (ordersByCar.isEmpty()) return null;
        else return ordersByCar;
    }
    public ArrayList<Order> getOrdersByCustomer(User customer){
        ArrayList<Order> ordersByBuyer = new ArrayList<>();
        for (Order order : ordersList) {
            if (order.getCustomer().equals(customer)) ordersByBuyer.add(order);
        }
        if (ordersByBuyer.isEmpty()) return null;
        else return ordersByBuyer;
    }
}
