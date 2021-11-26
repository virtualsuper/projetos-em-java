/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANDRE PORTO
 */
@Entity
@Table(name = "TB_CLIENTE_CONTRATOS")
@NamedQueries({
    @NamedQuery(name = "TbClienteContratos.findAll", query = "SELECT t FROM TbClienteContratos t")})
public class TbClienteContratos implements Serializable {

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
    @Column(name = "DT_INICIAL")
    @Temporal(TemporalType.DATE)
    private Date dtInicial;
    @Column(name = "DT_FINAL")
    @Temporal(TemporalType.DATE)
    private Date dtFinal;
    @Column(name = "VL_BOLETO")
    private BigDecimal vlBoleto;
    @Column(name = "DIA_VENCIMENTO")
    private String diaVencimento;
    @JoinColumn(name = "TIPO_BOLETO_ID", referencedColumnName = "ID")
    @ManyToOne
    private TbTipoBoletos tipoBoletoId;
    @Column(name = "DS_DESCRICAO")
    private String dsDescricao;
    transient Boolean inGerar = false;
    transient Boolean inBoletoGerado = false;

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
    }

    public Boolean getInBoletoGerado() {
        return inBoletoGerado;
    }

    public void setInBoletoGerado(Boolean inBoletoGerado) {
        this.inBoletoGerado = inBoletoGerado;
    }
    

    public Boolean getInGerar() {
        return inGerar;
    }

    public void setInGerar(Boolean inGerar) {
        this.inGerar = inGerar;
    }

    public TbClienteContratos() {
    }

    public TbClienteContratos(BigDecimal id) {
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

    public Date getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    public BigDecimal getVlBoleto() {
        return vlBoleto;
    }

    public void setVlBoleto(BigDecimal vlBoleto) {
        this.vlBoleto = vlBoleto;
    }

    public String getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(String diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public TbTipoBoletos getTipoBoletoId() {
        return tipoBoletoId;
    }

    public void setTipoBoletoId(TbTipoBoletos tipoBoletoId) {
        this.tipoBoletoId = tipoBoletoId;
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
        if (!(object instanceof TbClienteContratos)) {
            return false;
        }
        TbClienteContratos other = (TbClienteContratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "principal.models.TbClienteContratos[ id=" + id + " ]";
    }

}
