package cars;

import java.util.ArrayList;

public class CarsManager {
    ArrayList<Car> carList = new ArrayList<>();

    public int getCarsCount() {
        return carsCount;
    }

    int carsCount;
    public CarsManager(){
        carList.add(new Car("Mitsubishi", "Delica", 2014, 3115000, Car.Condition.WELL_WORN));
        carList.add(new Car("Mitsubishi", "Outlander", 2017, 2798000, Car.Condition.WELL_WORN));
        carList.add(new Car("Mitsubishi", "L200", 2017, 2665000, Car.Condition.FIELD_TESTED));
        carList.add(new Car("Mitsubishi", "ASX", 2020, 2310000, Car.Condition.MINIMAL_WEAR));
        carList.add(new Car("Mitsubishi", "Lancer", 2012, 998000, Car.Condition.WELL_WORN));
        carList.add(new Car("Mitsubishi", "eK Wagon", 2019, 1342105, Car.Condition.MINIMAL_WEAR));
        carList.add(new Car("Mitsubishi", "Outlander", 2008, 1216000, Car.Condition.BATTLE_SCARRED));
        carList.add(new Car("Mitsubishi", "RVR", 2010, 1398000, Car.Condition.WELL_WORN));

        carsCount = carList.size();
    }
    public Car getCar(int index){
        if (!carList.isEmpty()) return carList.get(index);
        else return null;
    }
    public String getCarInfo(int index){
        if (index >= 0 && index < carsCount){
            return carList.get(index).toString();
        } return "";
    }
    public boolean isCarExists(Car car){
        return carList.contains(car);
    }
    public ArrayList<Car> getCarsList(){
        return carList;
    }
    public boolean deleteCarFromList(Car car){
        if (carList.contains(car)){
            carList.remove(car);
            carsCount = carList.size();
            System.out.println("Автомобиль удалён из списка");
            return true;
        } else {
            System.out.println("Такого автомобиля нет в списке");
            return false;
        }
    }

    public Car getCarFromList(int index) {
        return carList.get(index);
    }
}
