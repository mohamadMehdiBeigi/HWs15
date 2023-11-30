package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.LessonGrades;
import entity.enums.LessonGradeStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.LessonGradesRepository;
import util.SessionFactoryProvider;

public class LessonGradesRepositoryImpl extends BaseRepositoryImpl<LessonGrades, Long> implements LessonGradesRepository {
    @Override
    public Class<LessonGrades> getEntityClass() {
        return LessonGrades.class;
    }


    @Override
    public Integer currentSemester(Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("select max(l.semester) from Lesson l join LessonGrades lg on lg.id = lg.lesson.id where lg.student.id =:id");
            query.setParameter("id", id);
            Integer singleResult = (Integer) query.getSingleResult();
            if (singleResult == null) {
                return 1;
            } else
                return singleResult;
        }
    }

    @Override
    public Double average(Integer semester, Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query query = session.createQuery("select avg(lg.grade) from LessonGrades lg join Lesson l on lg.id = lg.lesson.id where l.semester =:semester and lg.student.id =:id");
            query.setParameter("id", id);
            query.setParameter("semester", semester);
            Double singleResult = (Double) query.getSingleResult();
            if (singleResult == null) {
                return 1.0;
            } else {
                return (Double) query.getSingleResult();
            }
        }
    }

    @Override
    public Long sum(Integer semester, Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();

            Query query = session.createQuery("select sum(l.lessonUnit) from Lesson l inner join l.lessonGrades lg on l.id = lg.lesson.id where l.semester =:semester and lg.student.id =:id");
            query.setParameter("semester", semester);
            query.setParameter("id", id);

            Long singleResult = (Long) query.getSingleResult();
            if (singleResult == null) {
                return 1L;
            } else {
                return (Long) query.getSingleResult();
            }
        }
    }

    @Override
    public void selectCourseStudent(LessonGrades lessonGrades, Integer semester, Long id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Long> query = session.createQuery("select count(lg.lessonGradeStatus) as count from LessonGrades lg where lg.student.id =:id and lg.lesson.id =: lessonId and lg.lessonGradeStatus =:lessonGradeStatus");
            query.setParameter("lessonGradeStatus", LessonGradeStatus.PASS);
            query.setParameter("id", id);
            query.setParameter("lessonId", lessonGrades.getLesson().getId());
            Long singleResult = query.getSingleResult();
            if (singleResult > 0) {
                System.out.println("you have passed the current lesson");
            } else {
                if (average(semester, id) > 18) {
                    if (sum(semester, id) <= 24) {
                        session.save(lessonGrades);
                        session.getTransaction().commit();
                    }
                } else if (average(semester, id) <= 18) {
                    if (sum(semester, id) <= 20) {
                        session.save(lessonGrades);
                        session.getTransaction().commit();
                    }
                }

            }
        } catch (Exception e){
            System.out.println("The specification is not correct");
        }
    }

}
