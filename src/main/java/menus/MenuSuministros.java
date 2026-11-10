package menus;

import dao.GestionDao;
import dao.PiezaDao;
import dao.ProveedorDao;
import dao.ProyectoDao;
import models.Gestion;
import models.Pieza;
import models.Proveedor;
import models.Proyecto;

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
                + "4) Consulta de suministro por id\n"
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
                    consultaPorId();
                    break;
                case 5:
                    listadoCompleto();
                    break;
                case 6:
                    // Volver al menú principal
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }

    private Gestion pedirDatosSuministro() {
        //Gestion(LocalDate fecha, int cantidad, Proveedor proveedor, Pieza pieza, Proyecto proyecto)
        String codigoPieza = preguntar("Código de la pieza: ");
        Pieza pieza = piezaDao.findByCodigo(codigoPieza);
        if (pieza == null) {
            System.out.println("Error: No existe una pieza con ese código.");
            return null;
        }

        String codigoProyecto = preguntar("Código del proyecto: ");
        Proyecto proyecto = proyectoDao.findByCodigo(codigoProyecto);
        if (proyecto == null) {
            System.out.println("Error: No existe un proyecto con ese código.");
            return null;
        }

        String codigoProveedor = preguntar("Código del proveedor: ");
        Proveedor proveedor = proveedorDao.findByCodigo(codigoProveedor);
        if (proveedor == null) {
            System.out.println("Error: No existe un proveedor con ese código.");
            return null;
        }

        int cantidad = Integer.parseInt(preguntar("Cantidad suministrada: "));
        String fecha = preguntar("Fecha del suministro (YYYY-MM-DD): ");

        Gestion gestion = new Gestion(fecha, cantidad, proveedor, pieza, proyecto);
        return gestion;
    }

    private void alta() {
        Gestion suministro = pedirDatosSuministro();
        if (suministro == null) {
            System.out.println("Error al introducir los datos del suministro.");
            return;
        }
        if (gestionDao.findById(suministro.getId()) != null) {
            System.out.println("Error: Ya existe una suministro con ese código.");
            return;
        }

        System.out.println("Suministro creado correctamente.");
        gestionDao.create(suministro);
    }

    private void baja() {
        int id;
        try {
            id = Integer.parseInt(preguntar("Id del suministro a dar de baja: "));
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
            return;
        }

        Gestion suministro = gestionDao.findById(id);
        if (suministro == null) {
            System.out.println("Suministro no encontrado");
            return;
        }

        gestionDao.delete(suministro);
        System.out.println("Suministro dado de baja correctamente.");
    }

    private void modificacion() {
        int id;
        try {
            id = Integer.parseInt(preguntar("Id del suministro a modificar: "));
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
            return;
        }

        Gestion suministro = gestionDao.findById(id);
        if (suministro == null) {
            System.out.println("Suministro no encontrado");
            return;
        }

        Gestion nuevoSuministro = pedirDatosSuministro();
        if (nuevoSuministro == null) {
            System.out.println("Error al introducir los datos del suministro.");
            return;
        }
        if (gestionDao.findById(nuevoSuministro.getId()) != null) {
            System.out.println("Error: Ya existe un suministro con ese código.");
            return;
        }
        suministro.setPieza(nuevoSuministro.getPieza());
        suministro.setProyecto(nuevoSuministro.getProyecto());
        suministro.setProveedor(nuevoSuministro.getProveedor());
        suministro.setCantidad(nuevoSuministro.getCantidad());
        suministro.setFecha(nuevoSuministro.getFecha());

        gestionDao.update(suministro);
        System.out.println("Suministro modificado correctamente.");
    }

    private void consultaPorId() {
        int id;
        try {
            id = Integer.parseInt(preguntar("Id del suministro a consultar: "));
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
            return;
        }

        Gestion suministro = gestionDao.findById(id);
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
