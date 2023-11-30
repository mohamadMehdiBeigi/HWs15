package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class ApplicationContext {

    private static final EducationEmployeeRepository EDUCATION_EMPLOYEE_REPOSITORY;
    private static final EducationEmployeeService EDUCATION_EMPLOYEE_SERVICE;
    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentService STUDENT_SERVICE;
    private static final TeacherRepository TEACHER_REPOSITORY;
    private static final TeacherService TEACHER_SERVICE;
    private static final LessonRepository LESSON_REPOSITORY;
    private static final LessonService LESSON_SERVICE;
    private static final LessonGradesRepository LESSON_GRADES_REPOSITORY;
    private static final LessonGradeService LESSON_GRADE_SERVICE;
    private static final PersonRepository PERSON_REPOSITORY;
    private static final PersonService PERSON_SERVICE;

    static {
        EDUCATION_EMPLOYEE_REPOSITORY = new EducationEmployeeRepositoryImpl();
        EDUCATION_EMPLOYEE_SERVICE = new EducationEmployeeServiceImpl(EDUCATION_EMPLOYEE_REPOSITORY);

        STUDENT_REPOSITORY = new StudentRepositoryImpl();
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);

        TEACHER_REPOSITORY = new TeacherRepositoryImpl();
        TEACHER_SERVICE = new TeacherServiceImpl(TEACHER_REPOSITORY);

        LESSON_REPOSITORY = new LessonRepositoryImpl();
        LESSON_SERVICE = new LessonServiceImpl(LESSON_REPOSITORY);

        LESSON_GRADES_REPOSITORY = new LessonGradesRepositoryImpl();
        LESSON_GRADE_SERVICE = new LessonGradeServiceImpl(LESSON_GRADES_REPOSITORY);

        PERSON_REPOSITORY = new PersonRepositoryImpl();
        PERSON_SERVICE = new PersonServiceImpl(PERSON_REPOSITORY);


    }

    public static EducationEmployeeService getEducationEmployeeService() {
        return EDUCATION_EMPLOYEE_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }

    public static LessonService getLessonService(){
        return LESSON_SERVICE;
    }

    public static LessonGradeService getLessonGradeService(){ return LESSON_GRADE_SERVICE;}
    public static PersonService getPersonService(){ return PERSON_SERVICE;}

}
