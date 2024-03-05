package entidades;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pasteleria")
public class Pasteleria implements Serializable
{

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column(name = "title", length = 100, unique=true)
    @Expose
    private String nombre;

    @Column(name = "company", length=30, nullable = false)
    @Expose
    private String marca;

    @Column(name = "image")
    @Expose
    private String imagen;

    @Column(name = "price", nullable = false)
    @Expose
    private Double precio;


    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proveedor_key",foreignKey = @ForeignKey(name = "fk_Pasteleria_prov"))
    private Proveedor prov;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cliente_Pasteleria",
            joinColumns = @JoinColumn(name = "Pasteleria_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private List<Cliente> clientes=new ArrayList<>();

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    //constructores
    public Pasteleria(){}

    public Pasteleria(Long id, String nombre, String marca, String imagen, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.imagen = imagen;
        this.precio = precio;

    }

    //getters y setters

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Proveedor getProv() {
        return prov;
    }

    public void setProv(Proveedor prov) {
        this.prov = prov;
    }

    //tostring

    @Override
    public String toString() {
        return "Pasteleria{" +
                "id=" + id + "\n"+
                "nombre='" + nombre + "\n"+
                "marca='" + marca + "\n"+
                "imagen='" + imagen + "\n"+
                "precio=" + precio + "\n"+
                "}\n";
    }
}
