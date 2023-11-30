package menu;

import config.ValidationUtil;
import entity.Lesson;
import entity.Teacher;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Scanner;

public class LessonMenu {
    public static Lesson lessonInstance(){
        Lesson lesson = new Lesson();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter lesson name :");
        String name = scanner.nextLine();
        lesson.setLessonName(name);

        System.out.println("enter lesson unit :");
        int unit = scanner.nextInt();
        scanner.nextLine();
        lesson.setLessonUnit(unit);

        System.out.println("enter lesson field:");
        String field = scanner.nextLine();
        lesson.setField(field);

        System.out.println("enter semester that lesson published");
        Integer semester = scanner.nextInt();
        lesson.setSemester(semester);
        scanner.nextLine();

        System.out.println("whats the teacher's id that teaching this lesson");
        Long teacherId = scanner.nextLong();
        scanner.nextLine();
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        lesson.setTeacher(teacher);

        try {
            ValidationUtil.validate(lesson);
        } catch (RuntimeException e) {
            System.err.println("validation failed. try later.");
        }

        return lesson;
    }
}
