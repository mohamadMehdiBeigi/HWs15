package service;

import base.service.BaseService;
import entity.Teacher;

public interface TeacherService extends BaseService<Teacher, Long> {

    void submitGrades(Long lessonId,Long teacherId,Long studentId, Double newGrade);

    Long getIdFromUsername(String username);

    void paySlip(Long id, Integer semester);

    void deleteForeignKeys(Long teacherId);


}
