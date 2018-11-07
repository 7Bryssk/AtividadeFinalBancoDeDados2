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
@Table(name = "telefones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefones.findAll", query = "SELECT t FROM Telefones t")
    , @NamedQuery(name = "Telefones.findByIdTelefone", query = "SELECT t FROM Telefones t WHERE t.idTelefone = :idTelefone")
    , @NamedQuery(name = "Telefones.findByTelefone", query = "SELECT t FROM Telefones t WHERE t.telefone = :telefone")
    , @NamedQuery(name = "Telefones.findByInativo", query = "SELECT t FROM Telefones t WHERE t.inativo = :inativo")})
public class Telefones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTelefone")
    private Integer idTelefone;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "inativo")
    private Short inativo;
    @JoinColumn(name = "idDadoGeral", referencedColumnName = "idDadoGeral")
    @ManyToOne(optional = false)
    private Dadosgerais idDadoGeral;

    public Telefones() {
    }

    public Telefones(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        hash += (idTelefone != null ? idTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefones)) {
            return false;
        }
        Telefones other = (Telefones) object;
        if ((this.idTelefone == null && other.idTelefone != null) || (this.idTelefone != null && !this.idTelefone.equals(other.idTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Telefones[ idTelefone=" + idTelefone + " ]";
    }
    
}
