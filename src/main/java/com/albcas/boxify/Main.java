package com.albcas.boxify;

import java.time.LocalDate;

public class Main {
    static void main() {

        Item libreta = new Item(Item.Size.SMALL, "Libreta de Mates", "");
        Item guitarra = new Item(Item.Size.BIG, "Fender Telecaster", "Linda");
        Item fotoVieja = new Item(Item.Size.TINY, "Foto de Eustaquio", "Está en blanco y negro. Es frágil.");

        guitarra.setPurchaseTime(LocalDate.now());
        guitarra.setWarrantyMonths(12);

        libreta.setWarrantyMonths(2);
        libreta.setPurchaseTime(LocalDate.of(2023, 8, 14));

        Inventory inventario = new Inventory();

        inventario.addItem(libreta);
        inventario.addItem(guitarra);
        inventario.addItem(fotoVieja);

        System.out.println(inventario.listItems());

        inventario.lookupItem(guitarra.getId());

        System.out.println(inventario.listItems());

        inventario.deleteItem(guitarra);

        System.out.println(inventario.listItems());



        System.out.println("Guitarra"+inventario.lookupItem(guitarra.getId()).getWarrantyExpirationDate()+" expira esa fecha ");
        System.out.println("Libreta"+inventario.lookupItem(libreta.getId()).getWarrantyExpirationDate()+" expira esa fecha ");

        System.out.println("Guitarra sigue en garantia? "+inventario.lookupItem(guitarra.getId()).warrantyIsInForce());
        System.out.println("Libreta sigue en garantia? "+inventario.lookupItem(libreta.getId()).warrantyIsInForce());


    }
}
