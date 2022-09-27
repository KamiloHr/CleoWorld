package com.vertice.Cleo.service;

import com.vertice.Cleo.modelos.Empresa;
import com.vertice.Cleo.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;
    //Listar Todas Empresas
    public List<Empresa> getAllEmpresa(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }
    //Buscar Empresa por Id
    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }
    //Guardar o Actualizar Empresa
    public boolean saveOrUpdateEmpresa(Empresa empresa){
        Empresa empSU =empresaRepository.save(empresa);
        if(empresaRepository.findById(empresa.getId())!=null){
            return true;
        }
        return false;
    }
    //Eliminar Empresa
    public boolean deletedEmpresa (Integer id){
        empresaRepository.deleteById(id);
        if (empresaRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
