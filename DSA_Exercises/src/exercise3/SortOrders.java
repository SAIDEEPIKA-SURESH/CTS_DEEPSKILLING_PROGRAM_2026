package exercise3;

public class SortOrders {

    static void bubbleSort(Order[] arr) {
        int n = arr.length;

        for(int i=0;i<n-1;i++) {
            for(int j=0;j<n-i-1;j++) {
                if(arr[j].totalPrice > arr[j+1].totalPrice) {
                    Order temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    static int partition(Order[] arr,int low,int high) {
        double pivot = arr[high].totalPrice;
        int i = low-1;

        for(int j=low;j<high;j++) {
            if(arr[j].totalPrice < pivot) {
                i++;
                Order temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }

        Order temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;

        return i+1;
    }

    static void quickSort(Order[] arr,int low,int high) {
        if(low<high) {
            int pi=partition(arr,low,high);
            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }

    public static void main(String[] args) {

        Order[] orders = {
            new Order(1,"Ram",3000),
            new Order(2,"John",1000),
            new Order(3,"Sam",5000)
        };

        System.out.println("Original Orders:");
        for(Order o:orders)
            System.out.println(o);

        // Make copies to test both sorting algorithms
        Order[] ordersBubble = orders.clone();
        Order[] ordersQuick = orders.clone();

        bubbleSort(ordersBubble);
        System.out.println("\nAfter Bubble Sort:");
        for(Order o:ordersBubble)
            System.out.println(o);

        quickSort(ordersQuick, 0, ordersQuick.length-1);
        System.out.println("\nAfter Quick Sort:");
        for(Order o:ordersQuick)
            System.out.println(o);
    }
}
