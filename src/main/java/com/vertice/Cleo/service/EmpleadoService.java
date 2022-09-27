package com.vertice.Cleo.service;
import com.vertice.Cleo.modelos.Empleado;
import com.vertice.Cleo.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    // listado de los empleados
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //empleados por ID

    public Empleado getEmpleadoById (Integer id){
        return empleadoRepository.findById(id).get();
    }

    // Guardar y Actualizar empleado

    public Boolean saveOrUpdateEmpleado (Empleado empleado){
        Empleado savEmpl=empleadoRepository.save(empleado);
        if(empleadoRepository.findById(savEmpl.getId())!=null){
            return true;
        }
        return false;
    }

    // eliminar Empleado
    public boolean deletedEmpleado (Integer id){
        empleadoRepository.deleteById(id);
        if (empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
    //Metodo para buscar Empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepository.findByEmpresa(id);
    }
}
