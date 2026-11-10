package menus;

import dao.ProveedorDao;
import utils.EntradaDatos;

public class MenuProveedores {
    private ProveedorDao proveedorDao;

    public MenuProveedores(ProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;

        //Gestión de proveedores (altas, bajas, modificaciones, búsquedas por código, nombre y dirección).
        final String MENU = "1) Alta de proveedor\n"
                + "2) Baja de proveedor\n"
                + "3) Modificación de proveedor\n"
                + "4) Consulta de proveedor por código\n"
                + "5) Consulta de proveedores por nombre\n"
                + "6) Consulta de proveedores por dirección\n"
                + "7) Listado completo de proveedores\n"
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
                    consultaPorDireccion();
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

    private void consultaPorDireccion() {
    }

    private void listadoCompleto() {
    }
}
