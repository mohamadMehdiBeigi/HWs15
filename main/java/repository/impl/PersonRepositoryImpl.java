package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.Person;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person, Long> implements PersonRepository {
    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }
}
