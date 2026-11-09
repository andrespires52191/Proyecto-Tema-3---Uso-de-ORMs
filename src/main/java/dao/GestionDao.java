package dao;

import idao.IGestionDao;
import models.Gestion;

public class GestionDao extends Dao<Gestion, Integer> implements IGestionDao {
    @Override
    public Gestion find(Integer id) {
        Gestion gestion = (Gestion) em.find(Gestion.class, id);
        return gestion;
    }
}
