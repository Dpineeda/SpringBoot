package com.umg.edu.mvcvideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.umg.edu.mvcvideo.config.ConexionDB;
import com.umg.edu.mvcvideo.modelo.Marca;

public class MarcaDao implements CrudDao<Marca> {

    @Override
    public boolean insertar(Marca objeto) {
        String sql = "INSERT INTO marcas (nombre) VALUES (?)";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar la marca");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Marca objeto) {
        String sql = "UPDATE marcas SET nombre = ? WHERE id_marcas = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setInt(2, objeto.getIdMarca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar la marca");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM marcas WHERE id_marcas = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la marca");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Marca buscarporid(int id) {
        String sql = "SELECT id_marcas, nombre FROM marcas WHERE id_marcas = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearMarca(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la marca");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Marca> listarTodos() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT id_marcas, nombre FROM marcas";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                marcas.add(mapearMarca(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las marcas");
            e.printStackTrace();
        }

        return marcas;
    }

    private Marca mapearMarca(ResultSet rs) throws SQLException {
        return new Marca(
            rs.getInt("id_marcas"),
            rs.getString("nombre")
        );
    }
}
