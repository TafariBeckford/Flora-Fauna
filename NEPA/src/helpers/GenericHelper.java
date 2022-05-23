/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author ochemagbegor
 * @param <T>
 */
public class GenericHelper<T> {

    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery criteriaQuery = null;

    public Boolean saveEntity(T entity, EntityManager em) {

        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            
//            em.flush();
//            em.clear();
            em.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Save Entity Error", JOptionPane.ERROR_MESSAGE);
             em.getTransaction().rollback();
        }

        return false;
    }

    public Boolean updateEntity(T entity, EntityManager em) {

        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();

            em.close();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, ex, "Update Entity Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public Boolean removeEntity(Class<T> Class, T entity, String pKey, EntityManager em) {
        try {
            em.getTransaction().begin();
            entity = em.find(Class, pKey);

            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, ex, "Remove Entity Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public Boolean removeEntity(Class<T> Class, T entity, Long pKey, EntityManager em) {
        try {
            em.getTransaction().begin();
            entity = em.find(Class, pKey);

            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Remove Entity Error " + ex);
//            JOptionPane.showMessageDialog(null, ex, "Remove Entity Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public List<T> findAll(Class<T> entity, EntityManager em) {
        
        criteriaBuilder = em.getCriteriaBuilder();

        criteriaQuery = criteriaBuilder.createQuery(entity);

        criteriaQuery.select(criteriaQuery.from(entity));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public T find(Class<T> entity, String pk, EntityManager em) {
        return em.find(entity, pk);
    }
}