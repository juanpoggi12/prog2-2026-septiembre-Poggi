package org.app.dto;

import org.app.models.EnumEstado;

import java.time.LocalDateTime;

public class AlquilerDTO {

    private Integer idAlquiler;
    private Integer idCliente;
    private LocalDateTime fechaHoraInicio;
    private String tipoVehiculo;
    private String vehiculo;
    private EnumEstado estado;

    public AlquilerDTO() {
    }

    public AlquilerDTO(Integer idAlquiler,
                       Integer idCliente,
                       LocalDateTime fechaHoraInicio,
                       String tipoVehiculo,
                       String vehiculo,
                       EnumEstado estado) {
        this.idAlquiler = idAlquiler;
        this.idCliente = idCliente;
        this.fechaHoraInicio = fechaHoraInicio;
        this.tipoVehiculo = tipoVehiculo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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