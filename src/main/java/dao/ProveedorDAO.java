package dao;

import entidades.Pasteleria;
import entidades.Proveedor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.List;

public class ProveedorDAO implements ProveedorDAOInterface{
    @Override
    public Proveedor crearProveedor(Proveedor p) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return p;
    }

    @Override
    public Proveedor buscarPorID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Proveedor p = session.find(Proveedor.class, id);
        session.close();

        return p;
    }

}
