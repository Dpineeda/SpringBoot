package com.umg.edu.mvcvideo.modelo;

import java.util.List;

import com.umg.edu.mvcvideo.dao.ClienteDao;
import com.umg.edu.mvcvideo.dao.EmpleadoDao;
import com.umg.edu.mvcvideo.dao.PuestoDao;

public class BaseDatos {

    public static void main(String[] args) {
        ClienteDao daoClientes = new ClienteDao();
        List<Cliente> cl = daoClientes.listarTodos();
        for (Cliente c : cl) {
            System.out.println(c.getNombre());
        }

        Cliente cl1 = daoClientes.buscarporid(1);
        if (cl1 != null) {
            cl1.setNombre("Alexander");
            if (daoClientes.actualizar(cl1)) {
                System.out.println("Cliente No. 1 actualizado correctamente");
            }
        }

        Cliente cl2 = daoClientes.buscarporid(2);
        if (cl2 != null) {
            cl2.setNombre("Juan Jose");
            if (daoClientes.actualizar(cl2)) {
                System.out.println("Cliente No.2 actualizado correctamente");
            }
        }

        EmpleadoDao daoEmpleados = new EmpleadoDao();
        List<Empleado> e1 = daoEmpleados.listarTodos();
        for (Empleado c : e1) {
            System.out.println(c.getNombre());
        }

        Empleado em1 = daoEmpleados.buscarporid(1);
        if (em1 != null) {
            em1.setNombre("Byron");
            if (daoEmpleados.actualizar(em1)) {
                System.out.println("Empleado No.1 actualizado correctamente");
            }
        }

        Empleado em2 = daoEmpleados.buscarporid(2);
        if (em2 != null) {
            em2.setNombre("Ernesto");
            if (daoEmpleados.actualizar(em2)) {
                System.out.println("Empleado No.2 actualizado correctamente");
            }
        }

        PuestoDao daoPuesto = new PuestoDao();
        List<Puesto> p1 = daoPuesto.listarTodos();
        for (Puesto c : p1) {
            System.out.println(c.getNombre());
        }

        Puesto pu1 = daoPuesto.buscarporid(1);
        if (pu1 != null) {
            pu1.setNombre("Ingeniero Civil");
            if (daoPuesto.actualizar(pu1)) {
                System.out.println("Puesto No.1 actualizado correctamente");
            }
        }

        Puesto pu2 = daoPuesto.buscarporid(2);
        if (pu2 != null) {
            pu2.setNombre("Electricista");
            if (daoPuesto.actualizar(pu2)) {
                System.out.println("Puesto No.2 actualizado correctamente ");
            }
        }
    }
}