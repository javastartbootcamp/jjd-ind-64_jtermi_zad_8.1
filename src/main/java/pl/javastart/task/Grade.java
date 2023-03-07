package pl.javastart.task;

public class Grade {

    private Student student;
    private Group group;
    private double grade;

    public  Grade(Student student, Group group, double grade) {
        this.student = student;
        this.group = group;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public Group getGroup() {
        return group;
    }

    public double getGrade() {
        return grade;
    }
}