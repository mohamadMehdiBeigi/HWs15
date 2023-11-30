package repository;

import base.repository.BaseRepository;
import entity.LessonGrades;

public interface LessonGradesRepository extends BaseRepository<LessonGrades, Long> {
    Double average (Integer semester, Long id);

    Long sum (Integer semester, Long id);

    Integer currentSemester(Long id);

    void selectCourseStudent(LessonGrades lessonGrades, Integer semester, Long id);

}
