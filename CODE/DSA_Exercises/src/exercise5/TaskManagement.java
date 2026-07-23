package exercise5;

public class TaskManagement {

    Task head;

    void add(int id,String name,String status) {
        Task t = new Task(id,name,status);

        if(head==null)
            head=t;
        else {
            Task temp=head;
            while(temp.next!=null)
                temp=temp.next;
            temp.next=t;
        }
    }

    void display() {
        Task temp=head;

        while(temp!=null) {
            System.out.println(
                temp.taskId+" "+
                temp.taskName+" "+
                temp.status);
            temp=temp.next;
        }
    }

    void delete(int id) {
        if(head == null) return;
        if(head.taskId==id) {
            head=head.next;
            return;
        }

        Task temp=head;
        while(temp.next!=null &&
              temp.next.taskId!=id)
            temp=temp.next;

        if(temp.next!=null)
            temp.next=temp.next.next;
    }

    public static void main(String[] args) {

        TaskManagement tm =
                new TaskManagement();

        tm.add(1,"Coding","Pending");
        tm.add(2,"Testing","Completed");

        System.out.println("Initial Tasks:");
        tm.display();

        tm.delete(1);

        System.out.println("\nAfter Delete:");
        tm.display();
    }
}
