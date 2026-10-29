package com.albcas.boxify.persistencia;

import com.albcas.boxify.Articulo;
import com.albcas.boxify.Inventario;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersistenciaJson {
    // Ruta del archivo "/data/inventario.json"
    private final Path archivo = Path.of("data", "inventario.json");
    // Libreria Jackson
    private final JsonMapper mapper = JsonMapper.builder().build();

    // Cargar los datos con manejo de excepciones
    public List<Articulo> cargar() throws IOException {
        if (Files.notExists(archivo)) {
            // Si no existe, se devuelve una lista vacía inmutable
            return List.of();
        }

        // Se lee el json, deserializa en una Lista, y la devuelve
        return mapper.readValue(
                archivo.toFile(),
                new TypeReference<List<Articulo>>() {
                }
        );
    }

    // Guardado del inventario en una carpeta superior a la ruta de archivo, o la crea previamente
    public void guardar(Inventario inventario) throws IOException {
        Files.createDirectories(archivo.getParent());

        // formato pretty de JSON indentado
        mapper.writerWithDefaultPrettyPrinter()
                // se serializan los datos
                .writeValue(archivo.toFile(), inventario.getArticulos());
    }

}
