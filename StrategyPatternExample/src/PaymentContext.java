public class PaymentContext {

    PaymentStrategy strategy;

    public PaymentContext(
            PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void execute(int amount) {
        strategy.pay(amount);
    }
}