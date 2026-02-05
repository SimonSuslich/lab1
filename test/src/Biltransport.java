import java.util.ArrayList;
import java.awt.Point;

public class Biltransport extends Car {

    private boolean rampOpened;
    private ArrayList<Car> carsStorage;
    private final int flakCapacity;

    public Biltransport(int flakCapacity) {
        this.flakCapacity = flakCapacity;
        rampOpened = false;
    }

    public void openOrCloseRamp() {
        if (getCurrentSpeed() == 0) {
            rampOpened = !rampOpened;
        }
    }

    public void loadCar(Car car) {
        //Checks if ramp is down
        if (rampOpened) {
            //Checks if distance is 5 or less
            if (Point.distance(car.xCoord, car.yCoord, this.xCoord, this.yCoord) <= 5) {
                //Checks if there is storage for car
                if (carsStorage.size() < flakCapacity) {
                    //Checks if car is instance of biltransport
                    if (!(car instanceof Biltransport)) {
                        carsStorage.add(car);
                        car.xCoord = this.xCoord;
                        car.yCoord = this.yCoord;
                    }
                }
            }
        }
    }

    public void offLoadCar() {
        //Checks if ramp is down
        if (rampOpened) {
            if (!carsStorage.isEmpty()) {
                Car car = carsStorage.getLast();
                carsStorage.removeLast();
                car.xCoord = this.xCoord + 5;
                car.yCoord = this.yCoord;
            }
        }
    }

    @Override
    public void move()  {
        super.move();
        for (Car car: carsStorage) {
            car.xCoord = this.xCoord;
            car.yCoord = this.yCoord;
        }
    }


    public boolean getRampOpened() {
        return rampOpened;
    }

    public ArrayList<Car> getCarsStorage() {
        return carsStorage;
    }
}
