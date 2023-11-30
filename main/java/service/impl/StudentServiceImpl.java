package service.impl;

import base.service.BaseServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public Long getIdFromUsername(String username) {
        return repository.getIdFromUsername(username);
    }

    @Override
    public void takenLesson(Integer semester, Long id) {
        repository.takenLesson(semester, id);
    }

    @Override
    public void deleteForeignKeys(Long studentId) {
        repository.deleteForeignKeys(studentId);
    }
}
