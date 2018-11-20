/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

import banco.entidade.Dadosgerais;
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
public class DadosGeraisDao implements Dao<Dadosgerais> {

    private static final String GET_BY_ID = "SELECT * FROM dadosgerais WHERE idDadoGeral = ?";
    private static final String GET_ALL = "SELECT * FROM dadosgerais";
    private static final String INSERT = "INSERT INTO dadosgerais (inativo, pertenece_a_classe) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE dadosgerais SET inativo = ?, pertenece_a_classe = ? WHERE idDadoGeral = ?";
    private static final String DELETE = "DELETE FROM dadosgerais WHERE idDadoGeral = ?";

    public DadosGeraisDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS dadosgerais"
                + "  (idDadoGeral           INTEGER,"
                + "   inativo               TINYINT(4),"
                + "   pertenece_a_classe    VARCHAR(50),"
                + "   PRIMARY KEY (idDadoGeral))";

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

    private Dadosgerais getClienteFromRS(ResultSet rs) throws SQLException {
        Dadosgerais dadosGerais = new Dadosgerais();

        dadosGerais.setIdDadoGeral(rs.getInt("idDadoGeral"));
        dadosGerais.setInativo(rs.getBoolean("inativo"));
        dadosGerais.setPerteneceAClasse(rs.getString("pertenece_a_classe"));

        return dadosGerais;
    }

    @Override
    public Dadosgerais getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Dadosgerais dadoGeral = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                dadoGeral = getClienteFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Dado Geral pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return dadoGeral;
    }

    @Override
    public List<Dadosgerais> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Dadosgerais> dadosGerais = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                dadosGerais.add(getClienteFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os dados gerais.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return dadosGerais;
    }

    @Override
    public void insert(Dadosgerais dadoGeral) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, dadoGeral.getInativo());
            stmt.setString(2, dadoGeral.getPerteneceAClasse());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                dadoGeral.setIdDadoGeral(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dado geral.", e);
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
            throw new RuntimeException("Erro ao remover dado geral.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Dadosgerais dadoGeral) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setBoolean(1, dadoGeral.getInativo());
            stmt.setString(2, dadoGeral.getPerteneceAClasse());
            stmt.setInt(3, dadoGeral.getIdDadoGeral());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dado geral.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

}
