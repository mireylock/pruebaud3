package org.iesvdm.pruebaud3.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    private int id_pelicula;
    @NotNull(message = "{msg.not.null}")
    @NotBlank(message="{msg.not.blank}")
    @Size(min=3, message="{msg.min}")
    private String titulo;

    @Size(max=300, message = "{msg.max}")
    private String descripcion;
    private LocalDate fecha_lanzamiento;
    private Idioma idioma;
    private int duracion_alquiler;

    @DecimalMin(value = "0", message = "{msg.min.value}")
    private double rental_rate;

    @Min(value = 0, message = "{msg.min.value}")
    private int duracion;

    @DecimalMax(value = "19.99", message = "{msg.max.value}")
    private double replacement_cost;
    private LocalDate ultima_actualizacion;

}
