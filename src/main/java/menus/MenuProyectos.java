package menus;

import dao.ProyectoDao;
import utils.EntradaDatos;

public class MenuProyectos {
    private ProyectoDao proyectoDao;

    public MenuProyectos(ProyectoDao proyectoDao) {
        this.proyectoDao = proyectoDao;

        //Gestión de proyectos (altas, bajas, modificaciones, búsquedas por código, nombre y ciudad).
        final String MENU = "1) Alta de proyecto\n"
                + "2) Baja de proyecto\n"
                + "3) Modificación de proyecto\n"
                + "4) Consulta de proyecto por código\n"
                + "5) Consulta de proyectos por nombre\n"
                + "6) Consulta de proyectos por ciudad\n"
                + "7) Listado completo de proyectos\n"
                + "8) Volver al menú principal";


        int accion = -1;
        while (accion != 8) {
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
                    consultaPorCiudad();
                    break;
                case 7:
                    listadoCompleto();
                    break;
                case 8:
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

    private void consultaPorCiudad() {
    }

    private void listadoCompleto() {
    }
}
