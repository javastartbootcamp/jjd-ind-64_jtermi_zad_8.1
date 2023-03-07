package pl.javastart.task;

public class Student extends Person {
    private int index;

    public Student(String firstName, String lastName, int index) {
        super(firstName, lastName);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void printStudentInfo() {
        System.out.print(this.index + " "
                + this.getFirstName() + " "
                + this.getLastName() + ": ");
    }

}