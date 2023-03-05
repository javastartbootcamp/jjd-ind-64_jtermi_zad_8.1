package pl.javastart.task;

import java.sql.SQLOutput;

public class UniversityApp {

    private Lecturer[] lecturers = new Lecturer[1000];
    private int lecturersCounter = 0;
    private Group[] groups = new Group[1000];
    private int groupsCounter = 0;
    private Student[] students = new Student[1000];
    private int studentsCounter = 0;
    private Grade[] grades = new Grade[1000];
    int gradesCounter = 0;

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (!isLecturerExists(id)) {
            lecturers[lecturersCounter] = new Lecturer(firstName, lastName, id, degree);
            lecturersCounter++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }
    }

    public boolean isLecturerExists(int id) {
        boolean lecturerExists = false;
        int i = 0;
        while (!lecturerExists && i < lecturersCounter) {
            if (lecturers[i].getId() == id) {
                lecturerExists = true;
            }
            i++;
        }
        return lecturerExists;
    }

    public void printLecturerInfo(int id) {
        int i = 0;
        while (lecturers[i].getId() != id) {
            i++;
        }
        System.out.printf("Prowadzący: %s %s %s \n", lecturers[i].getDegree(),
                lecturers[i].getFirstName(),
                lecturers[i].getLastName());
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */

    public void createGroup(String code, String name, int lecturerId) {

        if (!isLecturerExists(lecturerId)) {
            System.out.println("Prowadzący z id " + lecturerId + " nie istnieje");
        }
        if (isGroupExists(code)) {
            System.out.println("Grupa " + code + " już istnieje");
        }

        if (isLecturerExists(lecturerId) && !isGroupExists(code)) {
            groups[groupsCounter] = new Group(code, name, lecturerId);
            groupsCounter++;
        }
    }

    public boolean isGroupExists(String code) {
        boolean groupIsExisting = false;
        int i = 0;

        while (!groupIsExisting && i < groupsCounter) {
            if (groups[i].getGroupId() == code) {
                groupIsExisting = true;
            }
            i++;
        }
        return groupIsExisting;
    }

    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */

    public boolean isStudentExists(int index) {
        boolean studentExists = false;
        int i = 0;
        while (i < studentsCounter && !studentExists) {
            if (students[i].getIndex() == index) {
                studentExists = true;
            }
            i++;
        }
        return studentExists;
    }

    public void addStudent(int index, String firstName, String lastName) {
        students[studentsCounter] = new Student(firstName, lastName, index);
        studentsCounter++;
    }

    public boolean studentBelongsToGroup(int studentIndex, String groupCode) {
        int groupCounter = getGroupCounter(groupCode);
        return groups[groupCounter].isBelongsToGroup(studentIndex);
    }

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {

        if (!isStudentExists(index)) {
            addStudent(index, firstName, lastName);
        }

        if (!isGroupExists(groupCode)) {
            System.out.println("Grupa " + groupCode + " nie istnieje");

        } else if (studentBelongsToGroup(index, groupCode)) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        } else {
            int groupMarker = getGroupCounter(groupCode);
            groups[groupMarker].addStudentIndex(index);
        }
    }

    public int getGroupCounter(String groupCode) {
        int i = 0;
        while (groups[i].getGroupId() != groupCode) {
            i++;
        }
        return i;
    }
    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */

    public void printGroupInfo(String groupCode) {

        if (isGroupExists(groupCode)) {
            int groupMarker = getGroupCounter(groupCode);
            System.out.printf("Kod: %-10s\n", groups[groupMarker].getGroupId());
            System.out.printf("Nazwa: %-10s\n", groups[groupMarker].getGroupName());
            printLecturerInfo(groups[groupMarker].getLecturerId());
            System.out.println("Uczestnicy:");

            for (int i = 0; i < groups[groupMarker].getIndexCounter(); i++) {
                int index = groups[groupMarker].getStudentsIndex(i);
                System.out.println(index + " "
                        + students[getStudentsCounter(index)].getFirstName() + " "
                        + students[getStudentsCounter(index)].getLastName());
            }

        } else {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
        }
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
     * "Grupa pp-2022 nie istnieje"
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */

    public void addGrade(int studentIndex, String groupCode, double grade) {
        if (!isGroupExists(groupCode)) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else if (!studentBelongsToGroup(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
        } else if (studentHasGrade(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
        }
        setGrade(studentIndex, groupCode, grade);
    }

    public boolean studentHasGrade(int studentIndex, String groupCode) {
        boolean gradeFound = false;
        int i = 0;
        while (!gradeFound && i < gradesCounter) {
            if (grades[i].getStudentIndex() == studentIndex && grades[i].getGrade() > 0) {
                gradeFound = true;
            }
            i++;
        }
        return gradeFound;
    }

    public int getStudentsCounter(int studentIndex) {
        int i = 0;
        while (i < studentsCounter && students[i].getIndex() != studentIndex) {
            i++;
        }
        return i;
    }

    public void setGrade(int studentIndex, String groupCode, double grade) {
        grades[gradesCounter] = new Grade();
        grades[gradesCounter].setGrade(grade);
        grades[gradesCounter].setGroupId(groupCode);
        grades[gradesCounter].setStudentIndex(studentIndex);
        gradesCounter++;
    }

        /**
         * Wyświetla wszystkie oceny studenta.
         * Przykładowy wydruk:
         * Podstawy programowania: 5.0
         * Programowanie obiektowe: 5.5
         *
         * @param index - numer indesku studenta dla którego wyświetlić oceny
         */
    public void printGradesForStudent(int index) {
        for (int i = 0; i < groupsCounter; i++) {
            if (grades[i].getStudentIndex() == index) {
                System.out.println(groups[getGroupCounter(grades[i].getGroupId())].getGroupName()
                    + ": " + grades[i].getGrade());
            }
        }
    }

        /**
         * Wyświetla oceny studentów dla wskazanej grupy.
         * Przykładowy wydruk:
         * 179128 Marcin Abacki: 5.0
         * 179234 Dawid Donald: 4.5
         * 189521 Anna Kowalska: 5.5
         *
         * @param groupCode - kod grupy, dla której wyświetlić oceny
         */

    public void printGradesForGroup(String groupCode) {

        if (!isGroupExists(groupCode)) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else {
            for (int i = 0; i < gradesCounter; i++) {
                if (grades[i].getGroupId() == groupCode) {
                    int numerStudenta = getStudentsCounter(grades[i].getStudentIndex());
                    System.out.println(grades[i].getStudentIndex()
                            + " " + students[numerStudenta].getFirstName()
                            + " " + students[numerStudenta].getLastName() + ": "
                            + grades[i].getGrade());
                }
            }
        }
    }

        /**
         * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
         * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
         * Przykładowy wydruk:
         * 179128 Marcin Abacki
         * 179234 Dawid Donald
         * 189521 Anna Kowalska
         */
    public void printAllStudents() {
        for (int i = 0; i < studentsCounter; i++) {
            System.out.println(students[i].getIndex() + " "
                    + students[i].getFirstName() + " "
                    + students[i].getLastName());
        }
    }
}