package menu;

import org.xml.sax.SAXException;
import util.ApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    public static void mainMenu() throws IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome!!! please select your section \n" +
                " 1.Education Employee :  \n" +
                " 2.Teacher :  \n " +
                " 3.Student :");
        int input1 = scanner.nextInt();
        if (input1 == 1) {
            EmployeeMenu.employeeMenu();
        }

        if (input1 == 2) {
            TeacherMenu.teacherMenu();
        }
        if (input1 == 3) {
            StudentMenu.studentMenu();
        }
    }
}
