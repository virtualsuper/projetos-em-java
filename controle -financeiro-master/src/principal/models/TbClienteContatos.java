/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.models;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author ANDRE PORTO
 */
@Entity
@Table(name = "TB_CLIENTE_CONTATOS")
@NamedQueries({
    @NamedQuery(name = "TbClienteContatos.findAll", query = "SELECT t FROM TbClienteContatos t")})
public class TbClienteContatos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private TbClientes clienteId;
    @JoinColumn(name = "TP_CONTATO_ID", referencedColumnName = "ID")
    @ManyToOne
    private TbTipoContatos tpContatoId;
    @Column(name = "DS_CONTATO")
    private String dsContato;
    @Column(name = "DS_OBSERVACOES")
    private String dsObservacoes;
    @Column(name = "IN_STATUS")
    private Boolean inStatus;

    public TbClienteContatos() {
    }

    public TbClienteContatos(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public TbClientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(TbClientes clienteId) {
        this.clienteId = clienteId;
    }

    public TbTipoContatos getTpContatoId() {
        return tpContatoId;
    }

    public void setTpContatoId(TbTipoContatos tpContatoId) {
        this.tpContatoId = tpContatoId;
    }

    public String getDsContato() {
        return dsContato;
    }

    public void setDsContato(String dsContato) {
        this.dsContato = dsContato;
    }

    public String getDsObservacoes() {
        return dsObservacoes;
    }

    public void setDsObservacoes(String dsObservacoes) {
        this.dsObservacoes = dsObservacoes;
    }

    public Boolean getInStatus() {
        return inStatus;
    }

    public void setInStatus(Boolean inStatus) {
        this.inStatus = inStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbClienteContatos)) {
            return false;
        }
        TbClienteContatos other = (TbClienteContatos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "principal.models.TbClienteContatos[ id=" + id + " ]";
    }

}
