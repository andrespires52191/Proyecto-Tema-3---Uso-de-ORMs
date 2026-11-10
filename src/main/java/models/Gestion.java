package models;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

//Proveedores – Gestión (1:N): Un proveedor podrá estar asociado a varias gestiones, pero
//  cada gestión pertenecerá a un único proveedor.
//Piezas – Gestión (1:N): Una pieza podrá aparecer en varias gestiones, pero cada gestión
//  hará referencia a una única pieza.
//Proyectos – Gestión (1:N): Un proyecto podrá tener asociadas varias gestiones, pero cada
//  gestión pertenecerá a un único proyecto.
@Entity
public class Gestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @NotNull
    LocalDate fecha;

    @NotNull
    int cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    Proveedor proveedor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pieza_id")
    Pieza pieza;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    Proyecto proyecto;

    public Gestion() {
    }

    public Gestion(LocalDate fecha, int cantidad, Proveedor proveedor, Pieza pieza, Proyecto proyecto) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.pieza = pieza;
        this.proyecto = proyecto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "Gestion{" +
                "id=" + id +
                ", pieza=" + pieza.getCodigo() +
                ", proyecto=" + proyecto.getCodigo() +
                ", proveedor=" + proveedor.getCodigo() +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}
