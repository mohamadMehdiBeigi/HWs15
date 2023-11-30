package base.service;

import base.model.BaseEntity;
import base.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T, ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T saveOrUpdate(T entity) {
        return repository.saveOrUpdate(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Object findByUser(String username, String password) {
        try {
            return repository.findByUser(username, password);

        } catch (RuntimeException e){
            e.printStackTrace();
            System.out.println("user not find!!!");
        }
        return null;
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void changeStatus(ID id) {
        repository.changeStatus(id);
    }

    @Override
    public void update(T entity) {
        repository.update(entity);
    }


}
