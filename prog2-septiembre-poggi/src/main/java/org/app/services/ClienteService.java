package org.app.services;

import jakarta.persistence.EntityNotFoundException;
import org.app.dto.ClienteDTO;
import org.app.models.Cliente;
import org.app.repositories.AlquilerRepository;
import org.app.repositories.ClienteRepository;
import org.app.repositories.IAlquilerRepository;
import org.app.repositories.IClienteRepository;
import org.app.utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {
    private final IClienteRepository repository;
    private final IAlquilerRepository alquilerRepository;

    public ClienteService() {
        this.repository = new ClienteRepository();
        this.alquilerRepository = new AlquilerRepository();
    }

    public ClienteDTO crearCliente(ClienteDTO dto) {
        Validator.validarCliente(dto);

        Cliente existente = repository.obtenerClientePorDNI(dto.getDni());
        if (existente != null) {
            throw new IllegalArgumentException("ya existe un cliente con ese dni");
        }

        Cliente cliente = toEntity(dto);
        return toDTO(repository.crearCliente(cliente));
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = repository.obtenerClientes();
        return clientes.stream().map(ClienteService::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO actualizarCliente(Integer id, ClienteDTO dto) {
        Cliente cliente = repository.obtenerClientePorId(id);
        if (cliente == null) {
            throw new EntityNotFoundException("no se encontro el cliente");
        }

        Validator.validarCliente(dto);

        Cliente clienteDni = repository.obtenerClientePorDNI(dto.getDni());
        if (clienteDni != null && !clienteDni.getIdCliente().equals(id)) {
            throw new IllegalArgumentException("ya existe otro cliente con ese dni");
        }

        cliente.setNombre(dto.getNombre());
        cliente.setDni(dto.getDni());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setLicenciaConducir(dto.getLicenciaConducir());

        Cliente actualizado = repository.actualizarCliente(cliente);
        return toDTO(actualizado);
    }

    public void eliminarCliente(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("el id es obligatorio");
        }

        Cliente cliente = repository.obtenerClientePorId(id);
        if (cliente == null) {
            throw new EntityNotFoundException("no se encontro el cliente");
        }

        repository.eliminarCliente(cliente);
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setDni(dto.getDni());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setLicenciaConducir(dto.getLicenciaConducir());
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombre(cliente.getNombre());
        dto.setDni(cliente.getDni());
        dto.setTelefono(cliente.getTelefono());
        dto.setEmail(cliente.getEmail());
        dto.setLicenciaConducir(cliente.getLicenciaConducir());
        return dto;
    }
}