package com.vertice.Cleo.service;

import com.vertice.Cleo.modelos.Perfil;
import com.vertice.Cleo.repo.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {
    @Autowired
    PerfilRepository perfilRepository;
    //Listar Perfiles
    public List<Perfil> getAllPerfil(){
        List<Perfil> perfilList= new ArrayList<>();
        perfilRepository.findAll().forEach(perfil -> perfilList.add(perfil));
        return perfilList;
    }
    //Buscar Perfil por Id
    public Perfil getPerfilById(Integer id){
        return perfilRepository.findById(id).get();
    }
    //Guardar o Actualizar Perfil
    public Perfil SaveOrUpdatePerfil(Perfil perfil){
        Perfil perfilSorA = perfilRepository.save(perfil);
        return perfilSorA;
    }
    public boolean deletedPerfil(Integer id){
        perfilRepository.deleteById(id);
        if(perfilRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
