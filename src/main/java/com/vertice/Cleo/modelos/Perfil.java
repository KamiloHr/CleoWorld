package com.vertice.Cleo.modelos;


import javax.persistence.*;

@Entity
@Table(name="Perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imagen;
    private String telefono;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Empleado usuario;

    public Perfil() {
    }

    public Perfil(String imagen, String telefono, Empleado usuario) {
        this.imagen = imagen;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empleado getUsuario() {
        return usuario;
    }

    public void setUsuario(Empleado usuario) {
        this.usuario = usuario;
    }
}
