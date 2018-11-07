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
@Table(name = "enderecos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enderecos.findAll", query = "SELECT e FROM Enderecos e")
    , @NamedQuery(name = "Enderecos.findByIdEnderecos", query = "SELECT e FROM Enderecos e WHERE e.idEnderecos = :idEnderecos")
    , @NamedQuery(name = "Enderecos.findByNumero", query = "SELECT e FROM Enderecos e WHERE e.numero = :numero")
    , @NamedQuery(name = "Enderecos.findByRua", query = "SELECT e FROM Enderecos e WHERE e.rua = :rua")
    , @NamedQuery(name = "Enderecos.findByBairro", query = "SELECT e FROM Enderecos e WHERE e.bairro = :bairro")
    , @NamedQuery(name = "Enderecos.findByCidade", query = "SELECT e FROM Enderecos e WHERE e.cidade = :cidade")
    , @NamedQuery(name = "Enderecos.findByComplemento", query = "SELECT e FROM Enderecos e WHERE e.complemento = :complemento")
    , @NamedQuery(name = "Enderecos.findByInativo", query = "SELECT e FROM Enderecos e WHERE e.inativo = :inativo")})
public class Enderecos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEnderecos")
    private Integer idEnderecos;
    @Column(name = "numero")
    private String numero;
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "inativo")
    private Short inativo;
    @JoinColumn(name = "idDadoGeral", referencedColumnName = "idDadoGeral")
    @ManyToOne(optional = false)
    private Dadosgerais idDadoGeral;

    public Enderecos() {
    }

    public Enderecos(Integer idEnderecos) {
        this.idEnderecos = idEnderecos;
    }

    public Integer getIdEnderecos() {
        return idEnderecos;
    }

    public void setIdEnderecos(Integer idEnderecos) {
        this.idEnderecos = idEnderecos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Short getInativo() {
        return inativo;
    }

    public void setInativo(Short inativo) {
        this.inativo = inativo;
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
        hash += (idEnderecos != null ? idEnderecos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enderecos)) {
            return false;
        }
        Enderecos other = (Enderecos) object;
        if ((this.idEnderecos == null && other.idEnderecos != null) || (this.idEnderecos != null && !this.idEnderecos.equals(other.idEnderecos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Enderecos[ idEnderecos=" + idEnderecos + " ]";
    }
    
}
