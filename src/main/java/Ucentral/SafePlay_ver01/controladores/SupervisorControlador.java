package Ucentral.SafePlay_ver01.controladores;

import Ucentral.SafePlay_ver01.persistencia.entidades.Evaluacion;
import Ucentral.SafePlay_ver01.persistencia.entidades.Usuario;
import Ucentral.SafePlay_ver01.persistencia.entidades.Videojuego;
import Ucentral.SafePlay_ver01.persistencia.entidades.VideojuegoDTO;
import Ucentral.SafePlay_ver01.persistencia.repositorio.VideojuegoRepositorio;
import Ucentral.SafePlay_ver01.servicios.EvaluacionServicio;
import Ucentral.SafePlay_ver01.servicios.RecomendacionServicio;
import Ucentral.SafePlay_ver01.servicios.VideojuegoServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/supervisor")
public class SupervisorControlador {

    @Autowired
    private VideojuegoServicio videojuegoServicio;

    @Autowired
    private VideojuegoRepositorio videojuegoRepositorio;

    @Autowired
    private EvaluacionServicio evaluacionServicio;

    @Autowired
    private RecomendacionServicio recomendacionServicio;

    @GetMapping
    public String mostrarVideojuegos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !"supervisor".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        List<VideojuegoDTO> videojuegos = videojuegoServicio.obtenerVideojuegos();
        // Aplicar estados de aprobación desde la base de datos
        for (VideojuegoDTO juego : videojuegos) {
            Videojuego entidad = videojuegoRepositorio.findById(juego.getTitle()).orElse(new Videojuego());
            juego.setApproved(entidad.isApproved());
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("videojuegos", videojuegos);
        return "supervisor";
    }

    @PostMapping("/aprobar")
    public String aprobarJuego(@RequestParam("title") String title, @RequestParam("isApproved") boolean isApproved) {
        // Guardar el estado de aprobación en la base de datos
        Videojuego videojuego = videojuegoRepositorio.findById(title).orElse(new Videojuego());
        videojuego.setTitle(title);
        videojuego.setApproved(isApproved);
        videojuegoRepositorio.save(videojuego);
        return "redirect:/supervisor";
    }

    @GetMapping("/evaluaciones")
    public String verEvaluacionesPorJuego(@RequestParam(required = false) String title,
                                          HttpSession session,
                                          Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !"supervisor".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        List<Evaluacion> evaluaciones;
        if (title != null && !title.isEmpty()) {
            evaluaciones = evaluacionServicio.obtenerPorJuego(title);
            model.addAttribute("filtroJuego", title);
        } else {
            evaluaciones = evaluacionServicio.obtenerTodas();
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("evaluaciones", evaluaciones);
        return "supervisor-evaluaciones"; // este HTML debe existir
    }


    @PostMapping("/recomendar")
    public String enviarRecomendacion(@RequestParam String usuarioId,
                                      @RequestParam String juegoTitle,
                                      @RequestParam String mensaje) {
        recomendacionServicio.guardar(usuarioId, juegoTitle, mensaje);
        return "redirect:/supervisor/evaluaciones";
    }

}