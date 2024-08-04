package cars;

public class Car {
    private String brand, model;
    private int price, yearOfProduction;
    private Condition condition;

    public Car(String brand, String model, int yearOfProduction, int price, Condition condition) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.condition = condition;
    }

    public Car(String brand, String model, int yearOfProduction) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return brand + " " + model + " Год выпуска: " + yearOfProduction +
                " Состояние: " + condition + " Цена: " + price;
    }

    public enum Condition {
        FACTORY_NEW,
        MINIMAL_WEAR,
        FIELD_TESTED,
        WELL_WORN,
        BATTLE_SCARRED
    }
}
