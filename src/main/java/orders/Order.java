package orders;

import cars.Car;
import users.User;

public class Order {
    private Car car;
    private User customer;
    private boolean isSell;

    public Order(Car car, User customer) {
        this.car = car;
        this.customer = customer;
        isSell = false;
    }

    public Car getCar() {
        return car;
    }

    public User getCustomer() {
        return customer;
    }

    public boolean isSell() {
        return isSell;
    }
}
