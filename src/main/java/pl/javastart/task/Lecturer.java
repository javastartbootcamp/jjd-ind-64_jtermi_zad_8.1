package pl.javastart.task;

public class Lecturer extends Person {

    private int id;
    private String degree;

    public Lecturer(String firstName, String lastName, int id, String degree) {
        super(firstName, lastName);
        this.id = id;
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void printLecturerInfo() {
        System.out.println(this.degree + " "
                + this.getFirstName() + " "
                + this.getLastName());
    }
}
