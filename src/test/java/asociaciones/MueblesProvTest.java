package asociaciones;

import dao.*;
import entidades.Cliente;
import entidades.Pasteleria;
import entidades.Proveedor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PasteleriasProvTest {

    static PasteleriaDAOInterface dao_Pasteleria;
    static ProveedorDAOInterface dao_prov;
    static AsociacionesDAOInterface dao_asoc;
    static ClienteDAOInterface dao_cliente;

    @BeforeAll
    static void setUp(){
        dao_Pasteleria=new PasteleriaDAO();
        dao_prov=new ProveedorDAO();
        dao_asoc=new AsociacionesDAO();
        dao_cliente=new ClienteDAO();
    }

    @Test
    public void test1(){
//        Proveedor prov=new Proveedor(null,"Ikea SA","Malaga plaza mayor","95243246","ikea@gmail.com");
//        dao_prov.crearProveedor(prov);
        Pasteleria Pasteleria=dao_Pasteleria.buscarPorNombreLike("albany table").get(0);
        //asignar uno nuevo
        //dao_asoc.asignarProveedor(Pasteleria,prov);
        //asignar otro ya existente
        dao_asoc.asignarProveedor(Pasteleria,dao_prov.buscarPorID(2L));

    }

    @Test
    public void test2(){
        Pasteleria m=dao_Pasteleria.buscarPorId(3L);
        Proveedor p=dao_asoc.obtenerProvedorPasteleria(m);
        System.out.println(p.getNombre()+" "+p.getEmail());
    }

    @Test
    public void test3(){
        Proveedor p=dao_prov.buscarPorID(1L);
        List<Pasteleria> lista=dao_asoc.PasteleriasProveedor(p);
        System.out.println(lista);
    }

    @Test
    public void test4(){
        Pasteleria m=dao_Pasteleria.buscarPorId(2L);
        List<Cliente> lista=dao_asoc.clientesConPasteleria(m);
        System.out.println(lista);
    }

    @Test
    public void test5(){
        Cliente c=dao_cliente.buscarPorId(1L);
        List<Pasteleria> lista=dao_asoc.PasteleriasComprados(c);
        System.out.println(lista);
    }

    @Test
    public void test6(){
        Cliente c=dao_cliente.buscarPorId(4L);
        Pasteleria m=dao_Pasteleria.buscarPorId(6L);
        dao_asoc.comprarPasteleria(m,c);

    }
}
