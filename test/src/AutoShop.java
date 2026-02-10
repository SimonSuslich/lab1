import java.util.ArrayList;

public class AutoShop<CarBrand extends Car> {
    private final int workShopCapacity;
    private ArrayList<CarBrand> carsStorage = new ArrayList<CarBrand>();
    public AutoShop(int workShopCapacity) {
        this.workShopCapacity = workShopCapacity;
    }

    public void loadCar(CarBrand car) {
        if (carsStorage.size() < workShopCapacity) carsStorage.add(car);
    }

    public Car offLoadCar(Car car) {
        if (!carsStorage.isEmpty()) {
            int index = carsStorage.indexOf(car);
            if (index == -1) {
                throw new IndexOutOfBoundsException("Car is not in Autoshop");
            }
            Car repairedCar = carsStorage.get(index);
            carsStorage.remove(car);
            return repairedCar;
        }
        throw new IllegalArgumentException("Workshop is empty");
    }
}
