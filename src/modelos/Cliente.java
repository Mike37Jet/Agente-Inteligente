package modelos;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre, apellido, email, id;
    private int telefono, edad;

    public Cliente(String nombre, String apellido, String email, String id, int telefono, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.id = id;
        this.telefono = telefono;
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public int getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }
}
