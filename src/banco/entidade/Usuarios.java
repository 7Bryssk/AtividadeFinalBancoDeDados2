/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarios.findByNome", query = "SELECT u FROM Usuarios u WHERE u.nome = :nome")
    , @NamedQuery(name = "Usuarios.findByLogin", query = "SELECT u FROM Usuarios u WHERE u.login = :login")
    , @NamedQuery(name = "Usuarios.findBySenha", query = "SELECT u FROM Usuarios u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuarios.findByCpf", query = "SELECT u FROM Usuarios u WHERE u.cpf = :cpf")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cpf")
    private String cpf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Estabelecimentos> estabelecimentosCollection;
    @JoinColumn(name = "idDadoGeral", referencedColumnName = "idDadoGeral")
    @ManyToOne(optional = false)
    private Dadosgerais idDadoGeral;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @XmlTransient
    public Collection<Estabelecimentos> getEstabelecimentosCollection() {
        return estabelecimentosCollection;
    }

    public void setEstabelecimentosCollection(Collection<Estabelecimentos> estabelecimentosCollection) {
        this.estabelecimentosCollection = estabelecimentosCollection;
    }

    public Dadosgerais getIdDadoGeral() {
        return idDadoGeral;
    }

    public void setIdDadoGeral(Dadosgerais idDadoGeral) {
        this.idDadoGeral = idDadoGeral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
