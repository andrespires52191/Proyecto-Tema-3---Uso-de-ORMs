package dao;

import idao.IProyectoDao;
import models.Proyecto;

public class ProyectoDao extends Dao<Proyecto, Integer> implements IProyectoDao {
    @Override
    public Proyecto find(Integer id) {
        Proyecto proyecto = (Proyecto) em.find(Proyecto.class, id);
        return proyecto;
    }
}
