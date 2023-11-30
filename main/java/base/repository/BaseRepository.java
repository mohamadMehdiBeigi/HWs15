package base.repository;

import base.model.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseRepository <T extends BaseEntity<ID>, ID extends Serializable>{
    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    Object findByUser (String username, String password);

    void deleteById(ID id);

    void changeStatus(ID id);

    Collection<T> findAll();

    void update(T entity);


}
