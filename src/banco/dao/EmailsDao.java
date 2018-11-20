/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

import banco.entidade.Dadosgerais;
import banco.entidade.Emails;
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
public class EmailsDao implements Dao<Emails>{

    private static final String GET_BY_ID = "SELECT * FROM emails WHERE idEmail = ?";
    private static final String GET_ALL = "SELECT * FROM emails";
    private static final String INSERT = "INSERT INTO emails (email, inativo, idDadoGeral) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE emails SET email = ?, inativo = ?, idDadoGeral = ?,  WHERE idEmail = ?";
    private static final String DELETE = "DELETE FROM emails WHERE idEmail = ?";

    public EmailsDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS emails"
                + "  (idEmail                     INTEGER,"
                + "   email                    VARCHAR(20),"
                + "   inativo                     TINYINT(4),"
                + "   FOREIGN KEY (idDadoGeral) REFERENCES dadosgerais(idDadoGeral),"
                + "   PRIMARY KEY (idEmail))";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
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

    private Emails getEmailFromRS(ResultSet rs) throws SQLException {
        Emails email = new Emails();

        email.setIdEmail(rs.getInt("idEmail"));
        email.setEmail(rs.getString("email"));
        email.setInativo(rs.getBoolean("inativo"));
        email.setIdDadoGeral(new Dadosgerais(rs.getInt("idDadoGeral")));

        return email;
    }

    @Override
    public Emails getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Emails emails = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                emails = getEmailFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Email pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return emails;
    }

    @Override
    public List<Emails> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Emails> emails = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                emails.add(getEmailFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os Emails.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return emails;
    }

    @Override
    public void insert(Emails email) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // private static final String INSERT = "INSERT INTO usuarios (nome, login, senha, cpf, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
        
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, email.getEmail());
            stmt.setBoolean(2, email.getInativo());
            stmt.setInt(3, email.getIdDadoGeral().getIdDadoGeral());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                email.setIdEmail(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Email.", e);
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
            throw new RuntimeException("Erro ao remover Email.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Emails email) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, email.getEmail());
            stmt.setBoolean(2, email.getInativo());
            stmt.setInt(3, email.getIdDadoGeral().getIdDadoGeral());
            stmt.setInt(4, email.getIdEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Email.", e);
        } finally {
            close(conn, stmt, null);
        }
    }
    
}
