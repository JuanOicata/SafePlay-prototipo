package Ucentral.SafePlay_ver01.servicios;

import Ucentral.SafePlay_ver01.persistencia.entidades.Evaluacion;
import Ucentral.SafePlay_ver01.persistencia.repositorio.EvaluacionRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionServicio {

    private final EvaluacionRepositorio evaluacionRepositorio;

    public EvaluacionServicio(EvaluacionRepositorio evaluacionRepositorio) {
        this.evaluacionRepositorio = evaluacionRepositorio;
    }

    public void guardarEvaluacion(Evaluacion evaluacion) {
        evaluacionRepositorio.save(evaluacion);
    }

    public List<Evaluacion> obtenerPorUsuario(String usuarioId) {
        return evaluacionRepositorio.findByUsuarioId(usuarioId);
    }

    public List<Evaluacion> obtenerTodas() {
        return evaluacionRepositorio.findAll();
    }

    public List<Evaluacion> obtenerPorJuego(String juegoTitle) {
        return evaluacionRepositorio.findByJuegoTitle(juegoTitle);
    }



}
