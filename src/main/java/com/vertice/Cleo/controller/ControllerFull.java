package com.vertice.Cleo.controller;

import com.vertice.Cleo.modelos.Empleado;
import com.vertice.Cleo.modelos.Empresa;
import com.vertice.Cleo.modelos.MovimientosDinero;
import com.vertice.Cleo.repo.MovimientoRepository;
import com.vertice.Cleo.service.EmpleadoService;
import com.vertice.Cleo.service.EmpresaService;
import com.vertice.Cleo.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@RestController
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    MovimientoService movimientoService;
    //Empresas
    @GetMapping("/Empresas")//Ver Empresas
    public List<Empresa> viewEmpresas(){
        return empresaService.getAllEmpresa();
    }
   /* @PostMapping("/Empresas")//Guardar Empresas
    public Empresa saveEmpresas(@RequestBody Empresa empresa){
        return empresaService.saveOrUpdateEmpresa(empresa);
    }
    @PatchMapping("/Empresas/{id}")//Actualizar Empresas
    public  Empresa updateEmpresas(@RequestBody Empresa empresa, @PathVariable("id") Integer id){
        Empresa empresaUpdate= empresaService.getEmpresaById(id);
        empresaUpdate.setNombre(empresa.getNombre());
        empresaUpdate.setDireccion(empresa.getDireccion());
        empresaUpdate.setTelefono(empresa.getTelefono());
        empresaUpdate.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(empresaUpdate);
    }*/
    @GetMapping("/Empresas/{id}")//Buscar Empresas Por Id
    public Empresa findEmpresaId(@PathVariable("id") Integer id){
        return empresaService.getEmpresaById(id);
    }
    @DeleteMapping("/Empresas/{id}")//Eliminar Empresas
    public String deletedEmpresas(@PathVariable Integer id){
        boolean valor = empresaService.deletedEmpresa(id);
        if (valor == true){
            return "La Empresa con Id " + id + " Se elimino Correctamente";
        }
        return "No se puedo Eliminar la Empres con Id " + id;
    }

    // EMPLEADO
    @GetMapping ("/Empleados")

    public List <Empleado> viewEmpleados (){
        return empleadoService.getAllEmpleado();
    }

    /*@PostMapping("/Empleados")//Guardar Empleado
    public Empleado saveEmpleados(@RequestBody Empleado empleado){
        return empleadoService.saveOrUpdateEmpleado(empleado);
    }
    @PatchMapping("/Empleados/{id}")//Actualizar Empleado
    public  Empleado updateEmpleado(@RequestBody Empleado empleado, @PathVariable("id") Integer id){
        Empleado empleadoUpdate= empleadoService.getEmpleadoById(id);
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setCorreo(empleado.getCorreo());
        empleadoUpdate.setPerfil(empleado.getPerfil());
        return empleadoService.saveOrUpdateEmpleado(empleadoUpdate);
    }*/
    @GetMapping("/Empleados/{id}")//Buscar Empleado Por Id
    public Empleado findEmpleadoId(@PathVariable("id") Integer id){
        return empleadoService.getEmpleadoById(id);
    }
    @DeleteMapping("/Empleados/{id}")//Eliminar Empleado
    public String deletedEmpleado(@PathVariable Integer id){
        boolean valor = empleadoService.deletedEmpleado(id);
        if (valor == true){
            return "La Empleado con Id " + id + " Se elimino Correctamente";
        }
        return "No se puedo Eliminar la Empleado con Id " + id;
    }
    // MOVIMIENTOS DINERO

    //Ver Movimientos
    @GetMapping("/Movimientos")
    public List<MovimientosDinero> viewMovimientos(){
        return movimientoService.getAllMovimiento();
    }
    //Guardar Movimientos
    @PostMapping("/Movimientos")
    public MovimientosDinero saveMovimientos(@RequestBody MovimientosDinero movimientosDinero){
        return  movimientoService.saveOrUpdateMovimiento(movimientosDinero);
    }

    //Actualizar Movimiento
    @PatchMapping("/Movimiento/{id}")
    public MovimientosDinero updateMovimiento(@RequestBody MovimientosDinero movimientosDinero,@PathVariable("id")Integer id){
        MovimientosDinero movUpdate=movimientoService.getMovimientoById(id);
        movUpdate.setMonto(movimientosDinero.getMonto());
        movUpdate.setConceptoMovimiento(movimientosDinero.getConceptoMovimiento());
        movUpdate.setUsuarioMovimiento(movimientosDinero.getUsuarioMovimiento());
        return movimientoService.saveOrUpdateMovimiento(movUpdate);
    }
    @DeleteMapping("/Movimientos/{id}")
    public String deletedMovimiento(@PathVariable("id")Integer id){
        boolean valor = movimientoService.deletedMovimiento(id);
        if(valor==true){
            return "El movimiento con ID " + "Se elimino Correctamente";
        }
        return "No se pudo Eliminar el Movimiento con ID " + id;
    }


}
