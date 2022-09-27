package com.vertice.Cleo.repo;

import com.vertice.Cleo.modelos.MovimientosDinero;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientosDinero, Integer> {
    //Metodo para filtrar movimientos por empleado
    @Query(value ="select * from movimientos where usuario_movimiento_id=?1", nativeQuery = true)
    public abstract ArrayList<MovimientosDinero> findByEmpleado(Integer id);
    @Query(value="SELECT SUM(monto) from movimientos where usuario_movimiento_id=?1", nativeQuery = true)
    public abstract Long MontosPorEmpleado(Integer id); //id del empleado
    @Query(value="SELECT SUM(monto) from movimientos", nativeQuery = true)
    public abstract Long SumarMonto();
    @Query(value="select * from movimientos where usuario_movimiento_id in (select id from empleado where empresa_asociado_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientosDinero> findByEmpresa(Integer id);

    @Query(value="select sum(monto) from movimientos where usuario_movimiento_id in (select id from empleado where empresa_asociado_id= ?1)", nativeQuery = true)
    public abstract Long MontosPorEmpresa(Integer id); //Id de la empresa
    @Query(value = "select id from empleado where correo=?1",nativeQuery = true)
    public abstract Integer IdPorCorreo(String correo);
}