package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.EducationEmployee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.EducationEmployeeRepository;
import util.SessionFactoryProvider;

public class EducationEmployeeRepositoryImpl extends BaseRepositoryImpl<EducationEmployee, Long> implements EducationEmployeeRepository {

    @Override
    public Class<EducationEmployee> getEntityClass() {
        return EducationEmployee.class;
    }

    @Override
    public EducationEmployee paySlip(String username) {
        try(Session session = SessionFactoryProvider.getSessionFactory().openSession()){
            session.getTransaction();
            Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " u where u.username =:username ");
            query.setParameter("username", username);
            return (EducationEmployee) query.getSingleResult();

        }
    }
}
