package Ucentral.SafePlay_ver01.persistencia.repositorio;

import Ucentral.SafePlay_ver01.persistencia.entidades.Evaluacion;
import Ucentral.SafePlay_ver01.persistencia.entidades.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecomendacionRepositorio extends JpaRepository<Recomendacion, Long> {
    List<Recomendacion> findByUsuarioId(String usuarioId);

}
