package menu;

import config.ValidationUtil;
import entity.EducationEmployee;
import entity.Lesson;
import entity.Student;
import entity.Teacher;
import entity.enums.Gender;
import org.xml.sax.SAXException;
import util.ApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeMenu {

    public static void employeeMenu() throws IOException, SAXException {
        String employeeUsername;

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. login :  \n 2. signUp :");
        int input20 = scanner.nextInt();
        scanner.nextLine();
        if (input20 == 1) {
            System.out.println("enter username :");
            String input3 = scanner.nextLine();
            employeeUsername = input3;
            System.out.println("enter your password :");

            String input4 = scanner.nextLine();
            Object byUser = ApplicationContext.getEducationEmployeeService().findByUser(input3, input4);
            if (byUser != null) {
                employeePrivateMenu(employeeUsername);
            }
        }
        if (input20 == 2) {
            EducationEmployee employeeMenu = employeeInstance();
            ApplicationContext.getEducationEmployeeService().saveOrUpdate(employeeMenu);
            String username = employeeMenu.getUsername();
            System.out.println("you signed up successfully");

            employeePrivateMenu(username);
        }
    }


    private static void employeePrivateMenu(String employeeUsername) throws IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to Employee panel");
        System.out.println("choose: \n 1. students: \n 2. Teachers  \n 3. EducationEmployee: \n 4. Lessons \n 5. exit");

        int input1 = scanner.nextInt();
        if (input1 == 1) {
            System.out.println("What order do you want to do? \n 1:sign up \n 2: remove \n 3: disable \n 4: update");
            int input2 = scanner.nextInt();
            if (input2 == 1) {
                ApplicationContext.getStudentService().saveOrUpdate(StudentMenu.studentInstance());
            }
            if (input2 == 2) {
                System.out.println("enter your desire ID");
                long input3 = scanner.nextLong();
                ApplicationContext.getStudentService().deleteForeignKeys(input3);
                ApplicationContext.getStudentService().deleteById(input3);
                System.out.println("deleted successfully");
            }
            if (input2 == 3) {
                System.out.println("enter your desire ID");
                long input5 = scanner.nextLong();
                ApplicationContext.getStudentService().changeStatus(input5);
                System.out.println("account disable successfully");
            }
            if (input2 == 4){
                System.out.println("enter your id");
                Long id = scanner.nextLong();
                Student student = StudentMenu.studentInstance();
                student.setId(id);
                ApplicationContext.getStudentService().update(student);

            }
        }
        if (input1 == 2) {
            System.out.println("What order do you want to do? \n 1:sign up  \n 2: remove \n 3.disable \n 4.update");
            int input4 = scanner.nextInt();
            if (input4 == 1) {
                ApplicationContext.getTeacherService().saveOrUpdate(TeacherMenu.teacherInstance());
            }
            if (input4 == 2) {
                System.out.println("enter your desire ID");
                long input5 = scanner.nextLong();
                ApplicationContext.getTeacherService().deleteForeignKeys(input5);
                ApplicationContext.getTeacherService().deleteById(input5);
                System.out.println("deleted successfully");
            }
            if (input4 == 3) {
                System.out.println("enter your desire ID");
                long input5 = scanner.nextLong();
                ApplicationContext.getTeacherService().changeStatus(input5);
                System.out.println("account disable successfully");

            }
            if (input4 == 4){
                System.out.println("enter your id");
                Long id = scanner.nextLong();
                Teacher teacher = TeacherMenu.teacherInstance();
                teacher.setId(id);
                ApplicationContext.getTeacherService().update(teacher);

            }


        }
        if (input1 == 3) {
            System.out.println("What order do you want to do? \n 1:sign up \n 2: remove \n 3: see salary \n 4: disable  \n 5.update");
            int input6 = scanner.nextInt();
            if (input6 == 1) {
                ApplicationContext.getEducationEmployeeService().saveOrUpdate(employeeInstance());
            }
            if (input6 == 2) {
                System.out.println("enter your desire ID");
                long input7 = scanner.nextLong();
                ApplicationContext.getEducationEmployeeService().deleteById(input7);

                System.out.println("deleted successfully");
            }
            if (input6 == 3) {
                System.out.println("here is your salary & specification:");
                System.out.println(ApplicationContext.getEducationEmployeeService().paySlip(employeeUsername));


            }
            if (input6 == 4) {
                System.out.println("enter your desire ID");
                long input5 = scanner.nextLong();
                ApplicationContext.getEducationEmployeeService().changeStatus(input5);
                System.out.println("account disable successfully");
            }
            if (input6 == 5){
                System.out.println("enter your id");
                Long id = scanner.nextLong();
                EducationEmployee employee = employeeInstance();
                employee.setId(id);
                ApplicationContext.getEducationEmployeeService().update(employee);

            }
        }
        if (input1 == 4) {
            System.out.println("What order do you want to do? \n 1:sign up  \n 2: remove \n 3: update");
            int input8 = scanner.nextInt();
            if (input8 == 1) {
                ApplicationContext.getLessonService().saveOrUpdate(LessonMenu.lessonInstance());
            }
            if (input8 == 2) {
                System.out.println("enter your desire ID");
                long input9 = scanner.nextLong();
                ApplicationContext.getLessonService().deleteById(input9);
                System.out.println("deleted successfully");
            }
            if (input8 == 3){
                System.out.println("enter your id");
                Long id = scanner.nextLong();
                Lesson lesson = LessonMenu.lessonInstance();
                lesson.setId(id);
                ApplicationContext.getLessonService().update(lesson);

            }
        }
        if (input1 == 5) {
            MainMenu.mainMenu();
        }


    }

    public static EducationEmployee employeeInstance() {
        EducationEmployee employee = new EducationEmployee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter employee username:");
        String username = scanner.nextLine();
        employee.setUsername(username);

        System.out.println("enter employee password:");
        String password = scanner.nextLine();
        employee.setPassword(password);

        System.out.println("enter employee firstname:");
        String firstname = scanner.nextLine();
        employee.setFirstname(firstname);

        System.out.println("enter employee lastname:");
        String lastname = scanner.nextLine();
        employee.setLastname(lastname);

        System.out.println("choose gender: \n 1.male \n 2.female");
        int input = scanner.nextInt();
        if (input == 1) {
            employee.setGender(Gender.MALE);
        }
        if (input == 2) {
            employee.setGender(Gender.FEMALE);
        }
        System.out.println("enter nationalCode:");
        long nationalCode = scanner.nextLong();
        employee.setNationalCode(nationalCode);
        System.out.println("enter salary");
        Double salary = scanner.nextDouble();
        employee.setSalary(salary);

        try {
            ValidationUtil.validate(employee);
        } catch (RuntimeException e) {
            try {
                employeeMenu();
            } catch (IOException | SAXException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return employee;
    }
}
