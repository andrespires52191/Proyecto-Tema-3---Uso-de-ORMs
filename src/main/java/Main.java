import dao.GestionDao;
import dao.PiezaDao;
import dao.ProveedorDao;
import dao.ProyectoDao;
import models.Gestion;
import models.Pieza;
import models.Proveedor;
import models.Proyecto;

import java.time.LocalDate;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Gestión de proveedores (altas, bajas, modificaciones, búsquedas por código, nombre y dirección).
        //Gestión de piezas (altas, bajas, modificaciones, búsquedas por código y nombre).
        //Gestión de proyectos (altas, bajas, modificaciones, búsquedas por código, nombre y ciudad).
        //Gestión de suministros: altas, bajas, modificaciones y consultas de los suministros,
        //permitiendo relacionar proveedores, piezas y proyectos e indicar la cantidad suministrada.
        //En tod momento se deberá respetar la integridad referencial, impidiendo la eliminación de
        //proveedores, piezas o proyectos que estén asociados a suministros existentes.

        //Desde este menú se podrán realizar todas las operaciones sobre la base de datos:
        //gestión de proveedores, gestión de piezas, gestión de proyectos, gestión de suministros y
        //ayuda.

        // Ocultar mensajes de hibernate, para que no interfieran con los menus.
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.OFF);
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.OFF);
        }

        GestionDao gestionDao = new GestionDao();
        PiezaDao piezaDao = new PiezaDao();
        ProveedorDao proveedorDao = new ProveedorDao();
        ProyectoDao proyectoDao = new ProyectoDao();

        Pieza pieza = new Pieza("PZ001", "Pieza 1", "10.0", "Descripción de la pieza 1");
        piezaDao.create(pieza);

        Proveedor proveedor = new Proveedor("PV001", "Juan", "Pérez", "Calle Falsa 123");
        proveedorDao.create(proveedor);

        Proyecto proyecto = new Proyecto("PJ001", "Proyecto 1", "Madrid");
        proyectoDao.create(proyecto);

        Gestion gestion = new Gestion(LocalDate.now(), 5, proveedor, pieza, proyecto);
        gestionDao.create(gestion);

        try {
            proveedorDao.delete(proveedor);
        } catch (Exception e) {
            System.out.println("No se puede eliminar el proveedor porque está asociado a una gestión.");
        }

    }
}
