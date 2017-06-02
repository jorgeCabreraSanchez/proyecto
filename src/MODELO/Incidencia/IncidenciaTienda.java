/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 *
 * @author Jorge Cabrera
 */
public class IncidenciaTienda extends Incidencia {

    private Integer idTienda;

    public IncidenciaTienda(Integer idTienda, Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
        this.idTienda = idTienda;
    }

    public IncidenciaTienda(Integer idIncidencia, String titulo, String descripcion, LocalDate fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
    }

    public IncidenciaTienda(Integer idIncidencia, String titulo, String descripcion, Date fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
    }
    
      public IncidenciaTienda(String titulo, String descripcion, LocalDate fecha) {
        super(titulo,descripcion,fecha);
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
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
        return formato.format(this.fecha.toLocalDate());
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = Date.valueOf(fecha);
    }

    public String toString() {
        return "IDIncidencia: " + this.idIncidencia + "  Fecha: " + getFechaFormateada() + "  Título:  " + this.titulo + " \n Descripción: "
                + this.descripcion;
    }
}
