package Ucentral.SafePlay_ver01.persistencia.repositorio;

import Ucentral.SafePlay_ver01.persistencia.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepositorio extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(String usuarioId);
    boolean existsByUsuarioIdAndJuegoTitle(String usuarioId, String juegoTitle);
}
