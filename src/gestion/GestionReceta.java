package gestion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.UUID;

import dto.Receta;
import dto.TipoReceta;

import util.ArchivoUtil;

public class GestionReceta {
    private List<Receta> recetas;
    private String rutaArchivo= "recetas.txt";

    public GestionReceta(){
        recetas = ArchivoUtil.leerRecetas(rutaArchivo);
        if (recetas == null || recetas.isEmpty()) {
            recetas = new ArrayList<>();
            agregarRecetasPorDefecto();
            ArchivoUtil.guardarRecetas(recetas, rutaArchivo);
        }
    }

    private void agregarRecetasPorDefecto() {
        recetas.add(new Receta(
                UUID.randomUUID().toString(),
                "Arroz con leche",
                TipoReceta.POSTRE,
                Arrays.asList("Arroz", "Leche", "Azúcar", "Canela"),
                Arrays.asList("Hervir el arroz", "Agregar leche y azúcar", "Cocinar a fuego lento", "Servir con canela"),
                30,
                null,
                false
        ));

        recetas.add(new Receta(
                UUID.randomUUID().toString(),
                "Huevos revueltos",
                TipoReceta.DESAYUNO,
                Arrays.asList("Huevos", "Sal", "Aceite"),
                Arrays.asList("Batir los huevos", "Calentar el aceite", "Cocinar los huevos", "Agregar sal"),
                10,
                null,
                false
        ));

        recetas.add(new Receta(
                UUID.randomUUID().toString(),
                "Pollo al horno",
                TipoReceta.ALMUERZO,
                Arrays.asList("Pollo", "Papas", "Sal", "Especias"),
                Arrays.asList("Preparar el pollo", "Colocar en bandeja", "Agregar papas y especias", "Hornear"),
                60,
                null,
                false
        ));
    }

    public void agregarReceta(Receta receta){
        recetas.add(receta);
        ArchivoUtil.guardarRecetas(recetas, rutaArchivo);
    }
    public List<Receta> obtenerReceta(){
        return new ArrayList<>(recetas);
    }
    public List<Receta> buscarPorNombre(String nombre){
        return recetas.stream()
                .filter(r->r.obtenerNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Receta> filtrarPorTipo(TipoReceta tipoReceta){
        return recetas.stream()
                .filter(r->r.obtenerTipo() == tipoReceta)
                .collect(Collectors.toList());

    }
    public void eliminarReceta(String id){
        recetas.removeIf(r->r.ObtenerId().equals(id));
        ArchivoUtil.guardarRecetas(recetas, rutaArchivo);

    }
    public void marcarFavorita(String id){
        for (Receta r: recetas){
            if(r.ObtenerId().equals(id)){
                r.modificarEsFavorita(true);
                ArchivoUtil.guardarRecetas(recetas, rutaArchivo);
                break;
            }
        }

    }
    public List<Receta> obtenerFavoritas(){
        return recetas.stream()
                .filter(r -> Boolean.TRUE.equals(r.obtenerEsFavorita()))
                .collect(Collectors.toList());

    }
    public void desmarcarFavorita(String id){
        for (Receta r : recetas){
            if (r.ObtenerId().equals(id)){
                r.modificarEsFavorita(false);
                ArchivoUtil.guardarRecetas(recetas, rutaArchivo);
                break;

            }
        }

    }
}

