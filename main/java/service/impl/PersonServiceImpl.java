package service.impl;

import base.service.BaseServiceImpl;
import entity.Person;
import repository.PersonRepository;
import service.PersonService;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository> implements PersonService {
    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
