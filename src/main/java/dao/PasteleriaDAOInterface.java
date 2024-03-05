package dao;

import dto.PasteleriaDTO;
import entidades.Cliente;
import entidades.Pasteleria;
import entidades.Proveedor;

import java.util.List;
import java.util.Map;

public interface PasteleriaDAOInterface {

    List<Pasteleria> devolverTodos();

    List<Pasteleria> devolverTodos(int pagina,int tamaño);

    List<Pasteleria> devolverMasCaros();

    List<String> devolverTodasImages();

    //List<Map> devolverNombreImagenes();
    List<PasteleriaDTO> devolverNombreImagenes();

    Long totalPastelerias();

    Pasteleria buscarPorId(Long id);

    Double mediaPrecios();

    Double mediaPreciosMarca(String marca);

    List<Pasteleria> buscarPorNombreLike(String nombre);

    List<Pasteleria> buscarEntrePrecios(Double min, Double max);

    List<Pasteleria> buscarEntrePreciosMarca(Double min, Double max,String marca);

    List<Pasteleria> buscarEntrePreciosMarcas(Double min, Double max,List<String> marcas);

    Pasteleria create(Pasteleria Pasteleria);

    Pasteleria update(Pasteleria Pasteleria);

    boolean deleteById(Long id);

    boolean deleteAll();


}
