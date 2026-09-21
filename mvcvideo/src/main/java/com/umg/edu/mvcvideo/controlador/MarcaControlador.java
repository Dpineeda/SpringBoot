package com.umg.edu.mvcvideo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.edu.mvcvideo.dao.MarcaDao;
import com.umg.edu.mvcvideo.modelo.Marca;

@Controller
@RequestMapping("/marcas")
public class MarcaControlador {

    private final MarcaDao marcaDAO = new MarcaDao();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaDAO.listarTodos());
        return "marcas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("marca", new Marca());
        return "marcas-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Marca marca = marcaDAO.buscarporid(id);
        if (marca == null) {
            return "redirect:/marcas";
        }
        model.addAttribute("marca", marca);
        return "marcas-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Marca marca, RedirectAttributes ra) {
        boolean ok;

        if (marca.getIdMarca() > 0) {
            ok = marcaDAO.actualizar(marca);
        } else {
            ok = marcaDAO.insertar(marca);
        }

        if (!ok) {
            ra.addFlashAttribute("error", "Error al momento de almacenar la marca");
            return "redirect:/marcas";
        }

        ra.addFlashAttribute("success", "Marca guardada correctamente");
        return "redirect:/marcas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        marcaDAO.eliminar(id);
        return "redirect:/marcas";
    }
}
