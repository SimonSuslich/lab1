import java.awt.*;

public class Saab95 extends Car {

    public Saab95(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        turboOn = false;
        modelName = "Saab95";
        stopEngine();
    }

    @Override
    public void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    @Override
    public void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
}