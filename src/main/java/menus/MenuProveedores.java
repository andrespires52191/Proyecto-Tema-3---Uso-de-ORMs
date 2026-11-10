package menus;

import dao.ProveedorDao;
import models.Proveedor;

import java.util.List;

import static utils.EntradaDatos.preguntar;

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
                accion = Integer.parseInt(preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
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

    private Proveedor pedirDatosProveedor() {
        //Proveedor(String codigo, String nombre, String apellidos, String direccionPostal)
        String codigo = preguntar("Código del proveedor: ");
        String nombre = preguntar("Nombre del proveedor: ");
        String apellidos = preguntar("Apellidos del proveedor: ");
        String direccionPostal = preguntar("Dirección postal del proveedor: ");

        if (codigo.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || direccionPostal.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios.");
            return null;
        }

        Proveedor proveedor = new Proveedor(codigo, nombre, apellidos, direccionPostal);
        return proveedor;
    }

    private void alta() {
        Proveedor proveedor = pedirDatosProveedor();
        if (proveedor == null) {
            System.out.println("Error al introducir los datos del proveedor.");
            return;
        }

        proveedorDao.create(proveedor);
        System.out.println("Proveedor creado correctamente.");
    }

    private void baja() {
        String codigo = preguntar("Código del proveedor a dar de baja: ");
        Proveedor proveedor = proveedorDao.findByCodigo(codigo);
        if (proveedor == null) {
            System.out.println("Proveedor no encontrado");
            return;
        }

        proveedorDao.delete(proveedor);
        System.out.println("Proveedor dado de baja correctamente.");
    }

    private void modificacion() {
        String codigo = preguntar("Código del proveedor a modificar: ");
        Proveedor proveedor = proveedorDao.findByCodigo(codigo);
        if (proveedor == null) {
            System.out.println("Proveedor no encontrado");
            return;
        }

        Proveedor nuevoProveedor = pedirDatosProveedor();
        if (nuevoProveedor == null) {
            System.out.println("Error al modificar el proveedor.");
            return;
        }

        proveedorDao.update(nuevoProveedor);
        System.out.println("Proveedor modificado correctamente.");
    }

    private void consultaPorCodigo() {
        String codigo = preguntar("Código del proveedor a consultar: ");
        Proveedor proveedor = proveedorDao.findByCodigo(codigo);
        if (proveedor == null) {
            System.out.println("Proveedor no encontrado");
            return;
        }

        System.out.println("Información del proveedor:");
        System.out.println("Código: " + proveedor.getCodigo());
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Apellidos: " + proveedor.getApellidos());
        System.out.println("Dirección postal: " + proveedor.getDireccionPostal());
    }

    private void consultaPorNombre() {
        String nombre = preguntar("Nombre del proveedor a consultar: ");
        List<Proveedor> proveedores = proveedorDao.findByNombre(nombre);
        if (proveedores.isEmpty()) {
            System.out.println("No se encontraron proveedores con ese nombre.");
            return;
        }

        System.out.println("Proveedores encontrados:");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }

    private void consultaPorDireccion() {
        String direccion = preguntar("Dirección del proveedor a consultar: ");
        List<Proveedor> proveedores = proveedorDao.findByDireccion(direccion);
        if (proveedores.isEmpty()) {
            System.out.println("No se encontraron proveedores con esa dirección.");
            return;
        }

        System.out.println("Proveedores encontrados:");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }

    private void listadoCompleto() {
        List<Proveedor> proveedores = proveedorDao.findAll();
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        System.out.println("Listado completo de proveedores:");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }
}
