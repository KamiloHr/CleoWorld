package com.vertice.Cleo.service;

import com.vertice.Cleo.modelos.MovimientosDinero;
import com.vertice.Cleo.repo.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;

    //Listar Todos Los Movimientos
    public List<MovimientosDinero> getAllMovimiento(){
        List<MovimientosDinero> movList = new ArrayList<>();
        movimientoRepository.findAll().forEach(movimientos -> movList.add(movimientos));
        return movList;
    }

    //Buscar Movimientos por Id
    public MovimientosDinero getMovimientoById(Integer id){
        return movimientoRepository.findById(id).get();
    }

    //Guardar o Actualizar Movimientos
    public MovimientosDinero saveOrUpdateMovimiento(MovimientosDinero movimientosDinero){
        MovimientosDinero movSU=movimientoRepository.save(movimientosDinero);
        return movSU;
    }
    //Eliminiar Movimientos Dinero
    public boolean deletedMovimiento(Integer id){
        movimientoRepository.deleteById(id);
        if(movimientoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public boolean saveOrUpdateMovimientosDinero(MovimientosDinero mov) {
        MovimientosDinero movSU= movimientoRepository.save(mov);
        if(movimientoRepository.findById(mov.getId())!=null){
            return true;
        }
        return false;
    }

    public ArrayList<MovimientosDinero> movimientoPorEmpleado(Integer id) {
        return movimientoRepository.findByEmpleado(id);
    }
    public Long MontosPorEmpleado(Integer id){
        return movimientoRepository.MontosPorEmpleado(id);
    }
    public Long obtenerSumaMontos(){
        return movimientoRepository.SumarMonto();
    }
    public ArrayList<MovimientosDinero> obtenerPorEmpresa(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
        return movimientoRepository.findByEmpresa(id);
    }
    public Long MontosPorEmpresa(Integer id){
        return movimientoRepository.MontosPorEmpresa(id);
    }
    public Integer IdPorCorreo(String correo){
        return movimientoRepository.IdPorCorreo(correo);
    }
}
