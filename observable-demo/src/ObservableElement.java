import java.util.Observable;

public class ObservableElement extends Observable {

    private String Weather;
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public ObservableElement(String weather, Integer temperature) {
        Weather = weather;
        this.temperature = temperature;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
        setChanged();
        notifyObservers();
    }
}
