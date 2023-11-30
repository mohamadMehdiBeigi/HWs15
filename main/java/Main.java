import entity.Student;
import entity.enums.EducationLevel;
import entity.enums.Gender;
import menu.MainMenu;
import org.checkerframework.checker.units.qual.A;
import org.xml.sax.SAXException;
import util.ApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {
        Student student = new Student(1L,"aerrfrrf", "Admin@", "ali", "abcd", Gender.MALE, 12345L, EducationLevel.BACHELOR, "omran");


//        ApplicationContext.getStudentService().saveOrUpdate(student);
//        ApplicationContext.getStudentService().deleteById(1L);
//        System.out.println(ApplicationContext.getStudentService().findById(1L));
//        System.out.println(ApplicationContext.getStudentService().findAll());
        MainMenu.mainMenu();
//        System.out.println(ApplicationContext.getStudentService().getIdFromUsername("beigi"));
//        System.out.println(ApplicationContext.getTeacherService().paySlip(10L, 1));
//        ApplicationContext.getStudentService().update(student);
    }
}
