package menus;

import dao.GestionDao;
import dao.PiezaDao;
import dao.ProveedorDao;
import dao.ProyectoDao;
import models.Gestion;

import java.util.List;

import static utils.EntradaDatos.preguntar;

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

    private Gestion pedirDatosSuministro() {
        //Gestion(LocalDate fecha, int cantidad, Proveedor proveedor, Pieza pieza, Proyecto proyecto)
        String fecha = preguntar("Fecha del suministro (YYYY-MM-DD): ");
        int cantidad = Integer.parseInt(preguntar("Cantidad suministrada: "));
        String codigoProveedor = preguntar("Código del proveedor: ");
        String codigoPieza = preguntar("Código de la pieza: ");
        String codigoProyecto = preguntar("Código del proyecto: ");
        return null;
    }

    private void alta() {
        Gestion suministro = pedirDatosSuministro();
        if (suministro == null) {
            System.out.println("Error al introducir los datos del suministro.");
            return;
        }

        System.out.println("Suministro creado correctamente.");
        gestionDao.create(suministro);
    }

    private void baja() {
        String codigo = preguntar("Código del suministro a dar de baja: ");
        Gestion suministro = gestionDao.findByCodigo(codigo);
        if (suministro == null) {
            System.out.println("Suministro no encontrado");
            return;
        }

        gestionDao.delete(suministro);
        System.out.println("Suministro dado de baja correctamente.");
    }

    private void modificacion() {
        String codigo = preguntar("Código del suministro a modificar: ");
        Gestion suministro = gestionDao.findByCodigo(codigo);
        if (suministro == null) {
            System.out.println("Suministro no encontrado");
            return;
        }

        Gestion nuevoSuministro = pedirDatosSuministro();
        if (nuevoSuministro == null) {
            System.out.println("Error al introducir los datos del suministro.");
            return;
        }

        gestionDao.update(nuevoSuministro);
        System.out.println("Suministro modificado correctamente.");
    }

    private void consultaPorCodigo() {
        String codigo = preguntar("Código del suministro a consultar: ");
        Gestion suministro = gestionDao.findByCodigo(codigo);
        if (suministro == null) {
            System.out.println("Suministro no encontrado");
            return;
        }

        System.out.println("Información del suministro:");
        System.out.println("Id: " + suministro.getId());
        System.out.println("Fecha: " + suministro.getFecha());
        System.out.println("Cantidad: " + suministro.getCantidad());
        System.out.println("Proveedor: " + suministro.getProveedor().getNombre());
        System.out.println("Pieza: " + suministro.getPieza().getNombre());
        System.out.println("Proyecto: " + suministro.getProyecto().getNombre());
    }

    private void consultaPorNombre() {
        String nombre = preguntar("Nombre del suministro a consultar: ");
        List<Gestion> suministros = gestionDao.findByNombre(nombre);
        if (suministros.isEmpty()) {
            System.out.println("No se encontraron suministros con ese nombre.");
            return;
        }

        System.out.println("Información de los suministros:");
        for (Gestion suministro : suministros) {
            System.out.println(suministro);
        }
    }

    private void listadoCompleto() {
        List<Gestion> suministros = gestionDao.findAll();
        if (suministros.isEmpty()) {
            System.out.println("No hay suministros registrados.");
            return;
        }

        System.out.println("Listado completo de suministros:");
        for (Gestion suministro : suministros) {
            System.out.println(suministro);
        }
    }
}
