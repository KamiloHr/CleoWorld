package com.vertice.Cleo.repo;

import com.vertice.Cleo.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmpleadoRepository extends CrudRepository <Empleado, Integer > {
    //Buscar empleado por Empresa
    @Query(value="SELECT * FROM empleado where empresa_asociado_id=?1",nativeQuery = true)
    ArrayList<Empleado> findByEmpresa(Integer id);
}
