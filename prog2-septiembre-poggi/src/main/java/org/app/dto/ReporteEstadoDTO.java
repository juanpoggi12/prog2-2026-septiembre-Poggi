package org.app.dto;

public class ReporteEstadoDTO {

    private String estado;
    private Long cantidad;

    public ReporteEstadoDTO() {
    }

    public ReporteEstadoDTO(String estado, Long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}