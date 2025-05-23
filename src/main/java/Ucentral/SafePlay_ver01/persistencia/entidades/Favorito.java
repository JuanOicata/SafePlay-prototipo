package Ucentral.SafePlay_ver01.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId; // Guarda el nombre de usuario o ID, según cómo los manejas

    @Column(name = "juego_title", nullable = false)
    private String juegoTitle;

    public Favorito() {
    }

    public Favorito(String usuarioId, String juegoTitle) {
        this.usuarioId = usuarioId;
        this.juegoTitle = juegoTitle;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getJuegoTitle() {
        return juegoTitle;
    }

    public void setJuegoTitle(String juegoTitle) {
        this.juegoTitle = juegoTitle;
    }
}
