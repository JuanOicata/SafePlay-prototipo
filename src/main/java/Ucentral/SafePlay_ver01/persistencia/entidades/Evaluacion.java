package Ucentral.SafePlay_ver01.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId;

    @Column(name = "juego_title", nullable = false)
    private String juegoTitle;

    @Column(nullable = false)
    private String emocion;

    @Column(nullable = false)
    private boolean violento;

    @Column(name = "tipos_violencia")
    private String tiposViolencia; // Guarda texto tipo: "física, verbal"

    @Column(name = "nivel_violencia")
    private String nivelViolencia;

    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;


    public Evaluacion() {}

    public Evaluacion(String usuarioId, String juegoTitle, String emocion, boolean violento) {
        this.usuarioId = usuarioId;
        this.juegoTitle = juegoTitle;
        this.emocion = emocion;
        this.violento = violento;
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

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public boolean isViolento() {
        return violento;
    }

    public void setViolento(boolean violento) {
        this.violento = violento;
    }

    public String getTiposViolencia() {
        return tiposViolencia;
    }

    public void setTiposViolencia(String tiposViolencia) {
        this.tiposViolencia = tiposViolencia;
    }

    public String getNivelViolencia() {return nivelViolencia; }

    public void setNivelViolencia(String nivelViolencia) {this.nivelViolencia = nivelViolencia; }

    public String getComentarios() {return comentarios; }

    public void setComentarios(String comentarios) {this.comentarios = comentarios; }
}
