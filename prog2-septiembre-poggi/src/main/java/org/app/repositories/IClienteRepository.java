package org.app.repositories;

import org.app.models.Cliente;

import java.util.List;

public interface IClienteRepository {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(Integer id);
    List<Cliente> obtenerClientes();
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
    Cliente obtenerClientePorDNI(String dni);

}
