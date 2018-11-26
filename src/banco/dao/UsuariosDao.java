/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

import banco.entidade.Dadosgerais;
import banco.entidade.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class UsuariosDao implements Dao<Usuarios>{

    private static final String GET_BY_ID = "SELECT * FROM usuarios WHERE idUsuario = ?";
    private static final String GET_ALL = "SELECT * FROM usuarios";
    private static final String INSERT = "INSERT INTO usuarios (nome, login, senha, cpf, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE usuarios SET nome = ?, login = ?, senha= ?, cpf = ?, idDadoGeral = ? WHERE idUsuario = ?";
    private static final String DELETE = "DELETE FROM usuarios WHERE idUsuario = ?";

    public UsuariosDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        final String sqlCreate = "CREATE TABLE IF NOT EXISTS usuarios"
                + "  (idUsuario           INTEGER NOT NULL AUTO_INCREMENT,"
                + "   nome                VARCHAR(50),"
                + "   login               VARCHAR(50),"
                + "   senha               VARCHAR(16),"
                + "   cpf                 VARCHAR(50),"
                + "   idDadoGeral         INTEGER,"
                + "   FOREIGN KEY (idDadoGeral) REFERENCES dadosgerais(idDadoGeral),"
                + "   PRIMARY KEY (idUsuario))";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
    }
    public int ChecarLogin(Usuarios usu) throws Exception{
        
        String sql="select * from usuarios u where u.login = '" + usu.getLogin() + 
                "' and u.senha = '" + usu.getSenha() + "'";
        
        java.sql.PreparedStatement sqlPrep = DbConnection.getConnection().prepareStatement(sql);
        ResultSet rs = sqlPrep.executeQuery();
        
        rs.first();
        
        if(rs.getRow() == 0){            
            return 0;
        }else{
            UsuarioLogado user = new UsuarioLogado();
            
            user.setIdusuario(rs.getInt("idUsuario"));
            user.setNome(rs.getString("nome"));
            return 1;
        }
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar recursos.", e);
        }

    }

    private Usuarios getUsuarioFromRS(ResultSet rs) throws SQLException {
        Usuarios usuario = new Usuarios();

        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setIdDadoGeral(new Dadosgerais(rs.getInt("idDadoGeral")));

        return usuario;
    }

    @Override
    public Usuarios getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Usuarios usuario = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = getUsuarioFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Usuário pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return usuario;
    }

    @Override
    public List<Usuarios> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Usuarios> usuario = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                usuario.add(getUsuarioFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os Usuários.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return usuario;
    }

    @Override
    public void insert(Usuarios usuario) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // private static final String INSERT = "INSERT INTO usuarios (nome, login, senha, cpf, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
        
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setInt(5, usuario.getIdDadoGeral().getIdDadoGeral());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Usuário.", e);
        } finally {
            close(conn, stmt, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover Usuário.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Usuarios usuario) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setInt(5, usuario.getIdDadoGeral().getIdDadoGeral());
            stmt.setInt(6, usuario.getIdUsuario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Usuário.", e);
        } finally {
            close(conn, stmt, null);
        }
    }
    
}
