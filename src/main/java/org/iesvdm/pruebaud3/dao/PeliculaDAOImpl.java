package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.domain.Idioma;
import org.iesvdm.pruebaud3.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaDAOImpl implements PeliculaDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Pelicula pelicula) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO pelicula
                        (titulo, descripcion, fecha_lanzamiento, id_idioma, duracion_alquiler, rental_rate, duracion, replacement_cost, ultima_actualizacion)
                        VALUE
                        (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, pelicula.getTitulo());
            ps.setString(idx++, pelicula.getDescripcion());
            ps.setDate(idx++, Date.valueOf(pelicula.getFecha_lanzamiento()));
            ps.setInt(idx++, pelicula.getIdioma().getId_idioma());
            ps.setInt(idx++, pelicula.getDuracion_alquiler());
            ps.setDouble(idx++, pelicula.getRental_rate());
            ps.setInt(idx++, pelicula.getDuracion());
            ps.setDouble(idx++, pelicula.getReplacement_cost());
            ps.setDate(idx++, Date.valueOf(pelicula.getUltima_actualizacion()));

            return ps;
        }, keyHolder);

        pelicula.setId_pelicula(keyHolder.getKey().intValue());

    }

    @Override
    public List<Pelicula> getAll() {
        List<Pelicula> listPelicula = this.jdbcTemplate.query("""
                SELECT * FROM  pelicula P left join idioma I on  P.id_idioma = I.id_idioma
                """, (rs, rowNum) -> new Pelicula (rs.getInt("P.id_pelicula"),
                                    rs.getString("P.titulo"),
                rs.getString("P.descripcion"),
                rs.getDate("P.fecha_lanzamiento").toLocalDate(),
                new Idioma(rs.getInt("I.id_idioma"),
                            rs.getString("nombre"),
                            rs.getDate("I.ultima_actualizacion").toLocalDate()
                        ),
                rs.getInt("P.duracion_alquiler"),
                rs.getDouble("P.rental_rate"),
                rs.getInt("P.duracion"),
                rs.getDouble("P.replacement_cost"),
                rs.getDate("P.ultima_actualizacion").toLocalDate()
        ));

        return listPelicula;
    }

    @Override
    public Optional<Pelicula> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(int id) {

    }
}
