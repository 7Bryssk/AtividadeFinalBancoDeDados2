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
@Table(name = "dadosgerais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dadosgerais.findAll", query = "SELECT d FROM Dadosgerais d")
    , @NamedQuery(name = "Dadosgerais.findByIdDadoGeral", query = "SELECT d FROM Dadosgerais d WHERE d.idDadoGeral = :idDadoGeral")
    , @NamedQuery(name = "Dadosgerais.findByInativo", query = "SELECT d FROM Dadosgerais d WHERE d.inativo = :inativo")
    , @NamedQuery(name = "Dadosgerais.findByPerteneceAClasse", query = "SELECT d FROM Dadosgerais d WHERE d.perteneceAClasse = :perteneceAClasse")})
public class Dadosgerais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDadoGeral")
    private Integer idDadoGeral;
    @Column(name = "inativo")
    private Short inativo;
    @Column(name = "pertenece_a_classe")
    private String perteneceAClasse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Emails> emailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Estabelecimentos> estabelecimentosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Enderecos> enderecosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Clientes> clientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDadoGeral")
    private Collection<Telefones> telefonesCollection;

    public Dadosgerais() {
    }

    public Dadosgerais(Integer idDadoGeral) {
        this.idDadoGeral = idDadoGeral;
    }

    public Integer getIdDadoGeral() {
        return idDadoGeral;
    }

    public void setIdDadoGeral(Integer idDadoGeral) {
        this.idDadoGeral = idDadoGeral;
    }

    public Short getInativo() {
        return inativo;
    }

    public void setInativo(Short inativo) {
        this.inativo = inativo;
    }

    public String getPerteneceAClasse() {
        return perteneceAClasse;
    }

    public void setPerteneceAClasse(String perteneceAClasse) {
        this.perteneceAClasse = perteneceAClasse;
    }

    @XmlTransient
    public Collection<Emails> getEmailsCollection() {
        return emailsCollection;
    }

    public void setEmailsCollection(Collection<Emails> emailsCollection) {
        this.emailsCollection = emailsCollection;
    }

    @XmlTransient
    public Collection<Estabelecimentos> getEstabelecimentosCollection() {
        return estabelecimentosCollection;
    }

    public void setEstabelecimentosCollection(Collection<Estabelecimentos> estabelecimentosCollection) {
        this.estabelecimentosCollection = estabelecimentosCollection;
    }

    @XmlTransient
    public Collection<Enderecos> getEnderecosCollection() {
        return enderecosCollection;
    }

    public void setEnderecosCollection(Collection<Enderecos> enderecosCollection) {
        this.enderecosCollection = enderecosCollection;
    }

    @XmlTransient
    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<Telefones> getTelefonesCollection() {
        return telefonesCollection;
    }

    public void setTelefonesCollection(Collection<Telefones> telefonesCollection) {
        this.telefonesCollection = telefonesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDadoGeral != null ? idDadoGeral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadosgerais)) {
            return false;
        }
        Dadosgerais other = (Dadosgerais) object;
        if ((this.idDadoGeral == null && other.idDadoGeral != null) || (this.idDadoGeral != null && !this.idDadoGeral.equals(other.idDadoGeral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.entidade.Dadosgerais[ idDadoGeral=" + idDadoGeral + " ]";
    }
    
}
