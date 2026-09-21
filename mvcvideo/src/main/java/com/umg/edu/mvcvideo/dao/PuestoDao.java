package com.umg.edu.mvcvideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.umg.edu.mvcvideo.config.ConexionDB;
import com.umg.edu.mvcvideo.modelo.Puesto;

public class PuestoDao implements CrudDao<Puesto> {

    @Override
    public boolean insertar(Puesto objeto) {
        String sql = "INSERT INTO PUESTO (id_puesto, nombre, salario_base) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, objeto.getIdPuesto());
            ps.setString(2, objeto.getNombre());
            ps.setDouble(3, objeto.getsalarioBase());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el puesto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Puesto objeto) {
        String sql = "UPDATE PUESTO SET id_puesto = ?, nombre = ?, salario_base = ? WHERE id_puesto = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, objeto.getIdPuesto());
            ps.setString(2, objeto.getNombre());
            ps.setDouble(3, objeto.getsalarioBase());
            ps.setInt(4, objeto.getIdPuesto());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el puesto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM PUESTO WHERE id_puesto = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el puesto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Puesto buscarporid(int id) {
        String sql = "SELECT id_puesto, nombre, salario_base FROM PUESTO WHERE id_puesto = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPuesto(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el Puesto");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Puesto> listarTodos() {
        List<Puesto> puestos = new ArrayList<>();
        String sql = "SELECT id_puesto, nombre, salario_base FROM PUESTO";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                puestos.add(mapearPuesto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los puestos");
            e.printStackTrace();
        }

        return puestos;
    }

    private Puesto mapearPuesto(ResultSet rs) throws SQLException {
        return new Puesto(
            rs.getInt("id_puesto"),
            rs.getString("nombre"),
            rs.getDouble("salario_base")
        );
    }
}
