import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Volvo240 volvo240 = new Volvo240();

    @Test
    void gas() {
        double v0 = volvo240.getCurrentSpeed();
        volvo240.gas(0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 < v1));
    }

    @Test
    void brake() {
        volvo240.gas(0.5);
        double v0 = volvo240.getCurrentSpeed();
        volvo240.brake(0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v1 < v0));
    }

    @Test
    void brakeWithGas() {
        double v0 = volvo240.getCurrentSpeed();
        volvo240.gas(-0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 == v1));
    }

    @Test
    void gasWithBrake() {
        volvo240.gas(0.5);
        double v0 = volvo240.getCurrentSpeed();
        volvo240.brake(-0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 == v1));
    }

    @Test
    void vHigherThanEnginePower() {
        for (int i = 0; i < volvo240.getEnginePower()+1; i++) {
            volvo240.gas(1);
        }
        double v = volvo240.getCurrentSpeed();
        assertTrue(v <= volvo240.getEnginePower());
    }

    @Test
    void vLowerThanZero() {
        volvo240.gas(0.5);
        volvo240.brake(1);
        double v = volvo240.getCurrentSpeed();
        assertTrue(v >= 0);
    }

    private Scania scania = new Scania();

    @Test
    void flakAngleNotOver70() {
        scania.changeAngle(71);
        assertEquals(70, scania.getAngle());
    }


    @Test
    void flakAngleNotNegative() {
        scania.changeAngle(-10);
        assertEquals(0, scania.getAngle());
    }

    @Test
    void flakAngleChange() {
        scania.changeAngle(40);
        assertEquals(40, scania.getAngle());
    }

    @Test
    void notDrivingFlakAngleOver0() {
        scania.changeAngle(70);
        double v0 = scania.getCurrentSpeed();
        scania.incrementSpeed(1);
        double v1 = scania.getCurrentSpeed();
        assertEquals(v0, v1);
    }

    @Test
    void notStartingEngineFlakAngle() {
        scania.changeAngle(10);
        scania.startEngine();
        assertEquals(0, scania.getCurrentSpeed());
    }

    private Biltransport biltransport = new Biltransport(3);

    @Test
    void useRamp() {
        assertEquals(0, biltransport.getAngle());
        biltransport.changeAngle(1);
        assertEquals(1, biltransport.getAngle());
        biltransport.changeAngle(-1);
        assertEquals(0,biltransport.getAngle());
    }


    @Test
    void loadsOnlyMaxCapacity() {
        ArrayList<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < 4; i++) cars.add(new Car());
        for (Car car: cars) biltransport.loadCar(car);
        assertEquals(3, biltransport.getCarsStorage().size());
    }


    @Test
    void cannotLoadBiltransport() {
        Biltransport bt1 = new Biltransport(1);
        Biltransport bt2 = new Biltransport(1);
        Scania scania = new Scania();
        bt1.loadCar(bt2);
        assertEquals(0, bt1.getCarsStorage().size());
        bt1.loadCar(scania);
        assertEquals(0, bt1.getCarsStorage().size());
    }


    @Test
    void notFlakWithNegativeCars() {
        biltransport.offLoadCar();
        assertEquals(0, biltransport.getCarsStorage().size());
    }

    @Test
    void offLoadCars() {
        for (int i=0; i<2; i++) biltransport.loadCar(new Volvo240());
        Volvo240 car3 = new Volvo240();
        biltransport.loadCar(car3);
        int amountOfCars = biltransport.getCarsStorage().size();
        biltransport.offLoadCar();

        assertEquals(amountOfCars - 1, biltransport.getCarsStorage().size());
        assertFalse(biltransport.getCarsStorage().contains(car3));
    }

    @Test
    void btAndCarsSameCoords() {
        Car car = new Car();
        biltransport.loadCar(car);
        biltransport.changeAngle(1);
        biltransport.startEngine();
        biltransport.gas(1);
        biltransport.turnRight();
        biltransport.move();
        biltransport.brake(1);
        biltransport.stopEngine();

        assertTrue(biltransport.xCoord == car.xCoord && biltransport.yCoord == car.yCoord);
    }
}