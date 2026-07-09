public class StudentController {

    Student model;
    StudentView view;

    public StudentController(
            Student model,
            StudentView view) {

        this.model=model;
        this.view=view;
    }

    void updateGrade(
            String grade) {
        model.setGrade(grade);
    }

    void updateView() {
        view.displayStudentDetails(
                model);
    }
}