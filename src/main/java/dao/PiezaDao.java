package dao;

import idao.IPiezaDao;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Pieza;

import java.util.List;

public class PiezaDao extends Dao<Pieza, Integer> implements IPiezaDao {
    @Override
    public Pieza find(Integer id) {
        Pieza pieza = (Pieza) em.find(Pieza.class, id);
        return pieza;
    }

    public Pieza findByCodigo(String codigo) {
        try {
            String HQL_COD = "from Pieza as p where p.codigo = :codigo";
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query1 = em.createQuery(HQL_COD);
            query1.setParameter("codigo", codigo);
            entityTransaction.commit();
            Pieza pieza = (Pieza) query1.getSingleResult();
            return pieza;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Pieza> findByNombre(String nombre) {
        String HQL_COD = "from Pieza as p where p.nombre = :nombre";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Pieza> query1 = em.createQuery(HQL_COD, Pieza.class);
        query1.setParameter("nombre", nombre);
        entityTransaction.commit();
        List<Pieza> piezas = query1.getResultList();
        return piezas;
    }

    public List<Pieza> findAll() {
        String HQL_COD = "from Pieza";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Pieza> query1 = em.createQuery(HQL_COD, Pieza.class);
        entityTransaction.commit();
        List<Pieza> piezas = query1.getResultList();
        return piezas;
    }
}
