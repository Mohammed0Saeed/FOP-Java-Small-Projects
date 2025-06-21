package h05;

public class WeatherBalloon implements Flying {
    private final int balloonNumber;

    public WeatherBalloon(int balloonNumber) {
        this.balloonNumber = balloonNumber;
    }

    @Override
    public String getIdentifier() {
        return "Weather Balloon " + balloonNumber;
    }

    /**
     * register the weather balloon when flying
     */
    public void start() {
        Airspace.get().register(this);
    }

    /**
     * register teh weather balloon when landing
     */
    public void pop() {
        Airspace.get().deregister(this);
    }
}
