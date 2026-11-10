import dao.GestionDao;
import dao.PiezaDao;
import dao.ProveedorDao;
import dao.ProyectoDao;
import menus.MenuPiezas;
import menus.MenuProveedores;
import menus.MenuProyectos;
import menus.MenuSuministros;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static utils.EntradaDatos.preguntar;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Ocultar mensajes de hibernate, para que no interfieran con los menus.
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.OFF);
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.OFF);
        }

        GestionDao gestionDao;
        PiezaDao piezaDao;
        ProveedorDao proveedorDao;
        ProyectoDao proyectoDao;
        try {
            gestionDao = new GestionDao();
            piezaDao = new PiezaDao();
            proveedorDao = new ProveedorDao();
            proyectoDao = new ProyectoDao();
        } catch (Exception e) {
            System.out.println("La base de datos no está disponible.");
            System.out.println("Compruebe el problema y reinicie la aplicación.");
            //e.printStackTrace();
            return;
        }

        final String MENU = "1) Gestiòn de piezas\n"
                + "2) Gestiòn de proyectos\n"
                + "3) Gestiòn de proveedores\n"
                + "4) Gestiòn de suministros\n"
                + "5) Ayuda\n"
                + "6) Salir";

        int accion = -1;
        while (accion != 6) {
            try {
                accion = Integer.parseInt(preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    new MenuPiezas(piezaDao);
                    break;
                case 2:
                    new MenuProyectos(proyectoDao);
                    break;
                case 3:
                    new MenuProveedores(proveedorDao);
                    break;
                case 4:
                    new MenuSuministros(gestionDao, proveedorDao, piezaDao, proyectoDao);
                    break;
                case 5:
                    System.out.println("Ayuda:\n"
                            + "Gestión de proveedores: altas, bajas, modificaciones, búsquedas por código, nombre y dirección.\n"
                            + "Gestión de piezas: altas, bajas, modificaciones, búsquedas por código y nombre.\n"
                            + "Gestión de proyectos: altas, bajas, modificaciones, búsquedas por código, nombre y ciudad.\n"
                            + "Gestión de suministros: altas, bajas, modificaciones y consultas de los suministros,\n"
                            + "    permitiendo relacionar proveedores, piezas y proyectos e indicar la cantidad suministrada.\n"
                            + "    No se podrán eliminar proveedores, piezas o proyectos que estén asociados a suministros existentes.");
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }
}
