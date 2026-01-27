
import java.awt.*;

public class Car implements Movable {

    protected boolean turboOn;
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    protected double xCoord = 0.0;
    protected double yCoord = 0.0;
    protected int carAngle = 0;

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
    // TODO fix this method according to lab pm
    public void gas(double amount){
        if ((amount < 0) || (amount > 1)){
            return;
        }

        double v0 = getCurrentSpeed();
        incrementSpeed(amount);

        if (getCurrentSpeed() < v0){
            decrementSpeed(amount);
            return;
        }

        if (getCurrentSpeed() > getEnginePower()) {
            currentSpeed = getEnginePower();
            return;
        }
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if ((amount < 0) || (amount > 1)){
            return;
        }

        double v0 = getCurrentSpeed();
        decrementSpeed(amount);

        if (getCurrentSpeed() > v0){
            incrementSpeed(amount);
            return;
        }

        if (getCurrentSpeed() < 0) {
            currentSpeed = 0;
            return;
        }
    }


    public void move() {
        xCoord = Math.sin(carAngle)*currentSpeed;
        yCoord = Math.cos(carAngle)*currentSpeed;
    }

    public void turnLeft() {
        carAngle = (carAngle + 10)%360;
    }

    public void turnRight() {
        carAngle = (carAngle - 10)%360;
    }
}