package org.app.dto;

public class ClienteDTO {

    private Integer id;
    private String nombre;
    private String dni;
    private String telefono;
    private String email;
    private String licenciaConducir;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer idCliente, String nombre, String dni, String telefono, String email, String licenciaConducir) {
        this.id = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.licenciaConducir = licenciaConducir;
    }

    public Integer getIdCliente() {
        return id;
    }

    public void setIdCliente(Integer idCliente) {
        this.id = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }
}