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
@Table(name = "TB_BOLETOS_GERADOS")
@NamedQueries({
    @NamedQuery(name = "TbBoletosGerados.findAll", query = "SELECT t FROM TbBoletosGerados t")})
public class TbBoletosGerados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DT_PROCESSADO")
    @Temporal(TemporalType.DATE)
    private Date dtProcessado;
    @Column(name = "DT_VENCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dtVencimento;
    @Column(name = "DT_PAGAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;
    @Column(name = "VL_BOLETO")
    private BigDecimal vlBoleto;
    @Column(name = "NR_NOSSO_NUMERO")
    private String nrNossoNumero;
    @Column(name = "CD_DAC")
    private String cdDac;
    @JoinColumn(name = "CONTRATO_ID", referencedColumnName = "ID")
    @ManyToOne
    private TbClienteContratos contratoId;
    @Column(name = "IN_ENVIADO_REMESSA")
    private Boolean inEnviadoRemessa;
    @Column(name = "COMPETENCIA")
    private String competencia;
    @Column(name = "IN_2VIA")
    private Boolean in2Via;
    @Column(name = "IN_ENVIADO_EMAIL")
    private Boolean inEnviadoEmail;
    @Column(name = "IN_ENVIAR")
    private Boolean inEnviar;
      
    @Column(name = "DS_URL")
    private String dsUrl;
    
       @Column(name = "DS_EMAIL")
        private String dsEmail;

    public Boolean getIn2Via() {
        return in2Via;
    }

    public void setIn2Via(Boolean in2Via) {
        this.in2Via = in2Via;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
    
    transient Boolean inGerar = false;
    transient Boolean inBoletoGerado= false;
    
    
    public Boolean getInEnviadoRemessa() {
        return inEnviadoRemessa;
    }

    public void setInEnviadoRemessa(Boolean inEnviadoRemessa) {
        this.inEnviadoRemessa = inEnviadoRemessa;
    }
    
        public Boolean getInEnviadoEmail() {
        return inEnviadoEmail;
    }
        
        

    public void setInEnviar(Boolean inEnviar) {
        this.inEnviar = inEnviar;
    }
    
        public Boolean getInEnviar() {
        return inEnviar;
    }
        
        

    public void setInEnviadoEmail(Boolean inEnviadoEmail) {
        this.inEnviadoEmail = inEnviadoEmail;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public TbBoletosGerados() {
    }

    public TbBoletosGerados(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getDtProcessado() {
        return dtProcessado;
    }

    public void setDtProcessado(Date dtProcessado) {
        this.dtProcessado = dtProcessado;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public BigDecimal getVlBoleto() {
        return vlBoleto;
    }

    public void setVlBoleto(BigDecimal vlBoleto) {
        this.vlBoleto = vlBoleto;
    }

    public String getNrNossoNumero() {
        return nrNossoNumero;
    }

    public void setNrNossoNumero(String nrNossoNumero) {
        this.nrNossoNumero = nrNossoNumero;
    }

    public String getCdDac() {
        return cdDac;
    }

    public void setCdDac(String cdDac) {
        this.cdDac = cdDac;
    }

    public TbClienteContratos getContratoId() {
        return contratoId;
    }

    public void setContratoId(TbClienteContratos contratoId) {
        this.contratoId = contratoId;
    }
    
     public String getDsUrl() {
        return dsUrl;
    }

    public void setDsUrl(String dsUrl) {
        this.dsUrl = dsUrl;
    }
    
        
     public String getDsEamil() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }
    
        public Boolean getInGerar() {
        return inGerar;
    }

    public void setInGerar(Boolean inGerar) {
        this.inGerar = inGerar;
    }
    
        public Boolean getInBoletoGerado() {
        return inBoletoGerado;
    }

    public void setInBoletoGerado(Boolean inBoletoGerado) {
        this.inBoletoGerado = inBoletoGerado;
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
        if (!(object instanceof TbBoletosGerados)) {
            return false;
        }
        TbBoletosGerados other = (TbBoletosGerados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "principal.models.TbBoletosGerados[ id=" + id + " ]";
    }

}
