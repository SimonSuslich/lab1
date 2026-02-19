import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    AutoShop<Volvo240> volvo240AutoShop = new AutoShop<Volvo240>(2);

    //methods:

    public void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Volvo240());
        cc.cars.add(new Volvo240());
        cc.cars.add(new Volvo240());


        cc.cars.add(new Scania());
        System.out.println(volvo240AutoShop);
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc, cc.cars);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Car car : cars) {
                if (checkHitWall(car)) {
                    car.carAngle = (car.carAngle + 180)%360;
                }
               car.move();
               int x = (int) Math.round(car.getxCoord());
               int y = (int) Math.round(car.getyCoord());
               if (atAutoShopColide(car)) {
                   if (car instanceof Volvo240) {
                        volvo240AutoShop.loadCar((Volvo240) car);
                        car.stopEngine();
                        //.out.println(" autoshop cars: "+volvo240AutoShop.getCarsStorage()  );

                        //System.out.println(volvo240AutoShop + "--- "+ frame.autoShop);
                        System.out.println(volvo240AutoShop.getCarsStorage());

                   }
               }

                //System.out.println("Car model " +car.modelName);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car: cars) {
            car.brake(brake);
        }
    }

    void setTurboOn() {
        for (Car car: cars) {
            if (car instanceof Saab95) {
                car.setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for (Car car: cars) {
            if (car instanceof Saab95) {
                car.setTurboOff();
            }
        }
    }

    void changeAngle(int deltaAngle) {
        for (Car car: cars) {
            if (car instanceof Scania) {
                ((Scania) car).changeAngle(deltaAngle);
            }
        }
    }

    void startEngine() {
        for (Car car: cars) {
            car.stopEngine();
        }
    }

    void stopEngine() {
        for (Car car: cars) {
            car.stopEngine();
        }
    }

    boolean checkHitWall(Car car) {
        int carWidth = car.image.getWidth();
        int carHeight = car.image.getHeight();
        Dimension windowSize = frame.drawPanel.getSize();


        if (windowSize.height < car.getyCoord()+carHeight) {
            return true;
        }

        if (windowSize.width < car.getxCoord()+carWidth) {
            return true;
        }

        if (0 > car.getyCoord()) {
            return true;
        }
        if (0 > car.getxCoord()) {
            return true;
        }
        return false;
    }

    boolean atAutoShopColide(Car car){
        int carWidth = car.image.getWidth();
        int carHeight = car.image.getHeight();
        Point autoShopSize = frame.drawPanel.volvoWorkshopPoint;
        int autoShopWidth = frame.drawPanel.volvoWorkshopImage.getWidth();
        int autoShopHeight = frame.drawPanel.volvoWorkshopImage.getHeight();





        if ((autoShopSize.y < car.getyCoord()+carHeight && autoShopSize.y +autoShopHeight > car.getyCoord()+carHeight) ||  (autoShopSize.y < car.getyCoord() && autoShopSize.y +autoShopHeight > car.getyCoord())) {
            if ((autoShopSize.x < car.getxCoord()+carWidth && autoShopSize.x +autoShopWidth > car.getxCoord()+carWidth) ||  (autoShopSize.x < car.getxCoord() && autoShopSize.x +autoShopWidth > car.getxCoord())) {
                return true;
            }
        }

        return false;
    }
}
