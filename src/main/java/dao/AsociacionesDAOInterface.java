package dao;

import entidades.Cliente;
import entidades.Pasteleria;
import entidades.Proveedor;

import java.util.List;

public interface AsociacionesDAOInterface {

    // RELACION 1 a M o M a 1 ------------------------------
    boolean asignarProveedor(Pasteleria m, Proveedor p);

    Proveedor obtenerProvedorPasteleria(Pasteleria m);

    List<Pasteleria> PasteleriasProveedor(Proveedor p);


    //----------------------RELACION M a N --------------

    List<Cliente> clientesConPasteleria(Pasteleria m);

    List<Pasteleria> PasteleriasComprados(Cliente c);

    boolean comprarPasteleria(Pasteleria m, Cliente c);


}
