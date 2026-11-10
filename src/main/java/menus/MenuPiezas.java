package menus;

import dao.PiezaDao;
import models.Pieza;
import utils.EntradaDatos;

import java.util.List;

import static utils.EntradaDatos.preguntar;

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
            System.out.println();
        }
    }

    private Pieza pedirDatosPieza() {
        //public Pieza(String codigo, String nombre, String precio, String descripcion)
        String codigo = preguntar("Código de la pieza: ");
        if (codigo.isEmpty()) {
            System.out.println("Error: El código es obligatorio.");
            return null;
        }

        String nombre = preguntar("Nombre de la pieza: ");
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre es obligatorio.");
            return null;
        }

        double precio;
        try {
            precio = Double.parseDouble(EntradaDatos.preguntar("Precio de la pieza (€): "));
        } catch (NumberFormatException e) {
            System.out.println("Error: Precio debe ser un número válido.");
            return null;
        }

        String descripcion = preguntar("Descripción de la pieza: ");

        Pieza pieza = new Pieza(codigo, nombre, precio, descripcion);
        return pieza;
    }

    private void alta() {
        Pieza pieza = pedirDatosPieza();
        if (pieza == null) {
            System.out.println("Error al introducir los datos de la pieza.");
            return;
        }
        if (piezaDao.findByCodigo(pieza.getCodigo()) != null) {
            System.out.println("Error: Ya existe una pieza con ese código.");
            return;
        }

        piezaDao.create(pieza);
        System.out.println("Pieza creada correctamente.");
    }

    private void baja() {
        String codigo = preguntar("Código de la pieza a dar de baja: ");
        Pieza pieza = piezaDao.findByCodigo(codigo);
        if (pieza == null) {
            System.out.println("Pieza no encontrada");
            return;
        }

        piezaDao.delete(pieza);
        System.out.println("Pieza dada de baja correctamente.");
    }

    private void modificacion() {
        String codigo = preguntar("Código de la pieza a modificar: ");
        Pieza pieza = piezaDao.findByCodigo(codigo);
        if (pieza == null) {
            System.out.println("Pieza no encontrada");
            return;
        }

        Pieza nuevaPieza = pedirDatosPieza();
        if (nuevaPieza == null) {
            System.out.println("Error al modificar la pieza.");
            return;
        }
        if (piezaDao.findByCodigo(nuevaPieza.getCodigo()) != null) {
            System.out.println("Error: Ya existe una pieza con ese código.");
            return;
        }
        pieza.setCodigo(nuevaPieza.getCodigo());
        pieza.setNombre(nuevaPieza.getNombre());
        pieza.setPrecio(nuevaPieza.getPrecio());
        pieza.setDescripcion(nuevaPieza.getDescripcion());

        piezaDao.update(pieza);
        System.out.println("Pieza modificada correctamente.");
    }

    private void consultaPorCodigo() {
        String codigo = preguntar("Código de la pieza a consultar: ");
        Pieza pieza = piezaDao.findByCodigo(codigo);
        if (pieza == null) {
            System.out.println("Pieza no encontrada");
            return;
        }

        System.out.println("Información de la pieza:");
        System.out.println("Código: " + pieza.getCodigo());
        System.out.println("Nombre: " + pieza.getNombre());
        System.out.println("Precio: " + pieza.getPrecio());
        System.out.println("Descripción: " + pieza.getDescripcion());
    }

    private void consultaPorNombre() {
        String nombre = preguntar("Nombre de la pieza a consultar: ");
        List<Pieza> piezas = piezaDao.findByNombre(nombre);
        if (piezas.isEmpty()) {
            System.out.println("No se encontraron piezas con ese nombre.");
            return;
        }

        System.out.println("Piezas encontradas:");
        for (Pieza p : piezas) {
            System.out.println(p);
        }
    }

    private void listadoCompleto() {
        List<Pieza> piezas = piezaDao.findAll();
        if (piezas.isEmpty()) {
            System.out.println("No hay piezas registradas.");
            return;
        }

        System.out.println("Listado completo de piezas:");
        for (Pieza p : piezas) {
            System.out.println(p);
        }
    }
}
