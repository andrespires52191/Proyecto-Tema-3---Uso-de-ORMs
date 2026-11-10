package dao;

import idao.IProyectoDao;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Proyecto;

import java.util.List;

public class ProyectoDao extends Dao<Proyecto, Integer> implements IProyectoDao {
    @Override
    public Proyecto find(Integer id) {
        Proyecto proyecto = (Proyecto) em.find(Proyecto.class, id);
        return proyecto;
    }

    public Proyecto findByCodigo(String codigo) {
        try {
            String HQL_COD = "from Proyecto as p where p.codigo = :codigo";
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query1 = em.createQuery(HQL_COD);
            query1.setParameter("codigo", codigo);
            entityTransaction.commit();
            Proyecto proyecto = (Proyecto) query1.getSingleResult();
            return proyecto;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Proyecto> findByNombre(String nombre) {
        String HQL_COD = "from Proyecto as p where p.nombre = :nombre";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proyecto> query1 = em.createQuery(HQL_COD, Proyecto.class);
        query1.setParameter("nombre", nombre);
        entityTransaction.commit();
        List<Proyecto> proyectos = query1.getResultList();
        return proyectos;
    }

    public List<Proyecto> findByCiudad(String ciudad) {
        String HQL_COD = "from Proyecto as p where p.ciudad = :ciudad";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proyecto> query1 = em.createQuery(HQL_COD, Proyecto.class);
        query1.setParameter("ciudad", ciudad);
        entityTransaction.commit();
        List<Proyecto> proyectos = query1.getResultList();
        return proyectos;
    }

    public List<Proyecto> findAll() {
        String HQL_COD = "from Proyecto";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proyecto> query1 = em.createQuery(HQL_COD, Proyecto.class);
        entityTransaction.commit();
        List<Proyecto> proyectos = query1.getResultList();
        return proyectos;
    }
}
