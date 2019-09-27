import java.util.Observable;
import java.util.Observer;

public class ObservableDemo implements Observer {

    private ObservableElement observableElement;

    @Override
    public void update(Observable o, Object arg) {

        observableElement = (ObservableElement) o;
        System.out.println("WSeatehr reposrf  live :" + observableElement.getWeather());
    }

    public static void main(String[] args) {
        ObservableElement observable=new ObservableElement("abc",1);
        ObservableDemo observer=new ObservableDemo();
        observable.addObserver(observer);
        observable.setTemperature(123);
        observable.setWeather("cold");
        observable.setWeather("hot");
        observable.setWeather("chill");

    }
}
