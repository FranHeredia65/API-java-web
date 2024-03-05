package dao;

import entidades.Cliente;
import entidades.Pasteleria;
import entidades.Proveedor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class AsociacionesDAO implements AsociacionesDAOInterface {

    @Override
    public boolean asignarProveedor(Pasteleria m, Proveedor p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            m.setProv(p);
            session.update(m);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public Proveedor obtenerProvedorPasteleria(Pasteleria m) {
        Proveedor proveedor;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Pasteleria> query = session.createQuery("select m from Pasteleria m join fetch m.prov where m.id =:id", Pasteleria.class);
            query.setParameter("id", m.getId());
            proveedor = query.getSingleResult().getProv();

        } catch (NoResultException nre) {
            proveedor=null;
        }
        session.close();


        session.close();


        return proveedor;
    }

    @Override
    public List<Pasteleria> PasteleriasProveedor(Proveedor p) {
        List<Pasteleria> lista_Pastelerias;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Proveedor> query = session.createQuery("select p from Proveedor p join fetch p.almacen  where p.id =:id", Proveedor.class);
            query.setParameter("id", p.getId());
            lista_Pastelerias = query.getSingleResult().getAlmacen();
        } catch (NoResultException nre) {
            lista_Pastelerias = new ArrayList<>();
        }

        session.close();

        return lista_Pastelerias;
    }

    @Override
    public List<Cliente> clientesConPasteleria(Pasteleria m) {
        List<Cliente> lista_clientes = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Pasteleria> query = session.createQuery("select m from Pasteleria m join fetch m.clientes where m.id =:id", Pasteleria.class);
            query.setParameter("id", m.getId());
            lista_clientes = query.getSingleResult().getClientes();
        } catch (NoResultException nre) {
            lista_clientes = new ArrayList<>();
        }
        session.close();

        return lista_clientes;
    }

    @Override
    public List<Pasteleria> PasteleriasComprados(Cliente c) {
        List<Pasteleria> lista_Pastelerias = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Cliente> query = session.createQuery("select c from Cliente c join fetch c.Pastelerias where c.id =:id", Cliente.class);
            query.setParameter("id", c.getId());
            lista_Pastelerias = query.getSingleResult().getPastelerias();
        } catch (NoResultException nre) {
            lista_Pastelerias = new ArrayList<>();
        }

        session.close();
        return lista_Pastelerias;
    }

    @Override
    public boolean comprarPasteleria(Pasteleria m, Cliente c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            List<Pasteleria> lista_Pastelerias = PasteleriasComprados(c);
            lista_Pastelerias.add(m);
            c.setPastelerias(lista_Pastelerias);

            List<Cliente> lista_clientes = clientesConPasteleria(m);
            lista_clientes.add(c);
            m.setClientes(lista_clientes);

            session.beginTransaction();

            session.update(m);
            session.update(c);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }


        session.close();
        return true;
    }

}
