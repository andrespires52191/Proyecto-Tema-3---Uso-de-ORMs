package menus;

import dao.ProyectoDao;
import models.Proyecto;

import java.util.List;

import static utils.EntradaDatos.preguntar;

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
            System.out.println();
        }
    }

    private Proyecto pedirDatosProyecto() {
        String codigo = preguntar("Código del proyecto: ");
        String nombre = preguntar("Nombre del proyecto: ");
        String ciudad = preguntar("Ciudad del proyecto: ");

        if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty()) {
            System.out.println("Todos los campos son obligatorios.");
            return null;
        }

        return new Proyecto(codigo, nombre, ciudad);
    }

    private void alta() {
        Proyecto proyecto = pedirDatosProyecto();
        if (proyecto == null) {
            System.out.println("Error al introducir los datos del proyecto.");
            return;
        }
        if (proyectoDao.findByCodigo(proyecto.getCodigo()) != null) {
            System.out.println("Error: Ya existe un proyecto con ese código.");
            return;
        }

        System.out.println("Proyecto creado correctamente.");
        proyectoDao.create(proyecto);
    }

    private void baja() {
        String codigo = preguntar("Código del proyecto a dar de baja: ");
        Proyecto proyecto = proyectoDao.findByCodigo(codigo);
        if (proyecto == null) {
            System.out.println("Proyecto no encontrado");
            return;
        }

        proyectoDao.delete(proyecto);
        System.out.println("Proyecto dado de baja correctamente.");
    }

    private void modificacion() {
        String codigo = preguntar("Código del proyecto a modificar: ");
        Proyecto proyecto = proyectoDao.findByCodigo(codigo);
        if (proyecto == null) {
            System.out.println("Proyecto no encontrado");
            return;
        }

        Proyecto nuevoProyecto = pedirDatosProyecto();
        if (nuevoProyecto == null) {
            System.out.println("Error al introducir los datos del proyecto.");
            return;
        }
        if (proyectoDao.findByCodigo(nuevoProyecto.getCodigo()) != null) {
            System.out.println("Error: Ya existe un proyecto con ese código.");
            return;
        }
        proyecto.setCodigo(nuevoProyecto.getCodigo());
        proyecto.setNombre(nuevoProyecto.getNombre());
        proyecto.setCiudad(nuevoProyecto.getCiudad());

        proyectoDao.update(proyecto);
        System.out.println("Proyecto modificado correctamente.");
    }

    private void consultaPorCodigo() {
        String codigo = preguntar("Código del proyecto a consultar: ");
        Proyecto proyecto = proyectoDao.findByCodigo(codigo);
        if (proyecto == null) {
            System.out.println("Proyecto no encontrado");
            return;
        }

        System.out.println("Información del proyecto:");
        System.out.println("Código: " + proyecto.getCodigo());
        System.out.println("Nombre: " + proyecto.getNombre());
        System.out.println("Ciudad: " + proyecto.getCiudad());
    }

    private void consultaPorNombre() {
        String nombre = preguntar("Nombre del proyecto a consultar: ");
        List<Proyecto> proyectos = proyectoDao.findByNombre(nombre);
        if (proyectos.isEmpty()) {
            System.out.println("No se encontraron proyectos con ese nombre.");
            return;
        }

        System.out.println("Proyectos encontrados:");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    private void consultaPorCiudad() {
        String ciudad = preguntar("Ciudad del proyecto a consultar: ");
        List<Proyecto> proyectos = proyectoDao.findByCiudad(ciudad);
        if (proyectos.isEmpty()) {
            System.out.println("No se encontraron proyectos en esa ciudad.");
            return;
        }

        System.out.println("Proyectos encontrados:");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    private void listadoCompleto() {
        java.util.List<Proyecto> proyectos = proyectoDao.findAll();
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
            return;
        }

        System.out.println("Listado completo de proyectos:");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }
}
