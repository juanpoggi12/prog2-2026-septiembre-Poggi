package org.app.services;

import jakarta.persistence.EntityNotFoundException;
import org.app.dto.AlquilerDTO;
import org.app.dto.AlquileresPorEstadoDTO;
import org.app.dto.AlquileresPorTipoDTO;
import org.app.dto.ReporteAlquilerDTO;
import org.app.models.Alquiler;
import org.app.models.Cliente;
import org.app.models.EnumEstado;
import org.app.repositories.AlquilerRepository;
import org.app.repositories.ClienteRepository;
import org.app.repositories.IAlquilerRepository;
import org.app.repositories.IClienteRepository;
import org.app.utils.Validator;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AlquilerService {
    private final IAlquilerRepository alquilerRepository;
    private final IClienteRepository clienteRepository;

    public AlquilerService(){
        this.alquilerRepository = new AlquilerRepository();
        this.clienteRepository = new ClienteRepository();
    }

    public AlquilerDTO crearAlquiler(AlquilerDTO dto) {
        Validator.validarAlquiler(dto);

        Cliente cliente = clienteRepository.obtenerClientePorId(dto.getIdCliente());
        if (cliente == null) {
            throw new EntityNotFoundException("no se encontro el cliente del alquiler");
        }

        Alquiler alquiler = toEntity(dto);
        alquiler.setCliente(cliente);
        alquiler.setEstado(EnumEstado.PENDIENTE);

        Alquiler creado = alquilerRepository.crearAlquiler(alquiler);
        return toDTO(creado);
    }

    public AlquilerDTO actualizarAlquiler(AlquilerDTO dto) {
        Validator.validarAlquiler(dto);

        Alquiler alquiler = alquilerRepository.obtenerPorID(dto.getIdAlquiler());
        if  (alquiler == null) {
            throw new EntityNotFoundException("no se encontro el alquiler solicitafo");
        }

        Cliente cliente = clienteRepository.obtenerClientePorId(dto.getIdCliente());
        if(cliente == null) {
            throw new IllegalArgumentException("el cliente dl alquiler no existe");
        }

        alquiler.setCliente(cliente);
        alquiler.setFechaHoraInicio(dto.getFechaHoraInicio());
        alquiler.setTipoVehiculo(dto.getTipoVehiculo());
        alquiler.setVehiculo(dto.getVehiculo());
        alquiler.setEstado(dto.getEstado());

        Alquiler actualizado = alquilerRepository.actualizarAlquiler(alquiler);
        return toDTO(actualizado);
    }

    public List<AlquilerDTO> obtenerAlquileresConFiltros(Date fechaDesde, Date fechaHasta, String tipoVehiculo, String estado) {
        EnumEstado estadoEnum = null;

        if (estado != null && !estado.isBlank()) {
            try {
                estadoEnum = EnumEstado.valueOf(estado.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("estado invalido");
            }
        }

        if (fechaDesde != null && fechaHasta != null && fechaDesde.after(fechaHasta)) {
            throw new IllegalArgumentException("la fecha desde no puede ser posterior a la fecha hasta");
        }

        List<Alquiler> alquileres = alquilerRepository.buscarAlquileresConFiltros(fechaDesde, fechaHasta, tipoVehiculo, estadoEnum);
        return alquileres.stream().map(AlquilerService::toDTO).collect(Collectors.toList());
    }

    public ReporteAlquilerDTO generarReporteAlquileres() {
        CompletableFuture<List<AlquileresPorTipoDTO>> porTipo = CompletableFuture.supplyAsync(this::contarAlquileresPorTipo);
        CompletableFuture<List<AlquileresPorEstadoDTO>> porEstado = CompletableFuture.supplyAsync(this::contarAlquileresPorEstado);

        return porTipo.thenCombine(
                porEstado,
                (tipos, estados) -> new ReporteAlquilerDTO(estados, tipos)
        ).join();
    }

    private List<AlquileresPorTipoDTO> contarAlquileresPorTipo() {
        List<Object[]> resultados = alquilerRepository.contarPorTipoVehiculo();

        return resultados.stream()
                .map(r -> new AlquileresPorTipoDTO((String) r[0], (Long) r[1]))
                .collect(Collectors.toList());
    }

    private List<AlquileresPorEstadoDTO> contarAlquileresPorEstado() {
        List<Object[]> resultados = alquilerRepository.contarPorEstado();

        return resultados.stream()
                .map(r -> new AlquileresPorEstadoDTO((EnumEstado) r[0], (Long) r[1]))
                .collect(Collectors.toList());
    }



    public static AlquilerDTO toDTO(Alquiler alquiler) {
        AlquilerDTO dto = new AlquilerDTO();
        dto.setIdCliente(alquiler.getId());
        dto.setIdCliente(alquiler.getCliente().getIdCliente());
        dto.setFechaHoraInicio(alquiler.getFechaHoraInicio());
        dto.setTipoVehiculo(alquiler.getTipoVehiculo());
        dto.setVehiculo(alquiler.getVehiculo());
        dto.setEstado(alquiler.getEstado());
        return dto;
    }

    public static Alquiler toEntity(AlquilerDTO dto) {
        Alquiler alquiler = new Alquiler();
        alquiler.setFechaHoraInicio(dto.getFechaHoraInicio());
        alquiler.setTipoVehiculo(dto.getTipoVehiculo());
        alquiler.setVehiculo(dto.getVehiculo());
        alquiler.setEstado(dto.getEstado());

        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(dto.getIdCliente());
            alquiler.setCliente(cliente);
        }

        return alquiler;
    }

}
