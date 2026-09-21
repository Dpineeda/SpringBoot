package com.umg.edu.mvcvideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.umg.edu.mvcvideo.config.ConexionDB;
import com.umg.edu.mvcvideo.modelo.Cliente;

public class ClienteDao implements CrudDao<Cliente> {

    @Override
    public boolean insertar(Cliente objeto) {
        String sql = "INSERT INTO CLIENTES (nit, nombre, apellidos, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNit());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidos());
            ps.setString(4, objeto.getEmail());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Cliente objeto) {
        String sql = "UPDATE CLIENTES SET nit = ?, nombre = ?, apellidos = ?, email = ? WHERE id_clientes = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNit());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidos());
            ps.setString(4, objeto.getEmail());
            ps.setInt(5, objeto.getIdCliente());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CLIENTES WHERE id_clientes = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cliente buscarporid(int id) {
        String sql = "SELECT id_clientes, nit, nombre, apellidos, email FROM CLIENTES WHERE id_clientes = ?";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el cliente");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id_clientes, nit, nombre, apellidos, email FROM clientes";

        try (Connection conn = ConexionDB.IniciarConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los clientes");
            e.printStackTrace();
        }

        return clientes;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_clientes"),
            rs.getString("nit"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("email")
        );
    }
}
