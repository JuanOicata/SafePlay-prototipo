package Ucentral.SafePlay_ver01.persistencia.repositorio;

import Ucentral.SafePlay_ver01.persistencia.entidades.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionRepositorio extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByUsuarioId(String usuarioId);
    List<Evaluacion> findByJuegoTitle(String juegoTitle);

}
