package com.albcas.boxify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventario {
    private final List<Articulo> articulos;

    public Inventario() {
        this.articulos = new ArrayList<Articulo>();
    }

    // Métodos

    public void anadirArticulo(Articulo articulo){
        this.articulos.add(articulo);
        System.out.println("Añadido: " + articulo.getNombre());
    }

    public void eliminarArticulo(Articulo articulo) {
        if (this.articulos.remove(articulo)) {
            System.out.println("Eliminado: " + articulo.getNombre());
        } else {
            System.out.println("Artículo no encontrado: " + articulo.getNombre());
        }
    }

    // Get lista artículos
    public List<Articulo> getArticulos() {
        return articulos;
    }

    // Busca por ID y retorna el artículo, o null
    public Articulo buscarArticulo(UUID id){
        for (Articulo articulo : articulos) {
            if (articulo.getId().equals(id)){ // Comprobar cada ID
                return articulo;
            }
        }
        System.out.println("Artículo no encontrado. ID: " + id);
        return null;
    }

    // Genera una lista de los artículos por nombre
    public String listarArticulos(){
        StringBuilder listaArticulos = new StringBuilder();
        int cont = 0;
        for (Articulo articulo : articulos) {
            listaArticulos.append(cont++).append(" - ").append(articulo.getNombre()).append("\n");
        }

        return listaArticulos.toString();
    }
}