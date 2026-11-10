package menus;

import dao.GestionDao;
import dao.PiezaDao;
import dao.ProveedorDao;
import dao.ProyectoDao;
import utils.EntradaDatos;

public class MenuSuministros {
    private GestionDao gestionDao;
    private ProveedorDao proveedorDao;
    private PiezaDao piezaDao;
    private ProyectoDao proyectoDao;

    public MenuSuministros(GestionDao gestionDao, ProveedorDao proveedorDao, PiezaDao piezaDao, ProyectoDao proyectoDao) {
        this.gestionDao = gestionDao;
        this.proveedorDao = proveedorDao;
        this.piezaDao = piezaDao;
        this.proyectoDao = proyectoDao;

        //Gestión de suministros: altas, bajas, modificaciones y consultas de los suministros,
        //permitiendo relacionar proveedores, piezas y proyectos e indicar la cantidad suministrada.
        //En tod momento se deberá respetar la integridad referencial, impidiendo la eliminación de
        //proveedores, piezas o proyectos que estén asociados a suministros existentes.
        final String MENU = "1) Alta de suministro\n"
                + "2) Baja de suministro\n"
                + "3) Modificación de suministro\n"
                + "4) Consulta de suministro por código\n"
                + "5) Listado completo de suministros\n"
                + "6) Volver al menú principal";


        int accion = -1;
        while (accion != 7) {
            try {
                accion = Integer.parseInt(EntradaDatos.preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    alta();
                    break;
                case 2:
                    baja();
                    break;
                case 3:
                    modificacion();
                    break;
                case 4:
                    consultaPorCodigo();
                    break;
                case 5:
                    consultaPorNombre();
                    break;
                case 6:
                    listadoCompleto();
                    break;
                case 7:
                    // Volver al menú principal
                    break;
                default:
                    System.out.println("Acción no válida");
            }
        }
    }

    private void alta() {
    }

    private void baja() {
    }

    private void modificacion() {
    }

    private void consultaPorCodigo() {
    }

    private void consultaPorNombre() {
    }

    private void listadoCompleto() {
    }
}
