package com.sha.springbootmicro.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IService<P,T> {

    public Optional<T> findById(Long id);
    public T saveEntity(T entity);

    public List<Optional<T>> filterByIds(List<Long> ids);

    public void deleteObjects(List<Long> ids);

    public void deleteObject(Long id);

    public List<T> getAllObjects();

    public List<P> get_objects_dto(List<T> objects);

    public P getDto(Long id);

    public void updateObject(Map<String, Object> updates, Optional<T> optionalObject);

    public Optional<T> addAttributes(Long id, T object, Map<String,Object> attributes);

    public ServiceEnum getType();


}
