public class ObserverTest {

    public static void main(String[] args) {

        StockMarket sm =
            new StockMarket();

        sm.register(
            new MobileApp());

        sm.register(
            new WebApp());

        sm.notifyObservers(500.25);
    }
}