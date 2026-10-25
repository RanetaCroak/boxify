package com.albcas.boxify;

import java.time.LocalDate;

public class Main {
    static void main() {

        Articulo libreta = new Articulo(Articulo.Tamanyo.PEQUEÑO, "Libreta de Mates", "");
        Articulo guitarra = new Articulo(Articulo.Tamanyo.GRANDE, "Fender Telecaster", "Linda");
        Articulo fotoVieja = new Articulo(Articulo.Tamanyo.PEQUEÑO, "Foto de Eustaquio", "Está en blanco y negro. Es frágil.");

        guitarra.setFechaCompra(LocalDate.now());
        guitarra.setMesesGarantia(12);

        libreta.setMesesGarantia(2);
        libreta.setFechaCompra(LocalDate.of(2023, 8, 14));

        Inventario inventario = new Inventario();

        inventario.anyadirArticulo(libreta);
        inventario.anyadirArticulo(guitarra);
        inventario.anyadirArticulo(fotoVieja);

        System.out.println(inventario.listarArticulos());

        inventario.buscarArticulo(guitarra.getId());

        System.out.println(inventario.listarArticulos());

        inventario.eliminarArticulo(guitarra);

        System.out.println(inventario.listarArticulos());



        System.out.println("Guitarra" + inventario.buscarArticulo(guitarra.getId()).getFechaVencimientoGarantia() + " expira esa fecha ");
        System.out.println("Libreta" + inventario.buscarArticulo(libreta.getId()).getFechaVencimientoGarantia() + " expira esa fecha ");

        System.out.println("Guitarra sigue en garantia? " + inventario.buscarArticulo(guitarra.getId()).garantiaEnVigor());
        System.out.println("Libreta sigue en garantia? " + inventario.buscarArticulo(libreta.getId()).garantiaEnVigor());

    }
}