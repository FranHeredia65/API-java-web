package dao;

import entidades.Cliente;
import entidades.Proveedor;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.PersistenceException;

public class ClienteDAO implements ClienteDAOInterface {

    @Override
    public Cliente crearCliente(Cliente c) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return c;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Cliente c = session.find(Cliente.class, id);
        session.close();

        return c;
    }
}
