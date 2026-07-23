public class StudentView {

    void displayStudentDetails(
            Student s) {

        System.out.println(
            s.getId()+" "+
            s.getName()+" "+
            s.getGrade());
    }
}