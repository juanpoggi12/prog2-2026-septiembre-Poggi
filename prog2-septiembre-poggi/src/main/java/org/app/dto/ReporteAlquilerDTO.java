package org.app.dto;

import java.util.List;

public class ReporteAlquilerDTO {
    private List<AlquileresPorTipoDTO> reportePorTipoVehiculo;
    private List<AlquileresPorEstadoDTO> reportePorEstado;

    public ReporteAlquilerDTO() {}

    public ReporteAlquilerDTO(List<AlquileresPorEstadoDTO> reportePorEstado, List<AlquileresPorTipoDTO> reportePorTipoVehiculo) {
        this.reportePorEstado = reportePorEstado;
        this.reportePorTipoVehiculo = reportePorTipoVehiculo;
    }

    public List<AlquileresPorTipoDTO> getReportePorTipoVehiculo() {
        return reportePorTipoVehiculo;
    }

    public void setReportePorTipoVehiculo(List<AlquileresPorTipoDTO> reportePorTipoVehiculo) {
        this.reportePorTipoVehiculo = reportePorTipoVehiculo;
    }

    public List<AlquileresPorEstadoDTO> getReportePorEstado() {
        return reportePorEstado;
    }

    public void setReportePorEstado(List<AlquileresPorEstadoDTO> reportePorEstado) {
        this.reportePorEstado = reportePorEstado;
    }
}