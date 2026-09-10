package org.app.dto;

import org.app.models.EnumEstado;

public class AlquileresPorEstadoDTO {
    private EnumEstado estado;
    private Long cantidad;

    public AlquileresPorEstadoDTO() {}

    public AlquileresPorEstadoDTO(EnumEstado estado, Long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public EnumEstado getEstado() {
        return estado;
    }

    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}