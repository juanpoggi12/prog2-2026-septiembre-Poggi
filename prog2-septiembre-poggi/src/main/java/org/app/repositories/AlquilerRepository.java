package org.app.repositories;

import jakarta.persistence.criteria.*;

import org.app.models.Alquiler;
import org.app.models.EnumEstado;
import org.app.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlquilerRepository implements IAlquilerRepository {

    @Override
    public Alquiler crearAlquiler(Alquiler alquiler) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            try {
                session.persist(alquiler);
                tx.commit();
                return alquiler;

            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public Alquiler actualizarAlquiler(Alquiler alquiler) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            try {
                Alquiler actualizado = session.merge(alquiler);
                tx.commit();
                return actualizado;

            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public Alquiler obtenerPorID(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Alquiler.class, id);
        }
    }

    @Override
    public List<Alquiler> buscarAlquileresConFiltros(Date fechaDesde, Date fechaHasta, String tipoVehiculo, EnumEstado estado) {
        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Alquiler> query = cb.createQuery(Alquiler.class);
            Root<Alquiler> alquiler = query.from(Alquiler.class);
            alquiler.fetch("cliente", JoinType.LEFT);

            List<Predicate> filtros = new ArrayList<>();

            if (fechaDesde != null) {
                filtros.add( cb.greaterThanOrEqualTo(alquiler.get("fechaHoraInicio"), fechaDesde));
            }
            if (fechaHasta != null) {
                filtros.add(cb.lessThanOrEqualTo(alquiler.get("fechaHoraInicio"), fechaHasta));
            }
            if (tipoVehiculo != null && !tipoVehiculo.isBlank()) {
                filtros.add(cb.like(cb.lower(alquiler.get("tipoVehiculo")), "%" + tipoVehiculo.toLowerCase() + "%"));
            }
            if (estado != null) {
                filtros.add(cb.equal(alquiler.get("estado"), estado));
            }

            query.where(filtros.toArray(new Predicate[0]));
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Object[]> contarPorTipoVehiculo() {
        try (Session session = HibernateUtil.getSession()) {

            String hql = "SELECT a.tipoVehiculo, COUNT(a) " + "FROM Alquiler a " + "GROUP BY a.tipoVehiculo";
            return session.createQuery(
                    hql,
                    Object[].class
            ).getResultList();
        }
    }

    @Override
    public List<Object[]> contarPorEstado() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT a.estado, COUNT(a) " + "FROM Alquiler a " + "GROUP BY a.estado";

            return session.createQuery(
                    hql,
                    Object[].class
            ).getResultList();
        }
    }
}

