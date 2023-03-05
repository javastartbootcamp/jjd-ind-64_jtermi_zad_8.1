package pl.javastart.task;

public class Group {

    private String groupId;
    private String groupName;
    private int lecturerId;
    private int[] studentIndex;
    private int indexCounter;

    public Group(String groupId, String groupName, int lecturerId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.lecturerId = lecturerId;
        this.studentIndex = new int[1000];
        this.indexCounter = 0;
    }

    public int getIndexCounter() {
        return this.indexCounter;
    }

    public int getStudentsIndex(int index) {
        return this.studentIndex[index];
    }

    public void addStudentIndex(int index) {
        this.studentIndex[indexCounter] = index;
        indexCounter++;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public boolean isBelongsToGroup(int index) {
        int i = 0;
        boolean belongsToGroup = false;
        while (i < indexCounter && !belongsToGroup) {
            if (studentIndex[i] == index) {
                belongsToGroup = true;
            }
            i++;
        }
        return belongsToGroup;
    }
}