package menus;

import dao.PiezaDao;
import utils.EntradaDatos;

public class MenuPiezas {
    private PiezaDao piezaDao;

    public MenuPiezas(PiezaDao piezaDao) {
        this.piezaDao = piezaDao;

        //Gestión de piezas (altas, bajas, modificaciones, búsquedas por código y nombre).
        final String MENU = "1) Alta de pieza\n"
                + "2) Baja de pieza\n"
                + "3) Modificación de pieza\n"
                + "4) Consulta de pieza por código\n"
                + "5) Consulta de piezas por nombre\n"
                + "6) Listado completo de piezas\n"
                + "7) Volver al menú principal";


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
