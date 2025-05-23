package Ucentral.SafePlay_ver01.persistencia.repositorio;

import Ucentral.SafePlay_ver01.persistencia.entidades.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoRepositorio extends JpaRepository<Videojuego, String> {
}