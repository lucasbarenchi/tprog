package com.main.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;


public abstract class AbstractDAO<T, ID> {

    private static EntityManager entityManager = null;
    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "EntityPersister";
    private Class<T> clazz;
    
    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
    protected static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = emFactoryObj.createEntityManager();
            return entityManager;
        }
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
            return entityManager;
        }
        return entityManager;
    }
    
    public static void initSchema() {
        getEntityManager();
    }
    
    AbstractDAO(Class<T> typeParameterClass) {
        this.clazz = typeParameterClass;
    }

    @Transactional
    public void save(T entity){
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }

    @Transactional
    public void merge(T entity){
        getEntityManager().merge(entity);
    }

    @Transactional
    public void save(List<T> entities){
        entities.forEach(e -> {
            getEntityManager().persist(e);
        });
    }
    @Transactional
    public void delete(T entity){
        getEntityManager().remove(entity);
    }

    @Transactional
    public void deleteById(ID id){
        T entity = getEntityManager().find(this.clazz, id);
        getEntityManager().remove(entity);
    }
    
    @Transactional
    public T load(ID id){
        return getEntityManager().find(this.clazz, id);
    }
}
