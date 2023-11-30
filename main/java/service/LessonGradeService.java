package service;

import base.service.BaseService;
import entity.LessonGrades;

public interface LessonGradeService extends BaseService<LessonGrades, Long> {

    Double average (Integer semester, Long id);

    Long sum (Integer semester, Long id);

    Integer currentSemester(Long id);

    void selectCourseStudent(LessonGrades lessonGrades, Integer semester, Long id);

    }
