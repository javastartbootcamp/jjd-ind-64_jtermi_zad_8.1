package pl.javastart.task;

public class Group {

    private String groupId;
    private String groupName;
    private Lecturer lecturer;
    private Student[] students = new Student[1000];
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

    public Lecturer getLecturer() {
        return lecturer;
    }

    public Group(String groupId, String groupName, Lecturer lecturer) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.lecturer = lecturer;
        this.indexCounter = 0;
    }

    public boolean studentBelongsToGroup(Student student) {
        int i = 0;
        boolean belongsToGroup = false;
        while (i < indexCounter && !belongsToGroup) {
            if (students[i] == student) {
                belongsToGroup = true;
            }
            i++;
        }
        return belongsToGroup;
    }

    public void addStudentToGroup(Student student) {
        students[indexCounter] = student;
        indexCounter++;
    }
}