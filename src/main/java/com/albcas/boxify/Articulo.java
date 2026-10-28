package com.albcas.boxify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Articulo {

    private Tamano tamano;
    private UUID id;
    private String nombre;
    private String nota;
    private String categoria;
    private LocalDate fechaCompra;
    private BigDecimal precioCompra;
    private int mesesGarantia;

    // Constructor para artículos nuevos
    public Articulo(Articulo.Tamano tamano, String nombre, String nota) {
        this.tamano = tamano;
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.nota = nota;
    }

    // Constructor vacío para Jackson
    public Articulo() {
    }

    public enum Tamano {
        PEQUENO, // Llaves, memorias USB, tarjetas.
        MEDIANO, // Carteras, bolsos, estuches.
        GRANDE,  // Monitores, altavoces, teclados.
        ENORME,  // Ordenadores, objetos de formas inusuales.
        VOLUMINOSO // Extra
    }

    ;

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

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public LocalDate getFechaVencimientoGarantia() {
        if (this.getFechaCompra() == null || mesesGarantia <= 0) {
            return null;
        }
        return this.getFechaCompra().plusMonths(mesesGarantia);
    }

    public boolean garantiaEnVigor() {
        LocalDate fechaVencimiento = this.getFechaVencimientoGarantia();
        if (fechaVencimiento == null) {
            return false;
        }
        return fechaVencimiento.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getNombre() +
                " Tamaño: " + this.getTamano() +
                " Categoría: " + this.categoria +
                "\nFecha de compra: " + this.fechaCompra +
                " Precio de compra: " + this.precioCompra + "€" +
                " Meses de garantía: " + this.mesesGarantia +
                "\nInfo garantía: " +
                " Caduca el " + this.getFechaVencimientoGarantia() +
                " ¿Garantía en vigor? -> " + this.garantiaEnVigor()+ "\n";
    }
}