package exercise4;

public class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int id,String name,
                    String position,double salary) {
        this.employeeId=id;
        this.name=name;
        this.position=position;
        this.salary=salary;
    }

    public String toString() {
        return employeeId+" "+name+
               " "+position+" "+salary;
    }
}
