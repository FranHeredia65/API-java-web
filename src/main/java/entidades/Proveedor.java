package entidades;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Proveedores")
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column(name = "nombre", length = 100, unique=true)
    @Expose
    private String nombre;

    @Column(name = "direccion", length = 100)
    @Expose
    private String direccion;

    @Column(name = "telefono", length = 100)
    @Expose
    private String telefono;

    @Column(name = "email", length = 100,nullable=false)
    @Expose
    private String email;

    @OneToMany(mappedBy = "prov",fetch = FetchType.LAZY)
    private List<Pasteleria> almacen=new ArrayList<>();

    public List<Pasteleria> getAlmacen() {
        return almacen;
    }

    public void setAlmacen(List<Pasteleria> almacen) {
        this.almacen = almacen;
    }

    public Proveedor(){

    }

    public Proveedor(Long id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
