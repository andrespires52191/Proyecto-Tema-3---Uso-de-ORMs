package models;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Pieza implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @NotNull
//    @Column(unique = true)
    String codigo;

    @NotNull
    String nombre;

    @NotNull
    String precio;

    @NotNull
    String descripcion;

    @OneToMany(mappedBy = "pieza", fetch = FetchType.LAZY)
    private List<Gestion> gestiones;

    public Pieza() {
    }

    public Pieza(String codigo, String nombre, String precio, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Pieza{" +
                "descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
