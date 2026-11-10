package models;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @NotNull
    @Column(unique = true)
    String codigo;

    @NotNull
    String nombre;

    @NotNull
    String apellidos;

    @NotNull
    String direccionPostal;

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Gestion> gestiones;

    public Proveedor() {
    }

    public Proveedor(String codigo, String nombre, String apellidos, String direccionPostal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccionPostal = direccionPostal;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccionPostal='" + direccionPostal + '\'' +
                '}';
    }
}
