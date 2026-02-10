import java.util.ArrayList;
import java.awt.Point;

public class Biltransport extends BigCar implements Loadable, HasAngle{

    private int rampAngle;
    private ArrayList<Car> carsStorage = new ArrayList<Car>();
    private final int flakCapacity;

    public Biltransport(int flakCapacity) {
        this.flakCapacity = flakCapacity;
        rampAngle = 0;
    }

    public void changeAngle(int deltaAngle) {
        if(getCurrentSpeed() > 0){
            return;
        }

        if (deltaAngle + rampAngle > 1) {
            rampAngle = 1;
        } else if (deltaAngle + rampAngle < 0) {
            rampAngle = 0;
        } else {
            rampAngle += deltaAngle;
        }
    }

    public int getAngle() {
        return rampAngle;
    }



    public void loadCar(Car car) {
        //Checks if ramp is down
        if (rampAngle==0) {
            //Checks if distance is 5 or less
            if (Point.distance(car.xCoord, car.yCoord, this.xCoord, this.yCoord) <= 5) {
                //Checks if there is storage for car
                if (carsStorage.size() < flakCapacity) {
                    //Checks if car is instance of biltransport
                    if (!(car instanceof BigCar)) {
                        carsStorage.add(car);
                        car.xCoord = this.xCoord;
                        car.yCoord = this.yCoord;
                    }
                }
            }
        }
    }

    public Car offLoadCar() {
        //Checks if ramp is down
        if (rampAngle==0) {
            if (!carsStorage.isEmpty()) {
                Car car = carsStorage.getLast();
                carsStorage.removeLast();
                car.xCoord = this.xCoord + 5;
                car.yCoord = this.yCoord;
                return car;
            }
        }
        return null;
    }

    @Override
    public void move()  {
        super.move();
        for (Car car: carsStorage) {
            car.xCoord = this.xCoord;
            car.yCoord = this.yCoord;
        }
    }

    public ArrayList<Car> getCarsStorage() {
        return carsStorage;
    }
}
