public class Scania extends BigCar implements HasAngle {

    private int flakAngle;

    public Scania() {
        flakAngle = 0;
    }



    public void changeAngle(int deltaAngle) {
        if(getCurrentSpeed() > 0){
            return;
        }

        if (deltaAngle + flakAngle > 70) {
            flakAngle = 70;
        } else if (deltaAngle + flakAngle < 0) {
            flakAngle = 0;
        } else {
            flakAngle += deltaAngle;
        }
    }

    public int getAngle() {
        return flakAngle;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (flakAngle == 0) {
            super.incrementSpeed(amount);
        }
    }

    @Override
    public void startEngine() {
        if (flakAngle == 0) {
            super.startEngine();
        }
    }
}
