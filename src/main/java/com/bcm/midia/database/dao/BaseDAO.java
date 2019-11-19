package com.bcm.midia.database.dao;

public interface BaseDAO<T> {
    public T save(T entity);
    public T update(T entity);
    public void delete(T entity);
    public void refresh(T entity);
//    public void flush();
//    public List<T> findAll();
//    public List<T> findAll(int first, int max);
//    public T findById(Long id);
//    public T find(Class<T> classe, Long id);
//    public void detach(T entity);
//    boolean contains(T entity);
 

}
