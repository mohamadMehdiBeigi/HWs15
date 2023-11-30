package base.repository;

import base.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<T, ID> {
    @Override
    public Object findByUser(String username, String password) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " u where u.username =:username and u.password =:password ");
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("username or password is invalid. try again");
        }
        return null;
    }

    @Override
    public T saveOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Optional<T> t = Optional.ofNullable(session.find(getEntityClass(), id));
            session.close();
            return t;
        } catch (Exception e) {
            System.err.println("your id is invalid.try a valid id");
            ;
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from " + getEntityClass().getSimpleName() + " u where u.id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("either your id is invalid or your id has referenced in other table,cant delete");

        }


    }

    @Override
    public Collection<T> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.getTransaction();
            Query<T> query = session.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass());
            return query.list();
        }
    }

    @Override
    public void changeStatus(ID id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update " + getEntityClass().getSimpleName() + " u set u.status =:status where u.id =:id");
            query.setParameter("status", false);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("your id is invalid.try a valid id");
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();

        }
    }

    public abstract Class<T> getEntityClass();

}
