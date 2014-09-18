package com.companyname.dao;
import com.companyname.model.Entity;

public interface Repository {
    <T extends Entity<TKey>, TKey> T get(Class<T> c, TKey id);
    <T extends Entity<TKey>, TKey> void save(Class<T> c, T entity);
    <T extends Entity<TKey>, TKey> void delete(Class<T> c, T entity);
    <T extends Entity<TKey>, TKey> void update(Class<T> c, T entity);
}
