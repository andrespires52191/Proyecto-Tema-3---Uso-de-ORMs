package dao;

import idao.IGestionDao;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Gestion;

import java.util.List;

public class GestionDao extends Dao<Gestion, Integer> implements IGestionDao {
    @Override
    public Gestion find(Integer id) {
        Gestion gestion = (Gestion) em.find(Gestion.class, id);
        return gestion;
    }

    public Gestion findById(int id) {
        try {
            String HQL_COD = "from Gestion as g where g.id = :id";
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query1 = em.createQuery(HQL_COD);
            query1.setParameter("id", id);
            entityTransaction.commit();
            Gestion gestion = (Gestion) query1.getSingleResult();
            return gestion;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Gestion> findAll() {
        String HQL_COD = "from Gestion";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Gestion> query1 = em.createQuery(HQL_COD, Gestion.class);
        entityTransaction.commit();
        List<Gestion> gestiones = query1.getResultList();
        return gestiones;
    }
}
