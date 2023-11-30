package menu;

import config.ValidationUtil;
import entity.EducationEmployee;
import entity.Lesson;
import entity.Student;
import entity.Teacher;
import entity.enums.Gender;
import entity.enums.TeacherLevel;
import org.xml.sax.SAXException;
import util.ApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.*;

public class TeacherMenu {
    public static void teacherMenu() throws IOException, SAXException {
        Scanner scanner = new Scanner(System.in);


        System.out.println("login menu:");
        System.out.println("enter username :");
        String username = scanner.nextLine();
        System.out.println("enter your password :");
        String password = scanner.nextLine();
        Object byUser = ApplicationContext.getTeacherService().findByUser(username, password);
        if (byUser != null) {
            Long idFromUsername = ApplicationContext.getTeacherService().getIdFromUsername(username);
            teacherPrivateMenu(idFromUsername, username, password);
        }
    }

    private static void teacherPrivateMenu(Long teacherId,String teacherUsername, String password) throws IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to Teacher panel");
        System.out.println("choose: \n" +
                "1.view your profile : \n" +
                "2.submit students grades  : \n" +
                "3.see salary paySlip  : \n" +
                "4.exit");
        int input = scanner.nextInt();
        if (input == 1){
            System.out.println(ApplicationContext.getTeacherService().findByUser(teacherUsername, password));

        }
        if (input == 2){

            System.out.println("enter student id that you want to enter grade");
            Long studentId = scanner.nextLong();
            System.out.println("enter lesson id that you want to enter grade");
            Long lessonId = scanner.nextLong();
            System.out.println("enter grade's number");
            Double grade = scanner.nextDouble();
            ApplicationContext.getTeacherService().submitGrades(lessonId, teacherId, studentId, grade);
        }
        if (input == 3){
            System.out.println("What semester are you considering?");
            int sem = scanner.nextInt();
            Optional<Teacher> byId = ApplicationContext.getTeacherService().findById(teacherId);
            if(byId.isPresent()) {
                Teacher t = byId.get();
                System.out.println(t);
            }
            System.out.println("salary : ");
            ApplicationContext.getTeacherService().paySlip(teacherId, sem);
        }
        if (input == 4){
            MainMenu.mainMenu();
        }

    }

    public static Teacher teacherInstance(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


        Teacher teacher = new Teacher();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter teacher username:");
        String username = scanner.nextLine();
        teacher.setUsername(username);

        System.out.println("enter teacher password:");
        String password = scanner.nextLine();
        teacher.setPassword(password);

        System.out.println("enter teacher firstname:");
        String firstname = scanner.nextLine();
        teacher.setFirstname(firstname);

        System.out.println("enter teacher lastname:");
        String lastname = scanner.nextLine();
        teacher.setLastname(lastname);

        System.out.println("choose gender: \n 1.male \n 2.female");
        int input = scanner.nextInt();
        if(input == 1){
            teacher.setGender(Gender.MALE);}
        if (input == 2){
            teacher.setGender(Gender.FEMALE);}

        System.out.println("enter teacher nationalCode:");
        long nationalCode = scanner.nextLong();
        teacher.setNationalCode(nationalCode);

        System.out.println("what is your teaching level: \n 1.FACULTY_MEMBER  \n 2.TUITION");
        int selection = scanner.nextInt();
        if (selection == 1){
            teacher.setTeacherLevel(TeacherLevel.FACULTY_MEMBER);}
        if (selection == 2){
            teacher.setTeacherLevel(TeacherLevel.TUITION);}

        try {
            ValidationUtil.validate(teacher);
        } catch (RuntimeException e) {
            try {
                EmployeeMenu.employeeMenu();
            } catch (IOException | SAXException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return teacher;
    }

}
