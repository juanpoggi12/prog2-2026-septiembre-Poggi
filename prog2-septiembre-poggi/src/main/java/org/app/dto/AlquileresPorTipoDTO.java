package org.app.dto;

public class AlquileresPorTipoDTO {
    private String tipoVehiculo;
    private Long cantidad;

    public AlquileresPorTipoDTO() {}

    public AlquileresPorTipoDTO(String tipoVehiculo, Long cantidad) {
        this.tipoVehiculo = tipoVehiculo;
        this.cantidad = cantidad;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}