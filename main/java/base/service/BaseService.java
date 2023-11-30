package base.service;

import base.model.BaseEntity;
import com.sun.xml.bind.v2.model.core.ID;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    Object findByUser (String username, String password);


    void deleteById(ID id);

    Collection<T> findAll();
    void changeStatus(ID id);

    void update(T entity);


}
