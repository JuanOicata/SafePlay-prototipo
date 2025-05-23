package Ucentral.SafePlay_ver01.servicios;

import Ucentral.SafePlay_ver01.persistencia.entidades.Recomendacion;
import Ucentral.SafePlay_ver01.persistencia.repositorio.RecomendacionRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionServicio {
    private final RecomendacionRepositorio recomendacionRepositorio;

    public RecomendacionServicio(RecomendacionRepositorio recomendacionRepositorio) {
        this.recomendacionRepositorio = recomendacionRepositorio;
    }

    public void guardar(String usuarioId, String juegoTitle, String mensaje) {
        recomendacionRepositorio.save(new Recomendacion(usuarioId, juegoTitle, mensaje));
    }

    public List<Recomendacion> obtenerPorUsuario(String usuarioId) {
        return recomendacionRepositorio.findByUsuarioId(usuarioId);
    }
}
