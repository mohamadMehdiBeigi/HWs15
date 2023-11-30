package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.Teacher;
import entity.enums.LessonGradeStatus;
import entity.enums.TeacherLevel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import repository.TeacherRepository;
import util.SessionFactoryProvider;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher, Long> implements TeacherRepository {
    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public void submitGrades(Long lessonId, Long teacherId, Long studentId, Double newGrade) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update LessonGrades lg " +
                    "set grade =:newGrade, lessonGradeStatus =:LessonGradeStatus  " +
                    "where lg.teacher.id =:teacherId and lg.student.id =:studentId and lg.lesson.id =:lessonId");
            query.setParameter("teacherId", teacherId);
            query.setParameter("newGrade", newGrade);
            query.setParameter("studentId", studentId);
            query.setParameter("lessonId", lessonId);
            if (newGrade >= 10) {
                query.setParameter("LessonGradeStatus", LessonGradeStatus.PASS);
            }
            if (newGrade < 10) {
                query.setParameter("LessonGradeStatus", LessonGradeStatus.NOT_PASS);
            }
            query.executeUpdate();

        }
    }

    @Override
    public Long getIdFromUsername(String username) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("select s.id from Teacher s where s.username =:username");
            query.setParameter("username", username);
            return (Long) query.getSingleResult();

        }
    }

    @Override
    public void paySlip(Long id, Integer semester) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("select sum(l.lessonUnit) from Lesson l " +
                    "where l.teacher.id =:id and l.semester =:semester");
            query.setParameter("id", id);
            query.setParameter("semester", semester);
            Long singleResult = (Long) query.getSingleResult();
            Teacher teacher = new Teacher();
            teacher.setId(id);
            Query query1 = session.createQuery("select t.teacherLevel from Teacher t where t.id =:id");
            query1.setParameter("id", id);
            TeacherLevel teacherLvl = (TeacherLevel) query1.getSingleResult();
            if (teacherLvl.equals(TeacherLevel.FACULTY_MEMBER)) {
                System.out.println("sum of lessonUnits : " + singleResult);
                System.out.println("salary: " + ((singleResult * 1_000_000) + 5_000_000));
            }
            if (teacherLvl.equals(TeacherLevel.TUITION)) {
                System.out.println("sum of lessonUnits : " + singleResult);
                System.out.println("salary : " + (singleResult * 1_000_000));
            }
        } catch (Exception e){
            System.err.println("id or semester is invalid");
        }
    }

    @Override
    public void deleteForeignKeys(Long teacherId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update LessonGrades lg set lg.teacher.id = null where lg.teacher.id =:teacherId");
            query.setParameter("teacherId", teacherId);
            query.executeUpdate();
            query = session.createQuery("update Lesson l set l.teacher.id = null where l.teacher.id =:teacherId");
            query.setParameter("teacherId", teacherId);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}