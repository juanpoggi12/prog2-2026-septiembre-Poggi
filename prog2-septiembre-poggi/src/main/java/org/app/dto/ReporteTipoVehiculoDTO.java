package org.app.dto;

public class ReporteTipoVehiculoDTO {

    private String tipoVehiculo;
    private Long cantidad;

    public ReporteTipoVehiculoDTO() {
    }

    public ReporteTipoVehiculoDTO(String tipoVehiculo, Long cantidad) {
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