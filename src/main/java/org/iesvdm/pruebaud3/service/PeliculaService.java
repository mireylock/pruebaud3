package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.PeliculaDAOImpl;
import org.iesvdm.pruebaud3.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeliculaService {
    @Autowired
    PeliculaDAOImpl peliculaDAO;

    public void createpelicula (Pelicula pelicula) {
        peliculaDAO.create(pelicula);
    }


    public List<Pelicula> listAll () {
        return peliculaDAO.getAll();
    }

    //Conteo de películas totales.
    //Coste de reemplazo de todas las películas de la categoría de horror (asume que el id de categoría está fijo y no cambia).
    //El número de películas por cada categoría.
    public int conteoPelis () {
        List<Pelicula> listaPeliculas = peliculaDAO.getAll();

        int conteoPelis = listaPeliculas.size();

        return conteoPelis;
    }



}
