package repository;

import base.repository.BaseRepository;
import entity.EducationEmployee;

public interface EducationEmployeeRepository extends BaseRepository<EducationEmployee, Long> {
    EducationEmployee paySlip(String username);

}
