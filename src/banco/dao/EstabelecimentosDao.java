/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;


import banco.entidade.Dadosgerais;
import banco.entidade.Estabelecimentos;
import banco.entidade.Usuarios;
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
public class EstabelecimentosDao implements Dao<Estabelecimentos>{

    private static final String GET_BY_ID = "SELECT * FROM estabelecimentos WHERE idEstabelecimento = ?";
    private static final String GET_ALL = "SELECT * FROM estabelecimentos";
    private static final String INSERT = "INSERT INTO estabelecimentos (razaoSocial, cnpj, nomeFantasia, IdUsuario, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE estabelecimentos SET razaoSocial = ?, cnpj = ?, nomeFantasia = ?, IdUsuario = ?, idDadoGeral = ?  WHERE idEstabelecimento = ?";
    private static final String DELETE = "DELETE FROM estabelecimentos WHERE idEstabelecimento = ?";

    public EstabelecimentosDao() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS estabelecimentos"
                + "  (idEstabelecimento           INTEGER,"
                + "   razaoSocial                 VARCHAR(50),"
                + "   cnpj                        VARCHAR(50),"
                + "   nomeFantasia                VARCHAR(50),"
                + "   IdUsuario                   INTEGER,"
                + "   idDadoGeral                 INTEGER,"
                + "   FOREIGN KEY (IdUsuario) REFERENCES usuarios(IdUsuario),"
                + "   FOREIGN KEY (idDadoGeral) REFERENCES dadosgerais(idDadoGeral),"
                + "   PRIMARY KEY (idEstabelecimento))";

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

    private Estabelecimentos getEstabelecimentoFromRS(ResultSet rs) throws SQLException {
        Estabelecimentos estabelecimento = new Estabelecimentos();

        estabelecimento.setIdEstabelecimento(rs.getInt("idEstabelecimento"));
        estabelecimento.setRazaoSocial(rs.getString("razaoSocial"));
        estabelecimento.setCnpj(rs.getString("cnpj"));
        estabelecimento.setNomeFantasia(rs.getString("nomeFantasia"));
        estabelecimento.setIdUsuario(new Usuarios(rs.getInt("IdUsuario")));
        estabelecimento.setIdDadoGeral(new Dadosgerais(rs.getInt("idEstabelecimento")));

        return estabelecimento;
    }

    @Override
    public Estabelecimentos getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Estabelecimentos estabelecimento = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                estabelecimento = getEstabelecimentoFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Estabelecimento pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return estabelecimento;
    }

    @Override
    public List<Estabelecimentos> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Estabelecimentos> estabelecimento = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                estabelecimento.add(getEstabelecimentoFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os Estabelecimentos.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return estabelecimento;
    }

    @Override
    public void insert(Estabelecimentos estabelecimento) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // private static final String INSERT = "INSERT INTO usuarios (nome, login, senha, cpf, idDadoGeral) VALUES (?, ?, ?, ?, ?)";
        
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, estabelecimento.getRazaoSocial());
            stmt.setString(2, estabelecimento.getCnpj());
            stmt.setString(3, estabelecimento.getNomeFantasia());
            stmt.setInt(4, estabelecimento.getIdUsuario().getIdUsuario());
            stmt.setInt(5, estabelecimento.getIdDadoGeral().getIdDadoGeral());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                estabelecimento.setIdEstabelecimento(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Estabelecimento.", e);
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
            throw new RuntimeException("Erro ao remover Estabelecimento.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Estabelecimentos estabelecimento) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, estabelecimento.getRazaoSocial());
            stmt.setString(2, estabelecimento.getCnpj());
            stmt.setString(3, estabelecimento.getNomeFantasia());
            stmt.setInt(4, estabelecimento.getIdUsuario().getIdUsuario());
            stmt.setInt(5, estabelecimento.getIdDadoGeral().getIdDadoGeral());
            stmt.setInt(6, estabelecimento.getIdEstabelecimento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Estabelecimento.", e);
        } finally {
            close(conn, stmt, null);
        }
    }
    
    public TableModel getTabelaEstabelecimentos() {
        UsuarioLogado user = new UsuarioLogado();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Razão Social");
        modelo.addColumn("Nome Fantasia");
        modelo.addColumn("CNPJ");

        List<Estabelecimentos> lista = new ArrayList<>();
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM estabelecimentos e, dadosgerais d WHERE e.IdUsuario = ? and e.idDadoGeral=d.idDadoGeral and d.inativo='true'");
            stmt.setInt(1, user.getIdusuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(getEstabelecimentoFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter Estabelecimentos do Usuario.", e);
        } finally {
            close(conn, stmt, rs);
        }
        
        for (Estabelecimentos estabelecimento : lista) {
            
            Object[] linha = {
                estabelecimento.getIdEstabelecimento(),
                estabelecimento.getRazaoSocial(),
                estabelecimento.getNomeFantasia(),
                estabelecimento.getCnpj()
            };
    
            
            modelo.addRow(linha);

        }
        return modelo;
    }
    
}
