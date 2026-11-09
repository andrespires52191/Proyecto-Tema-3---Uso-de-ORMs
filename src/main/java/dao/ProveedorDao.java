package dao;

import idao.IProveedorDao;
import models.Proveedor;

public class ProveedorDao extends Dao<Proveedor, Integer> implements IProveedorDao {
    @Override
    public Proveedor find(Integer id) {
        Proveedor proveedor = (Proveedor) em.find(Proveedor.class, id);
        return proveedor;
    }
}
