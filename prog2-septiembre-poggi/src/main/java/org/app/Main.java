package org.app;

import org.app.dto.AlquilerDTO;
import org.app.dto.ClienteDTO;
import org.app.dto.ReporteAlquilerDTO;
import org.app.models.EnumEstado;
import org.app.services.AlquilerService;
import org.app.services.ClienteService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        AlquilerService alquilerService = new AlquilerService();

        try {
            System.out.println("LISTADO CLIENTES");
            clienteService.listarClientes().forEach(c ->
                    System.out.println(c.getIdCliente() + " - " + c.getNombre())
            );

            System.out.println("\nCREAR CLIENTE");
            ClienteDTO cliente = new ClienteDTO();
            cliente.setNombre("Pedro Lopez");
            cliente.setDni("12121221");
            cliente.setTelefono("121212");
            cliente.setEmail("pedro@email.com");
            cliente.setLicenciaConducir("1");

            ClienteDTO clienteCreado = clienteService.crearCliente(cliente);
            System.out.println("Cliente creado con ID: " + clienteCreado.getIdCliente());

            System.out.println("\nCREAR ALQUILER");
            AlquilerDTO alquiler = new AlquilerDTO();
            alquiler.setIdCliente(clienteCreado.getIdCliente());
            alquiler.setFechaHoraInicio(LocalDateTime.now());
            alquiler.setTipoVehiculo("suv");
            alquiler.setVehiculo("auto");
            alquiler.setEstado(EnumEstado.PENDIENTE);

            AlquilerDTO alquilerCreado = alquilerService.crearAlquiler(alquiler);
            System.out.println("Alquiler creado con ID: " + alquilerCreado.getIdAlquiler());

            System.out.println("\nBUSCAR ALQUILERES");
            alquilerService.obtenerAlquileresConFiltros(
                    null,
                    null,
                    "suv",
                    "PENDIENTE"
            ).forEach(a ->
                    System.out.println(
                            a.getIdAlquiler() + " - " +
                                    a.getTipoVehiculo() + " - " +
                                    a.getEstado()
                    )
            );

            System.out.println("\nREPORTE");
            ReporteAlquilerDTO reporte = alquilerService.generarReporteAlquileres();

            System.out.println("Por tipo de vehiculo:");
            reporte.getReportePorTipoVehiculo().forEach(r ->
                    System.out.println(r.getTipoVehiculo() + ": " + r.getCantidad())
            );

            System.out.println("Por estado:");
            reporte.getReportePorEstado().forEach(r ->
                    System.out.println(r.getEstado() + ": " + r.getCantidad())
            );

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}