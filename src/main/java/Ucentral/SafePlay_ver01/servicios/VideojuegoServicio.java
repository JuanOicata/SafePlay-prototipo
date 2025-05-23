package Ucentral.SafePlay_ver01.servicios;

import Ucentral.SafePlay_ver01.persistencia.entidades.Videojuego;
import Ucentral.SafePlay_ver01.persistencia.entidades.VideojuegoDTO;
import Ucentral.SafePlay_ver01.persistencia.repositorio.VideojuegoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class VideojuegoServicio {

    private final VideojuegoRepositorio videojuegoRepositorio;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.freetogame.com/api/games";

    public VideojuegoServicio(VideojuegoRepositorio videojuegoRepositorio) {
        this.videojuegoRepositorio = videojuegoRepositorio;
    }
    public List<VideojuegoDTO> obtenerVideojuegos() {
        try {
            ResponseEntity<VideojuegoDTO[]> response = restTemplate.getForEntity(API_URL, VideojuegoDTO[].class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Arrays.asList(response.getBody());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error al obtener videojuegos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<VideojuegoDTO> obtenerVideojuegosAprobados() {
        List<VideojuegoDTO> todos = obtenerVideojuegos();
        return todos.stream()
                .filter(juego -> {
                    Videojuego entidad = videojuegoRepositorio.findById(juego.getTitle()).orElse(null);
                    return entidad != null && entidad.isApproved();
                })
                .toList();
    }


}