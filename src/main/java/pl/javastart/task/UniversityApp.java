package pl.javastart.task;

public class UniversityApp {

    private Lecturer[] lecturers = new Lecturer[1000];
    private int lecturersCounter = 0;
    private Group[] groups = new Group[1000];
    private int groupsCounter = 0;
    private Student[] students = new Student[1000];
    private int studentsCounter = 0;
    private Grade[] grades = new Grade[1000];
    private int gradesCounter = 0;

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
        if (findLecturerById(id) == null) {
            lecturers[lecturersCounter] = new Lecturer(firstName, lastName, id, degree);
            System.out.println("Utworzono nowego Lektora"
                    + lecturers[lecturersCounter].getFirstName() + " "
                    + lecturers[lecturersCounter].getLastName());
            lecturersCounter++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }
    }

    private Lecturer findLecturerById(int id) {
        Lecturer lecturer = null;
        int i = 0;
        while (i < lecturersCounter && lecturer == null) {
            if (lecturers[i].getId() == id) {
                lecturer = lecturers[i];
            }
            i++;
        }
        return lecturer;
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

        Lecturer lecturerById = findLecturerById(lecturerId);
        Group groupByCode = findGroupByCode(code);

        if (lecturerById == null) {
            System.out.println("Prowadzący z id " + lecturerId + " nie istnieje");
        }

        if (groupByCode != null) {
            System.out.println("Grupa " + code + " już istnieje");
        }

        if ((lecturerById != null) && (groupByCode == null)) {
            groups[groupsCounter] = new Group(code, name, lecturerById);
            groupsCounter++;
        }
    }

    private Group findGroupByCode(String code) {
        Group group = null;
        int i = 0;
        while (i < groupsCounter && group == null) {
            if (groups[i].getGroupId() == code) {
                group = groups[i];
            }
            i++;
        }
        return group;
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

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Student student = findStudentByIndex(index);
        Group group = findGroupByCode(groupCode);

        if (student == null) {
            student = addStudent(index, firstName, lastName);
        }

        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");

        } else if (group.studentBelongsToGroup(student)) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        } else {
            group.addStudentToGroup(student);
        }
    }

    private Student findStudentByIndex(int index) {
        Student student = null;
        int i = 0;
        while (i < studentsCounter && student == null) {
            if (students[i].getIndex() == index) {
                student = students[i];
            }
            i++;
        }
        return student;
    }

    private Student addStudent(int index, String firstName, String lastName) {
        students[studentsCounter] = new Student(firstName, lastName, index);
        studentsCounter++;
        return students[studentsCounter - 1];
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
        Group group = findGroupByCode(groupCode);

        if (group != null) {
            System.out.printf("Kod: %-10s\n", group.getGroupId());
            System.out.printf("Nazwa: %-10s\n", group.getGroupName());
            System.out.println("Prowadzący: "
                    + group.getLecturer().getDegree() + " "
                    + group.getLecturer().getFirstName() + " "
                    + group.getLecturer().getLastName());
            System.out.println("Uczestnicy:");

            for (int i = 0; i < group.getIndexCounter(); i++) {
                System.out.println(group.getStudent(i).getIndex() + " "
                        + group.getStudent(i).getFirstName() + " "
                        + group.getStudent(i).getLastName());
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
        Student student = findStudentByIndex(studentIndex);
        Group group = findGroupByCode(groupCode);

        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else if (!group.studentBelongsToGroup(student)) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
        } else if (studentHasGrade(student)) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
        }
        addGrade(student, group, grade);
    }

    private void addGrade(Student student, Group group, double grade) {
        grades[gradesCounter] = new Grade(student, group, grade);
        gradesCounter++;
    }

    private boolean studentHasGrade(Student student) {
        boolean gradeFound = false;
        int i = 0;
        while (!gradeFound && i < gradesCounter) {
            if (grades[i].getStudent() == student && grades[i].getGrade() > 0) {
                gradeFound = true;
            }
            i++;
        }
        return gradeFound;
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
            Student student = findStudentByIndex(index);
            if (grades[i].getStudent() == student) {
                System.out.println(grades[i].getGroup().getGroupName() + ": "
                    + grades[i].getGrade());
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
        Group group = findGroupByCode(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else {
            for (int i = 0; i < gradesCounter; i++) {
                if (grades[i].getGroup() == group) {
                    Student student = grades[i].getStudent();
                    System.out.println(student.getIndex() + " "
                            + student.getFirstName() + " "
                            + student.getLastName() + ": "
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