package service;

import base.service.BaseService;
import entity.Student;

public interface StudentService extends BaseService<Student, Long> {
    Long getIdFromUsername(String username);
    void takenLesson(Integer semester, Long id);

    void deleteForeignKeys(Long studentId);


}
