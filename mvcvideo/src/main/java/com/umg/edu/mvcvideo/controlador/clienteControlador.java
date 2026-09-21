package com.umg.edu.mvcvideo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.edu.mvcvideo.dao.ClienteDao;
import com.umg.edu.mvcvideo.modelo.Cliente;

@Controller
@RequestMapping("/clientes")
public class clienteControlador {

    private final ClienteDao clienteDAO = new ClienteDao();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDAO.listarTodos());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente(0, "", "", "", ""));
        return "clientes-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Cliente cliente = clienteDAO.buscarporid(id);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        boolean ok;

        if (cliente.getIdCliente() > 0) {
            ok = clienteDAO.actualizar(cliente);
        } else {
            ok = clienteDAO.insertar(cliente);
        }

        if (!ok) {
            ra.addFlashAttribute("error", "Error al momento de almacenar");
            return "redirect:/clientes";
        }

        ra.addFlashAttribute("success", "Cliente guardado correctamente");
        return "redirect:/clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        clienteDAO.eliminar(id);
        return "redirect:/clientes";
    }
}
