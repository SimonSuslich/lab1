import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Volvo240 volvo240 = new Volvo240();

    @org.junit.jupiter.api.Test
    void gas() {
        double v0 = volvo240.getCurrentSpeed();
        volvo240.gas(0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 < v1));
    }

    @org.junit.jupiter.api.Test
    void brake() {
        volvo240.gas(0.5);
        double v0 = volvo240.getCurrentSpeed();
        volvo240.brake(0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v1 < v0));
    }

    @org.junit.jupiter.api.Test
    void brakeWithGas() {
        double v0 = volvo240.getCurrentSpeed();
        volvo240.gas(-0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 == v1));
    }

    @org.junit.jupiter.api.Test
    void gasWithBrake() {
        volvo240.gas(0.5);
        double v0 = volvo240.getCurrentSpeed();
        volvo240.brake(-0.5);
        double v1 = volvo240.getCurrentSpeed();
        assertTrue((v0 == v1));
    }

    @org.junit.jupiter.api.Test
    void vHigherThanEnginePower() {
        for (int i = 0; i < volvo240.getEnginePower()+1; i++) {
            volvo240.gas(1);
        }
        double v = volvo240.getCurrentSpeed();
        assertTrue(v <= volvo240.getEnginePower());
    }

    @org.junit.jupiter.api.Test
    void vLowerThanZero() {
        volvo240.gas(0.5);
        volvo240.brake(1);
        double v = volvo240.getCurrentSpeed();
        assertTrue(v >= 0);
    }
}