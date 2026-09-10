package org.app.repositories;

import org.app.models.Cliente;
import org.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteRepository implements IClienteRepository {

    @Override
    public Cliente crearCliente(Cliente cliente) {
        try(Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();

            try {
                session.persist(cliente);
                tx.commit();
                return cliente;
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public Cliente obtenerClientePorId(Integer id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Cliente> obtenerClientes() {
        try(Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Cliente").list();
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        try(Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();

            try {
                Cliente actualizado = session.merge(cliente);
                tx.commit();
                return actualizado;
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        cliente.setEsActivo(false);
        actualizarCliente(cliente);
    }

    @Override
    public Cliente obtenerClientePorDNI(String dni) {
        try (Session session = HibernateUtil.getSession()) {

            return session.createQuery(
                            "FROM Cliente c WHERE c.dni = :dni",
                            Cliente.class
                    )
                    .setParameter("dni", dni)
                    .uniqueResult();
        }
    }
}
