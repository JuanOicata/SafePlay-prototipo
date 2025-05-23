package Ucentral.SafePlay_ver01.persistencia.repositorio;

import Ucentral.SafePlay_ver01.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    Usuario findByUsuario(String usuario);

}