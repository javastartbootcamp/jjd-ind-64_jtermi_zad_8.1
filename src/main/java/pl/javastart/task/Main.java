package pl.javastart.task;

public class Main {

    // uzupełnij metody w UniversityApp zgodnie z dokumentacją
    public static void main(String[] args) {
        UniversityApp universityApp = new UniversityApp();

        universityApp.createLecturer(1, "dr", "Janusz", "Rataj");
        universityApp.createGroup("pp-2022", "Podstawy Programowania", 1);
        universityApp.createGroup("po-2022", "Programowanie Obiektowe", 1);
        universityApp.addStudentToGroup(179128, "pp-2022", "Marcin", "Abacki");
        universityApp.addStudentToGroup(179128, "po-2022", "Marcin", "Abacki");
        universityApp.addStudentToGroup(179129, "pp-2022", "Adam", "Browarski");
        universityApp.addStudentToGroup(179129, "po-2022", "Adam", "Browarski");
        universityApp.addStudentToGroup(179130, "po-2022", "Marcin", "Abacki");

        System.out.println("Wydruk listy wszystich studentow");
        universityApp.printAllStudents();

        universityApp.addGrade(179128, "pp-2022", 5);
        universityApp.addGrade(179128, "po-2022", 5.5);
        universityApp.addGrade(179128, "po-2022", 5.5);
        universityApp.addGrade(179130, "po-2022", 5.5);

        universityApp.printGroupInfo("pp-2022");
        System.out.println();
        System.out.println("Wydruk listy ocen dla grupy");
        universityApp.printGradesForGroup("pp-2022");
        System.out.println();

        // when
        System.out.println("Wydruk ocen dla studenta");
        universityApp.printGradesForStudent(179128);
    }

}
