/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import java.sql.Date;

/**
 *
 * @author Jorge Cabrera
 */
public class IncidenciaTrabajador extends Incidencia {

    private Integer idTrabajador;
    private String tipo;

    public IncidenciaTrabajador(Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
    }

    public IncidenciaTrabajador(Integer idTrabajador, String tipo, Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
        this.idTrabajador = idTrabajador;
        this.tipo = tipo;
    }

    public IncidenciaTrabajador(String tipo, Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
        this.tipo = tipo;
    }
        

        public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    

}
