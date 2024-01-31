package org.iesvdm.pruebaud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebaud3.domain.Idioma;
import org.iesvdm.pruebaud3.domain.Pelicula;
import org.iesvdm.pruebaud3.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping("/peliculas")
    public String listar (Model model) {
        List<Pelicula> listAllPeliculas = peliculaService.listAll();
        model.addAttribute("listaPeliculas", listAllPeliculas);

        int conteoPelis = peliculaService.conteoPelis();
        model.addAttribute("conteoPelis", conteoPelis);

        return "peliculas";
    }

    @GetMapping("/peliculas/crear")
    public String crear (Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);

        Idioma idioma = new Idioma();
        model.addAttribute("idioma", idioma);

        return "crear-pelicula";
    }

    @PostMapping("/peliculas/crear")
    public String submitCrear (@Valid @ModelAttribute("pelicula") Pelicula pelicula, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pelicula", pelicula);
            return "crear-pelicula";
        }
        peliculaService.createpelicula(pelicula);
        List<Pelicula> listaPeliculas = peliculaService.listAll();
        model.addAttribute("listaPeliculas", listaPeliculas);

        return "/peliculas";
    }

}
