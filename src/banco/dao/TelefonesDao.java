/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

import banco.entidade.Dadosgerais;
import banco.entidade.Telefones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Bruno
 */
public class TelefonesDao implements Dao<Telefones> {

    private static final String GET_BY_ID = "SELECT * FROM telefones WHERE idTelefone = ?";
    private static final String GET_ALL = "SELECT * FROM telefones";
    private static final String INSERT = "INSERT INTO telefones (telefone, inativo, idDadoGeral) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE telefones SET telefone = ?, inativo = ?, idDadoGeral = ?  WHERE idTelefone = ?";
    private static final String DELETE = "DELETE FROM telefones WHERE idTelefone = ?";

    public TelefonesDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS telefones"
                + "  (idTelefone                  INTEGER NOT NULL AUTO_INCREMENT,"
                + "   telefone                    VARCHAR(20),"
                + "   inativo                     TINYINT(4),"
                + "   idDadoGeral                 INTEGER,"
                + "   FOREIGN KEY (idDadoGeral) REFERENCES dadosgerais(idDadoGeral),"
                + "   PRIMARY KEY (idTelefone))";

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

    private Telefones getTelefoneFromRS(ResultSet rs) throws SQLException {
        Telefones telefone = new Telefones();

        telefone.setIdTelefone(rs.getInt("idTelefone"));
        telefone.setTelefone(rs.getString("telefone"));
        telefone.setInativo(rs.getBoolean("inativo"));
        telefone.setIdDadoGeral(new Dadosgerais(rs.getInt("idDadoGeral")));

        return telefone;
    }

    @Override
    public Telefones getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Telefones telefones = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                telefones = getTelefoneFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Telefone pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return telefones;
    }

    @Override
    public List<Telefones> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Telefones> telefones = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                telefones.add(getTelefoneFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os Telefones.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return telefones;
    }

    @Override
    public void insert(Telefones telefone) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, telefone.getTelefone());
            stmt.setBoolean(2, telefone.getInativo());
            stmt.setInt(3, telefone.getIdDadoGeral().getIdDadoGeral());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                telefone.setIdTelefone(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Telefone.", e);
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
            throw new RuntimeException("Erro ao remover Telefone.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Telefones telefone) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, telefone.getTelefone());
            stmt.setBoolean(2, telefone.getInativo());
            stmt.setInt(3, telefone.getIdDadoGeral().getIdDadoGeral());
            stmt.setInt(4, telefone.getIdTelefone());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Telefone.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    public TableModel getTabelaTelefones(int idDadoGeral) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Telefone");

        List<Telefones> lista = new ArrayList<>();
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM telefones t WHERE t.idDadoGeral = ? and t.inativo='false'");
            stmt.setInt(1, idDadoGeral);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(getTelefoneFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Telefones.", e);
        } finally {
            close(conn, stmt, rs);
        }

        for (Telefones telefone : lista) {

            Object[] linha = {
                telefone.getIdTelefone(),
                telefone.getTelefone()
            };

            modelo.addRow(linha);

        }
        return modelo;
    }
}
