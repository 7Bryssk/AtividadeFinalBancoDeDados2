/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "estabelecimentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estabelecimentos.findAll", query = "SELECT e FROM Estabelecimentos e")
    , @NamedQuery(name = "Estabelecimentos.findByIdEstabelecimento", query = "SELECT e FROM Estabelecimentos e WHERE e.idEstabelecimento = :idEstabelecimento")
    , @NamedQuery(name = "Estabelecimentos.findByRazaoSocial", query = "SELECT e FROM Estabelecimentos e WHERE e.razaoSocial = :razaoSocial")
    , @NamedQuery(name = "Estabelecimentos.findByCnpj", query = "SELECT e FROM Estabelecimentos e WHERE e.cnpj = :cnpj")
    , @NamedQuery(name = "Estabelecimentos.findByNomeFantasia", query = "SELECT e FROM Estabelecimentos e WHERE e.nomeFantasia = :nomeFantasia")})
public class Estabelecimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstabelecimento")
    private Integer idEstabelecimento;
    @Column(name = "razaoSocial")
    private String razaoSocial;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "nomeFantasia")
    private String nomeFantasia;
    @JoinColumn(name = "idDadoGeral", referencedColumnName = "idDadoGeral")
    @ManyToOne(optional = false)
    private Dadosgerais idDadoGeral;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public Estabelecimentos() {
    }

    public Estabelecimentos(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Dadosgerais getIdDadoGeral() {
        return idDadoGeral;
    }

    public void setIdDadoGeral(Dadosgerais idDadoGeral) {
        this.idDadoGeral = idDadoGeral;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstabelecimento != null ? idEstabelecimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estabelecimentos)) {
            return false;
        }
        Estabelecimentos other = (Estabelecimentos) object;
        if ((this.idEstabelecimento == null && other.idEstabelecimento != null) || (this.idEstabelecimento != null && !this.idEstabelecimento.equals(other.idEstabelecimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Estabelecimentos[ idEstabelecimento=" + idEstabelecimento + " ]";
    }
    
}
