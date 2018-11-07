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
@Table(name = "emails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emails.findAll", query = "SELECT e FROM Emails e")
    , @NamedQuery(name = "Emails.findByIdEmail", query = "SELECT e FROM Emails e WHERE e.idEmail = :idEmail")
    , @NamedQuery(name = "Emails.findByEmail", query = "SELECT e FROM Emails e WHERE e.email = :email")
    , @NamedQuery(name = "Emails.findByInativo", query = "SELECT e FROM Emails e WHERE e.inativo = :inativo")})
public class Emails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmail")
    private Integer idEmail;
    @Column(name = "email")
    private String email;
    @Column(name = "inativo")
    private Short inativo;
    @JoinColumn(name = "idDadoGeral", referencedColumnName = "idDadoGeral")
    @ManyToOne(optional = false)
    private Dadosgerais idDadoGeral;

    public Emails() {
    }

    public Emails(Integer idEmail) {
        this.idEmail = idEmail;
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (idEmail != null ? idEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emails)) {
            return false;
        }
        Emails other = (Emails) object;
        if ((this.idEmail == null && other.idEmail != null) || (this.idEmail != null && !this.idEmail.equals(other.idEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Emails[ idEmail=" + idEmail + " ]";
    }
    
}
