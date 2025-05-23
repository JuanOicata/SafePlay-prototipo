package Ucentral.SafePlay_ver01.controladores;

import Ucentral.SafePlay_ver01.persistencia.entidades.Usuario;
import Ucentral.SafePlay_ver01.persistencia.entidades.VideojuegoDTO;
import Ucentral.SafePlay_ver01.servicios.UsuarioServicio;
import Ucentral.SafePlay_ver01.servicios.VideojuegoServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;
    private final VideojuegoServicio videojuegoServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio, VideojuegoServicio videojuegoServicio) {
        this.usuarioServicio = usuarioServicio;
        this.videojuegoServicio = videojuegoServicio;
    }

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        // Crear un nuevo objeto Usuario y agregarlo al modelo
        Usuario usuario = new Usuario();
        model.addAttribute("elusuario", usuario);
        return "registro";
    }

    @PostMapping("/almacenar")
    public String almacenarUsuario(@ModelAttribute("elusuario") Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro"; // Nombre de la plantilla HTML
        }
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        return "login"; // Este es el HTML que ya tienes
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("username") String username,
                                @ModelAttribute("password") String password,
                                Model model,
                                HttpSession session) {
        Usuario usuario = usuarioServicio.validarUsuario(username, password);

        if (usuario != null) {
            System.out.println("Usuario autenticado: " + usuario.getNombre() + " con rol: " + usuario.getRol());

            session.setAttribute("usuarioLogueado", usuario);

            if ("supervisor".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/supervisor";
            } else if ("jugador".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/jugador";
            } else {
                model.addAttribute("error", "Rol de usuario desconocido.");
                return "login";
            }
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            return "login";
        }
    }

    @GetMapping("/jugador")
    public String mostrarPantallaJugador(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !"jugador".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }

        List<VideojuegoDTO> juegosAprobados = videojuegoServicio.obtenerVideojuegosAprobados();
        model.addAttribute("usuario", usuario);
        model.addAttribute("videojuegos", juegosAprobados);
        return "jugador";
    }

}