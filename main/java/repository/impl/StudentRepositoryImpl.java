package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.StudentRepository;
import util.SessionFactoryProvider;

import java.util.Arrays;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long> implements StudentRepository {

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Long getIdFromUsername(String username) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("select s.id from Student s where s.username =:username");
            query.setParameter("username", username);
            return (Long) query.getSingleResult();

        }
    }

    @Override
    public void takenLesson(Integer semester, Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("from LessonGrades lg" +
                    " inner join Lesson l on lg.lesson.id = l.id" +
                    " where l.semester =:semester and lg.student.id =:id");
            query.setParameter("semester", semester);
            query.setParameter("id", id);
            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                System.out.println(Arrays.toString(result));

            }

        }
    }

    @Override
    public void deleteForeignKeys(Long studentId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update LessonGrades lg set lg.student.id = null where lg.student.id =:studentId");
            query.setParameter("studentId", studentId);
            query.executeUpdate();
            session.getTransaction().commit();

        }
    }
}
