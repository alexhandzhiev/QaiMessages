package app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;
import java.util.List;

/**
 * Extend to get access to CRUD operations for a concrete entity
 * @param <T>
 */
public abstract class AbstractHibernateDAO<T extends Serializable> {

    @PersistenceUnit
    EntityManagerFactory emf;

    private Class<T> clazz;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public T create(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return emf.unwrap(SessionFactory.class).openSession();
    }
}