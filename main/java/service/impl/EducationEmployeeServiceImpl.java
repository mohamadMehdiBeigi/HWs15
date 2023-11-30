package service.impl;

import base.service.BaseServiceImpl;
import entity.EducationEmployee;
import repository.EducationEmployeeRepository;
import service.EducationEmployeeService;

public class EducationEmployeeServiceImpl extends BaseServiceImpl<EducationEmployee, Long, EducationEmployeeRepository> implements EducationEmployeeService {

    public EducationEmployeeServiceImpl(EducationEmployeeRepository repository) {
        super(repository);
    }

    @Override
    public EducationEmployee paySlip(String username) {
        return repository.paySlip(username);
    }
}
