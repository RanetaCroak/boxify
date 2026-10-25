package com.albcas.boxify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Articulo {

    private Tamanyo tamanyo;
    private UUID id;
    private String nombre;
    private String nota;
    private String categoria;
    private LocalDate fechaCompra;
    private BigDecimal precioCompra;
    private int mesesGarantia;

    // Constructor
    public Articulo(Articulo.Tamanyo tamanyo, String nombre, String nota) {
        this.tamanyo = tamanyo;
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.nota = nota;
    }

    public enum Tamanyo {
        PEQUEÑO, // Llaves, memorias USB, tarjetas.
        MEDIANO, // Carteras, bolsos, estuches.
        GRANDE,  // Monitores, altavoces, teclados.
        ENORME,  // Ordenadores, objetos de formas inusuales.
        VOLUMINOSO // Extra
    };

    // Getters y Setters

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }

    public Tamanyo getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(Tamanyo tamanyo) {
        this.tamanyo = tamanyo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    // Métodos
    public LocalDate getFechaVencimientoGarantia(){
        if (this.getFechaCompra() == null || mesesGarantia <= 0) {
            return null;
        }
        return this.getFechaCompra().plusMonths(mesesGarantia);
    }

    public boolean garantiaEnVigor(){
        LocalDate fechaVencimiento = this.getFechaVencimientoGarantia();
        if (fechaVencimiento == null) {
            return false;
        }
        return fechaVencimiento.isAfter(LocalDate.now());
    }

}