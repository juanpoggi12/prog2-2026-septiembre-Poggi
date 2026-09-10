package org.app.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlquiler")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(nullable = false)
    private String tipoVehiculo;

    @Column(nullable = false)
    private String vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumEstado estado;

    public Alquiler() {
    }

    public Alquiler(Integer id, Cliente cliente,
                    LocalDateTime fechaHoraInicio,
                    String tipoVehiculo,
                    String vehiculo,
                    EnumEstado estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaHoraInicio = fechaHoraInicio;
        this.tipoVehiculo = tipoVehiculo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EnumEstado getEstado() {
        return estado;
    }

    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }
}