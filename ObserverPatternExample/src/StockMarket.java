import java.util.*;

public class StockMarket {

    List<Observer> observers =
            new ArrayList<>();

    void register(Observer o) {
        observers.add(o);
    }

    void notifyObservers(
            double price) {

        for(Observer o:observers)
            o.update(price);
    }
}