package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Usuario;

public class UsuarioDAO {
    
    public void cadastrarUsuario(Usuario usuario){

        String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, EMAIL) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletarUsuario(int id){
        String sql = "DELETE FROM USUARIO WHERE IDUSUARIO=?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscaUsuario(int id){
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO=?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setCodigo(rs.getInt("IDUSUARIO"));
                    usuario.setNome(rs.getString("NOME"));
                    usuario.setLogin(rs.getString("LOGIN"));
                    usuario.setSenha(rs.getString("SENHA"));
                    usuario.setEmail(rs.getString("EMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;

    }

    public Usuario alteraUsuario(int id){
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO=?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setCodigo(rs.getInt("IDUSUARIO"));
                    usuario.setNome(rs.getString("NOME"));
                    usuario.setLogin(rs.getString("LOGIN"));
                    usuario.setSenha(rs.getString("SENHA"));
                    usuario.setEmail(rs.getString("EMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIO SET NOME=?, LOGIN=?, SENHA=?, EMAIL=? WHERE IDUSUARIO=?";
    
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
