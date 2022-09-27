package com.vertice.Cleo.modelos;

import javax.persistence.*;
@Entity
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String correo;
    @OneToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    private String rolEmpleado;
    @ManyToOne
    @JoinColumn(name = "empresa_asociado_id")
    private  Empresa empresaAsociado;
    private String password;
    private Boolean estado;
    public Empleado() {
    }

    public Empleado(String nombre, String correo, Perfil perfil, String rolEmpleado, Empresa empresaAsociado,String password,Boolean estado) {
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
        this.rolEmpleado = rolEmpleado;
        this.empresaAsociado = empresaAsociado;
        this.password= password;
        this.estado=estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getRolEmpleado() {
        return rolEmpleado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setRolEmpleado(String rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public Empresa getEmpresaAsociado() {
        return empresaAsociado;
    }

    public void setEmpresaAsociado(Empresa empresaAsociado) {
        this.empresaAsociado = empresaAsociado;
    }
}
