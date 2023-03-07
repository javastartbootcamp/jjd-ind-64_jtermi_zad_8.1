package pl.javastart.task;

import java.util.Arrays;

public class Group {

    private String groupId;
    private String groupName;
    private Lecturer lecturer;
    private Student[] students = new Student[10];
    private int indexCounter;

    public int getIndexCounter() {
        return indexCounter;
    }

    public Student getStudent(int indexCounter) {
        return students[indexCounter];
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Group(String groupId, String groupName, Lecturer lecturer) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.lecturer = lecturer;
        this.indexCounter = 0;
    }

    public boolean studentBelongsToGroup(Student student) {
        for (int i = 0; i < indexCounter; i++) {
            if (students[i] == student) {
                return true;
            }
        }
        return false;
    }

    public void addStudentToGroup(Student student) {
        ensureCapacity();
        students[indexCounter] = student;
        indexCounter++;
    }

    private void ensureCapacity() {
        if (indexCounter >= students.length) {
            students = Arrays.copyOf(this.students, students.length * 2);
        }
    }

    public void printGroupInfo() {
        System.out.printf("Kod: %-10s\n", this.groupId);
        System.out.printf("Nazwa: %-10s\n", this.groupName);
        System.out.print("Prowadzący: ");

        this.lecturer.printLecturerInfo();

        System.out.println("Uczestnicy:");

        for (int i = 0; i < this.getIndexCounter(); i++) {
            System.out.println(this.getStudent(i).getIndex() + " "
                    + this.getStudent(i).getFirstName() + " "
                    + this.getStudent(i).getLastName());
        }
    }

}