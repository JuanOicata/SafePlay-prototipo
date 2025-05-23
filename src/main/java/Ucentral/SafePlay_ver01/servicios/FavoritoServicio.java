package Ucentral.SafePlay_ver01.servicios;

import Ucentral.SafePlay_ver01.persistencia.entidades.Favorito;
import Ucentral.SafePlay_ver01.persistencia.repositorio.FavoritoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServicio {

    private final FavoritoRepositorio favoritoRepositorio;

    public FavoritoServicio(FavoritoRepositorio favoritoRepositorio) {
        this.favoritoRepositorio = favoritoRepositorio;
    }

    public void guardarFavorito(String usuarioId, String juegoTitle) {
        if (!favoritoRepositorio.existsByUsuarioIdAndJuegoTitle(usuarioId, juegoTitle)) {
            favoritoRepositorio.save(new Favorito(usuarioId, juegoTitle));
        }
    }

    public List<Favorito> obtenerFavoritosDeUsuario(String usuarioId) {
        return favoritoRepositorio.findByUsuarioId(usuarioId);
    }
    public void eliminarFavorito(String usuarioId, String juegoTitle) {
        favoritoRepositorio.findByUsuarioId(usuarioId).stream()
                .filter(f -> f.getJuegoTitle().equals(juegoTitle))
                .findFirst()
                .ifPresent(favoritoRepositorio::delete);
    }

}
