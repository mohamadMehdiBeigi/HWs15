package menu;

import config.ValidationUtil;
import entity.*;
import entity.enums.EducationLevel;
import entity.enums.Gender;
import org.xml.sax.SAXException;
import util.ApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class StudentMenu {
    public static void studentMenu() throws IOException, SAXException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("login menu:");
        System.out.println("enter username :");
        String input3 = scanner.nextLine();
        System.out.println("enter your password :");
        String input4 = scanner.nextLine();
        Object byUser = ApplicationContext.getStudentService().findByUser(input3, input4);
        if (byUser != null) {
            Long idFromUsername = ApplicationContext.getStudentService().getIdFromUsername(input3);
            studentPrivateMenu(idFromUsername,input3, input4);
        }


    }

    private static void studentPrivateMenu(Long id ,String studentUsername, String password) throws IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to Student panel");
        System.out.println("choose : " +
                "1.view your profile :  \n" +
                "2.see lessons  : \n" +
                "3.choosing course unit :  \n" +
                "4.Viewing the lessons taken along with their grades : \n " +
                "5.exit");
        int input1 = scanner.nextInt();
        if (input1 == 1) {
            System.out.println(ApplicationContext.getStudentService().findByUser(studentUsername, password));
        }
        if (input1 == 2) {
            System.out.println(ApplicationContext.getLessonService().findAll());
        }
        if (input1 == 3) {
            System.out.println("enter your chosen lesson id");
            Long input10 = scanner.nextLong();
            Lesson lesson = new Lesson();
            lesson.setId(input10);

            System.out.println("enter your chosen Teacher id");
            Long input20 = scanner.nextLong();

            Teacher teacher = new Teacher();
            teacher.setId(input20);

            Student student = new Student();
            student.setId(id);
            LessonGrades lessonGrades = new LessonGrades(lesson,student,teacher);
            Integer currentSemester = ApplicationContext.getLessonGradeService().currentSemester(id);
            ApplicationContext.getLessonGradeService().selectCourseStudent(lessonGrades, currentSemester, id);
        }
        if (input1 == 4) {
            Integer currentSemester = ApplicationContext.getLessonGradeService().currentSemester(id);
            ApplicationContext.getStudentService().takenLesson(currentSemester, id);
        }
        if (input1 == 5) {
            MainMenu.mainMenu();
        }


    }

    public static Student studentInstance(){
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter student username:");
        String username = scanner.nextLine();
        student.setUsername(username);

        System.out.println("enter student password:");
        String password = scanner.nextLine();
        student.setPassword(password);

        System.out.println("enter student firstname:");
        String firstname = scanner.nextLine();
        student.setFirstname(firstname);

        System.out.println("enter student lastname:");
        String lastname = scanner.nextLine();
        student.setLastname(lastname);

        System.out.println("choose gender: \n 1.male \n 2.female");
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> student.setGender(Gender.MALE);
            case 2 -> student.setGender(Gender.FEMALE);
        }

        System.out.println("enter student nationalCode:");
        long nationalCode = scanner.nextLong();
        scanner.nextLine();
        student.setNationalCode(nationalCode);

        System.out.println("enter student study field");
        String studyField = scanner.nextLine();
        student.setStudyField(studyField);

        System.out.println("choose education level:  \n 1.associate  \n 2.bachelor  \n 3.master \n 4.phd");
        int input2 = scanner.nextInt();
        switch (input2){
            case 1 -> student.setEducationLevel(EducationLevel.ASSOCIATE);
            case 2 -> student.setEducationLevel(EducationLevel.BACHELOR);
            case 3 -> student.setEducationLevel(EducationLevel.MASTER);
            case 4 -> student.setEducationLevel(EducationLevel.PHD);
        }

        try {
            ValidationUtil.validate(student);
        } catch (RuntimeException e) {
            try {
                EmployeeMenu.employeeMenu();
            } catch (IOException | SAXException ex) {
                System.err.println(ex.getMessage());
            }
        }


        return student;
    }

}
