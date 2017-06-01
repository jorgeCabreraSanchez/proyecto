/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;


/**
 *
 * @author Jorge Cabrera
 */
public abstract class Incidencia {

    protected Integer idIncidencia;
    protected String titulo;
    protected String descripcion;
    protected Date fecha;

    public Incidencia(Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        this.idIncidencia = idIncidencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public Incidencia(Integer idIncidencia, String titulo, String descripcion, LocalDate fecha) {
        this.idIncidencia = idIncidencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = Date.valueOf(fecha);
    }
    
        public Incidencia(Integer idIncidencia, String titulo, String descripcion) {
        this.idIncidencia = idIncidencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
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
    
    public String getFechaFormateada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formato.format((TemporalAccessor) this.fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = Date.valueOf(fecha);
    }

}
