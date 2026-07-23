package exercise4;

public class EmployeeManagement {

    Employee[] employees = new Employee[10];
    int count = 0;

    void add(Employee e) {
        if (count >= employees.length) {
            System.out.println("Employee directory is full!");
            return;
        }
        employees[count++] = e;
    }

    void search(int id) {
        for(int i=0;i<count;i++) {
            if(employees[i].employeeId==id) {
                System.out.println(employees[i]);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    void traverse() {
        for(int i=0;i<count;i++)
            System.out.println(employees[i]);
    }

    void delete(int id) {
        for(int i=0;i<count;i++) {
            if(employees[i].employeeId==id) {
                for(int j=i;j<count-1;j++)
                    employees[j]=employees[j+1];
                employees[count-1] = null; // Clean up reference
                count--;
                return;
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagement obj =
                new EmployeeManagement();

        obj.add(new Employee(1,"Arun","Manager",50000));
        obj.add(new Employee(2,"Kumar","Developer",40000));

        System.out.println("Initial Employee List:");
        obj.traverse();

        obj.delete(1);

        System.out.println("\nAfter Delete:");
        obj.traverse();
    }
}
