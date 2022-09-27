package com.vertice.Cleo.modelos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
    @Table(name="Movimientos")
    public class MovimientosDinero{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private long monto;

        private String conceptoMovimiento;
        @ManyToOne
        @JoinColumn(name = "usuario_movimiento_id")
        private Empleado usuarioMovimiento;
        @DateTimeFormat(pattern="yyyy-MM-dd")private Date fecha;
        public MovimientosDinero() {
        }

        public MovimientosDinero(long monto, String conceptoMovimiento, Empleado usuarioMovimiento,Date fecha) {
            this.monto = monto;
            this.conceptoMovimiento = conceptoMovimiento;
            this.usuarioMovimiento = usuarioMovimiento;
            this.fecha=fecha;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getMonto() {
            return monto;
        }

        public void setMonto(long monto) {
            this.monto = monto;
        }

        public String getConceptoMovimiento() {
            return conceptoMovimiento;
        }

        public void setConceptoMovimiento(String conceptoMovimiento) {
            this.conceptoMovimiento = conceptoMovimiento;
        }

        public Empleado getUsuarioMovimiento() {
            return usuarioMovimiento;
        }

        public void setUsuarioMovimiento(Empleado usuarioMovimiento) {
            this.usuarioMovimiento = usuarioMovimiento;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }
}
