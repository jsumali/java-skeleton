package com.companyname.dao;

import com.companyname.model.Entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class HibernateSqlRepository implements Repository {

    private final SessionProvider sessionProvider;

    @Autowired
    public HibernateSqlRepository(SessionProvider sessionProvider){
        this.sessionProvider = sessionProvider;
    }

    @Override
    public <T extends Entity<TKey>, TKey> T get(Class<T> c, TKey id) {
        Session s = sessionProvider.getSession();
        return (T) s.get(c, (Serializable)id);
    }

    @Override
    public <T extends Entity<TKey>, TKey> void save(Class<T> c, T entity) {
        Session session = sessionProvider.getSession();

        try {
            Transaction t = session.beginTransaction();
            session.save(entity);
            t.commit();
        } catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public <T extends Entity<TKey>, TKey> void delete(Class<T> c, T entity) {
        Session session = sessionProvider.getSession();

        try {
            Transaction t = session.beginTransaction();
            session.delete(entity);
            t.commit();
        } catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public <T extends Entity<TKey>, TKey> void update(Class<T> c, T entity) {
        Session session = sessionProvider.getSession();

        try {
            Transaction t = session.beginTransaction();
            session.update(entity);
            t.commit();
        } catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }
}
