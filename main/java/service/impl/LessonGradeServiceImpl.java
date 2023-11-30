package service.impl;

import base.service.BaseServiceImpl;
import entity.LessonGrades;
import repository.LessonGradesRepository;
import service.LessonGradeService;

public class LessonGradeServiceImpl extends BaseServiceImpl<LessonGrades, Long, LessonGradesRepository> implements LessonGradeService {
    public LessonGradeServiceImpl(LessonGradesRepository repository) {
        super(repository);
    }

    @Override
    public Double average(Integer semester, Long id) {
        return repository.average(semester, id);
    }

    @Override
    public Long sum(Integer semester, Long id) {
        return repository.sum(semester, id);
    }

    @Override
    public Integer currentSemester(Long id) {
        return repository.currentSemester(id);
    }

    @Override
    public void selectCourseStudent(LessonGrades lessonGrades, Integer semester, Long id) {
        repository.selectCourseStudent(lessonGrades, semester, id);
    }

}
