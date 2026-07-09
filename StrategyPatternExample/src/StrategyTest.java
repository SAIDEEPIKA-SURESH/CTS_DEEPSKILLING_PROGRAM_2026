public class StrategyTest {

    public static void main(String[] args) {

        PaymentContext p =
            new PaymentContext(
                new CreditCardPayment());

        p.execute(5000);

        p =
            new PaymentContext(
                new PayPalPayment());

        p.execute(10000);
    }
}