package dao;

import idao.IProveedorDao;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Proveedor;

import java.util.List;

public class ProveedorDao extends Dao<Proveedor, Integer> implements IProveedorDao {
    @Override
    public Proveedor find(Integer id) {
        Proveedor proveedor = (Proveedor) em.find(Proveedor.class, id);
        return proveedor;
    }

    public Proveedor findByCodigo(String codigo) {
        try {
            String HQL_COD = "from Proveedor as p where p.codigo = :codigo";
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query1 = em.createQuery(HQL_COD);
            query1.setParameter("codigo", codigo);
            entityTransaction.commit();
            Proveedor proveedor = (Proveedor) query1.getSingleResult();
            return proveedor;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Proveedor> findByNombre(String nombre) {
        String HQL_COD = "from Proveedor as p where p.nombre = :nombre";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proveedor> query1 = em.createQuery(HQL_COD, Proveedor.class);
        query1.setParameter("nombre", nombre);
        entityTransaction.commit();
        List<Proveedor> proveedores = query1.getResultList();
        return proveedores;
    }

    public List<Proveedor> findByDireccion(String direccion) {
        String HQL_COD = "from Proveedor as p where p.direccionPostal = :direccion";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proveedor> query1 = em.createQuery(HQL_COD, Proveedor.class);
        query1.setParameter("direccion", direccion);
        entityTransaction.commit();
        List<Proveedor> proveedores = query1.getResultList();
        return proveedores;
    }

    public List<Proveedor> findAll() {
        String HQL_COD = "from Proveedor";
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        TypedQuery<Proveedor> query1 = em.createQuery(HQL_COD, Proveedor.class);
        entityTransaction.commit();
        List<Proveedor> proveedores = query1.getResultList();
        return proveedores;
    }
}
