package service;

import base.service.BaseService;
import entity.EducationEmployee;

public interface EducationEmployeeService extends BaseService<EducationEmployee, Long> {

    EducationEmployee paySlip(String username);
}
