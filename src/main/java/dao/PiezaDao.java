package dao;

import idao.IPiezaDao;
import models.Pieza;

public class PiezaDao extends Dao<Pieza, Integer> implements IPiezaDao {
    @Override
    public Pieza find(Integer id) {
        Pieza pieza = (Pieza) em.find(Pieza.class, id);
        return pieza;
    }
}
