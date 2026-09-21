package com.umg.edu.mvcvideo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.umg.edu.mvcvideo.config.ConexionDB;
import com.umg.edu.mvcvideo.modelo.Empleado;

public class EmpleadoDao implements CrudDao<Empleado> {

    @Override
    public boolean insertar(Empleado objeto) {
        String sql = "INSERT INTO empleados (nombre, apellidos, fecha_contratacion, id_puesto) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getApellidos());
            ps.setDate(3, Date.valueOf(objeto.getFechaContratacion()));
            ps.setInt(4, objeto.getIdPuesto());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el empleado");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Empleado objeto) {
        String sql = "UPDATE empleados SET nombre = ?, apellidos = ?, fecha_contratacion = ?, id_puesto = ? WHERE id_empleados = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getApellidos());
            ps.setDate(3, Date.valueOf(objeto.getFechaContratacion()));
            ps.setInt(4, objeto.getIdPuesto());
            ps.setInt(5, objeto.getIdEmpleado());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el empleado");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id_empleados = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el empleado");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Empleado buscarporid(int id) {
        String sql = "SELECT id_empleados, nombre, apellidos, fecha_contratacion, id_puesto FROM empleados WHERE id_empleados = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEmpleado(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el empleado");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT id_empleados, nombre, apellidos, fecha_contratacion, id_puesto FROM empleados";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                empleados.add(mapearEmpleado(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los empleados");
            e.printStackTrace();
        }

        return empleados;
    }

    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
            rs.getInt("id_empleados"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getDate("fecha_contratacion").toLocalDate(),
            rs.getInt("id_puesto")
        );
    }
}