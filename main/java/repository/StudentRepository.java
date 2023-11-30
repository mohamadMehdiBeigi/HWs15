package repository;

import base.repository.BaseRepository;
import entity.Student;

public interface StudentRepository extends BaseRepository<Student, Long> {
    Long getIdFromUsername(String username);
    void takenLesson(Integer semester, Long id);

    void deleteForeignKeys(Long studentId);
}
