package dao;

import dto.PasteleriaDTO;
import entidades.Cliente;
import entidades.Pasteleria;
import entidades.Proveedor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

public class PasteleriaDAO implements PasteleriaDAOInterface{
    public List<Pasteleria> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL
        List<Pasteleria> todos = session.createQuery("from Pasteleria", Pasteleria.class).list();
        session.close();

        return todos;
    }
    @Override
    public List<Pasteleria> devolverTodos(int pagina,int objetos_por_pagina) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL con limits
        Query query=session.createQuery("from Pasteleria", Pasteleria.class);
        query.setMaxResults(objetos_por_pagina);
        query.setFirstResult((pagina-1)*objetos_por_pagina);
        List<Pasteleria> todos = query.list();

        session.close();

        return todos;
    }

    @Override
    public List<Pasteleria> devolverMasCaros() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL
        List<Pasteleria> caros = session.createQuery("from Pasteleria m where m.precio>40", Pasteleria.class).list();
        session.close();

        return caros;
    }


    @Override
    public List<Pasteleria> buscarPorNombreLike(String busqueda) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL

        Query<Pasteleria> query = session.createQuery("from Pasteleria m where m.nombre like :busqueda", Pasteleria.class);
        List<Pasteleria> filtro=query.setParameter("busqueda", "%"+busqueda+"%").list();
        session.close();

        return filtro;
    }

    @Override
    public List<Pasteleria> buscarEntrePrecios(Double min, Double max) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL

        Query<Pasteleria> query = session.createQuery("from Pasteleria m where m.precio>=:min and m.precio<=:max", Pasteleria.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        List<Pasteleria> filtro = query.list();
        session.close();

        return filtro;
    }

    @Override
    public List<Pasteleria> buscarEntrePreciosMarca(Double min, Double max, String marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL

        Query<Pasteleria> query = session.createQuery("from Pasteleria m where m.precio>=:min and m.precio<=:max and m.marca=:marca", Pasteleria.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        query.setParameter("marca", marca);
        List<Pasteleria> filtro = query.list();
        session.close();

        return filtro;
    }

    @Override
    public List<Pasteleria> buscarEntrePreciosMarcas(Double min, Double max, List<String> marcas) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL

        Query<Pasteleria> query = session.createQuery("from Pasteleria m where m.precio>=:min and m.precio<=:max and m.marca in (:marcas)", Pasteleria.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        query.setParameterList("marcas", marcas);
        List<Pasteleria> filtro = query.list();
        session.close();

        return filtro;
    }

    @Override
    public List<String> devolverTodasImages() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL
        List<String> imagenes = session.createQuery("select m.imagen from Pasteleria m", String.class).list();
        session.close();

        return imagenes;
    }

    @Override
    //public List<Map> devolverNombreImagenes() {
    public List<PasteleriaDTO> devolverNombreImagenes() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL
        //  List<Map> Pastelerias = session.createQuery("select new map(m.nombre, m.imagen) from Pasteleria m", Map.class).list();
        List<PasteleriaDTO> Pastelerias = session.createQuery("select new dto.PasteleriaDTO(m.nombre, m.imagen) from Pasteleria m", PasteleriaDTO.class).list();

        session.close();

        return Pastelerias;
    }


    @Override
    public Long totalPastelerias() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Long contador = (Long) session.createQuery("select count(e) from Pasteleria e").getSingleResult();

        session.close();

        return contador;
    }

    @Override
    public Pasteleria buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Pasteleria employee = session.find(Pasteleria.class, id);
        session.close();

        return employee;
    }

    @Override
    public Double mediaPrecios() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Double media = session.createQuery("select avg(e.precio) from Pasteleria e", Double.class).getSingleResult();

        session.close();

        return media;
    }

    @Override
    public Double mediaPreciosMarca(String marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Double> query = session.createQuery("select avg(e.precio) from Pasteleria e where e.marca =:marca", Double.class);
        query.setParameter("marca", marca);
        Double media = (query.getSingleResult());

        session.close();

        return media;
    }


    @Override
    public Pasteleria create(Pasteleria Pasteleria) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(Pasteleria);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return Pasteleria;
    }

    @Override
    public Pasteleria update(Pasteleria Pasteleria) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(Pasteleria);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return Pasteleria;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Pasteleria employee = this.buscarPorId(id);

            session.delete(employee);

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    @Override
    public boolean deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            String deleteQuery = "DELETE FROM Pasteleria";
            Query query = session.createQuery(deleteQuery);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }

        return true;
    }


}
