/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

import banco.entidade.Dadosgerais;
import banco.entidade.Enderecos;
import banco.entidade.Enderecos;
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
public class EnderecosDao implements Dao<Enderecos>{

    private static final String GET_BY_ID = "SELECT * FROM enderecos WHERE idEndereco = ?";
    private static final String GET_ALL = "SELECT * FROM enderecos";
    private static final String INSERT = "INSERT INTO enderecos (numero, rua, bairro, cidade, complemento, inativo, idDadoGeral) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE enderecos SET numero = ?, rua = ?, bairro = ?, cidade = ?, complemento = ?, inativo = ?, idDadoGeral = ?,  WHERE idEndereco = ?";
    private static final String DELETE = "DELETE FROM enderecos WHERE idEndereco = ?";

    public EnderecosDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS enderecos"
                + "  (idEndereco                INTEGER NOT NULL AUTO_INCREMENT,"
                + "   numero                    VARCHAR(50),"
                + "   rua                       VARCHAR(50),"
                + "   bairro                    VARCHAR(50),"
                + "   cidade                    VARCHAR(50),"
                + "   complemento               VARCHAR(50),"
                + "   inativo                   TINYINT(4),"
                + "   FOREIGN KEY (idDadoGeral) REFERENCES dadosgerais(idDadoGeral),"
                + "   PRIMARY KEY (idEndereco))";

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

    private Enderecos getEnderecoFromRS(ResultSet rs) throws SQLException {
        Enderecos endereco = new Enderecos();

        endereco.setIdEnderecos(rs.getInt("idEndereco"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setRua(rs.getString("rua"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setInativo(rs.getBoolean("inativo"));
        endereco.setIdDadoGeral(new Dadosgerais(rs.getInt("idDadoGeral")));

        return endereco;
    }

    @Override
    public Enderecos getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Enderecos enderecos = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                enderecos = getEnderecoFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Endereco pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return enderecos;
    }

    @Override
    public List<Enderecos> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Enderecos> enderecos = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                enderecos.add(getEnderecoFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os Enderecos.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return enderecos;
    }

    @Override
    public void insert(Enderecos endereco) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // private static final String INSERT = "INSERT INTO usuarios (nome, login, senha, cpf, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
        
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, endereco.getNumero());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getComplemento());
            stmt.setBoolean(6, endereco.getInativo());
            stmt.setInt(7, endereco.getIdDadoGeral().getIdDadoGeral());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                endereco.setIdEnderecos(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Endereco.", e);
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
            throw new RuntimeException("Erro ao remover Endereco.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Enderecos endereco) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, endereco.getNumero());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getComplemento());
            stmt.setBoolean(6, endereco.getInativo());
            stmt.setInt(7, endereco.getIdDadoGeral().getIdDadoGeral());
            stmt.setInt(8, endereco.getIdEnderecos());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Endereco.", e);
        } finally {
            close(conn, stmt, null);
        }
    }
    
}
