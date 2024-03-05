import dao.AsociacionesDAO;
import dao.PasteleriaDAO;
import dao.ProveedorDAO;
import servicios.PasteleriasAPIREST;

public class Servidor {

    public static void main(String[] args) {
        PasteleriasAPIREST api=new PasteleriasAPIREST(new PasteleriaDAO(),new ProveedorDAO(),new AsociacionesDAO());
    }
}
