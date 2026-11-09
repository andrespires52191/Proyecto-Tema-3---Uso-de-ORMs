package models;

import java.time.LocalDate;

//Proveedores – Gestión (1:N): Un proveedor podrá estar asociado a varias gestiones, pero
//  cada gestión pertenecerá a un único proveedor.
//Piezas – Gestión (1:N): Una pieza podrá aparecer en varias gestiones, pero cada gestión
//  hará referencia a una única pieza.
//Proyectos – Gestión (1:N): Un proyecto podrá tener asociadas varias gestiones, pero cada
//  gestión pertenecerá a un único proyecto.
public class Gestion {
    int id;
    LocalDate fecha;
    int cantidad;
    Proveedor proveedor;
    Pieza pieza;
    Proyecto proyecto;
}
