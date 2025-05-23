package Ucentral.SafePlay_ver01.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;
    private String juegoTitle;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    public Recomendacion() {}

    public Recomendacion(String usuarioId, String juegoTitle, String mensaje) {
        this.usuarioId = usuarioId;
        this.juegoTitle = juegoTitle;
        this.mensaje = mensaje;
    }

    // Getters y setters


    public String getUsuarioId() { return usuarioId; }

    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getJuegoTitle() { return juegoTitle; }

    public void setJuegoTitle(String juegoTitle) { this.juegoTitle = juegoTitle; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

}
