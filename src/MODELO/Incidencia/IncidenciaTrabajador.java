/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public IncidenciaTrabajador(String tipo, Integer idIncidencia, String titulo, String descripcion, LocalDate fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
        this.tipo = tipo;
    }

    public IncidenciaTrabajador() {
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

    public IncidenciaTrabajador igual(IncidenciaTienda incidencia) {
        this.setTitulo(incidencia.getTitulo());
        this.setDescripcion(incidencia.getDescripcion());
        this.setFecha(incidencia.getFecha());

        return this;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "IDIncidencia: " + this.idIncidencia + "  Fecha: " + getFechaFormateada() + "  Tipo: " + this.tipo + "  Título:  " + this.titulo + " \n Descripción: "
                + this.descripcion;
    }

}
