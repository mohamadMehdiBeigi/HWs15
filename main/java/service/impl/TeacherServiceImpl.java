package service.impl;

import base.service.BaseServiceImpl;
import entity.Teacher;
import repository.TeacherRepository;
import service.TeacherService;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long, TeacherRepository> implements TeacherService {
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public void submitGrades(Long lessonId,Long teacherId,Long studentId, Double newGrade) {
        repository.submitGrades( lessonId, teacherId, studentId, newGrade);
    }

    @Override
    public Long getIdFromUsername(String username) {
        return repository.getIdFromUsername(username);
    }

    @Override
    public void paySlip(Long id, Integer semester) {
         repository.paySlip(id, semester);
    }

    @Override
    public void deleteForeignKeys(Long teacherId) {
        repository.deleteForeignKeys(teacherId);
    }
}
