package com.albcas.boxify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventario {
    private List<Articulo> articulos;

    public Inventario() {
        this.articulos = new ArrayList<Articulo>();
    }

    // Métodos

    public void anyadirArticulo(Articulo articulo){
        this.articulos.add(articulo);
        System.out.println("Añadido: " + articulo.getName());
    }

    public void eliminarArticulo(Articulo articulo) {
        if (this.articulos.remove(articulo)) {
            System.out.println("Eliminado: " + articulo.getName());
        } else {
            System.out.println("Artículo no encontrado: " + articulo.getName());
        }
    }

    public Articulo buscarArticulo(UUID id){
        for (Articulo articulo : articulos) {
            if (articulo.getId().equals(id)){ // Comprobar cada ID
                return articulo;
            }
        }
        System.out.println("Artículo no encontrado. ID: " + id);
        return null;
    }

    public String listarArticulos(){
        StringBuilder listaArticulos = new StringBuilder();

        for (Articulo articulo : articulos) {
            listaArticulos.append(articulo.getName()).append("\n");
        }

        return listaArticulos.toString();
    }
}