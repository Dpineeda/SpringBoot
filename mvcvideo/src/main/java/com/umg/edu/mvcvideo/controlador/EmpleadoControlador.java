package com.umg.edu.mvcvideo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.edu.mvcvideo.dao.EmpleadoDao;
import com.umg.edu.mvcvideo.modelo.Empleado;

@Controller
@RequestMapping("/empleados")
public class EmpleadoControlador {

    private final EmpleadoDao empleadoDAO = new EmpleadoDao();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoDAO.listarTodos());
        return "empleados";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Empleado empleado = empleadoDAO.buscarporid(id);
        if (empleado == null) {
            return "redirect:/empleados";
        }
        model.addAttribute("empleado", empleado);
        return "empleados-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Empleado empleado, RedirectAttributes ra) {
        boolean ok;

        if (empleado.getIdEmpleado() > 0) {
            ok = empleadoDAO.actualizar(empleado);
        } else {
            ok = empleadoDAO.insertar(empleado);
        }

        if (!ok) {
            ra.addFlashAttribute("error", "Error al momento de almacenar el empleado");
            return "redirect:/empleados";
        }

        ra.addFlashAttribute("success", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        empleadoDAO.eliminar(id);
        return "redirect:/empleados";
    }
}
