package dto;


public class PasteleriaDTO {
    private String nombre,url_imagen;

    public PasteleriaDTO(){}

    public PasteleriaDTO(String nombre, String url_imagen) {
        this.nombre = nombre;
        this.url_imagen = url_imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "PasteleriaDTO{" +
                "nombre='" + nombre + '\'' +
                ", url_imagen='" + url_imagen + '\'' +
                '}';
    }
}
