package exercise3;

public class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int id, String name, double price) {
        this.orderId = id;
        this.customerName = name;
        this.totalPrice = price;
    }

    public String toString() {
        return orderId + " " + customerName +
               " " + totalPrice;
    }
}
