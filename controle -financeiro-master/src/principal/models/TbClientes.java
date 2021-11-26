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
@Table(name = "TB_CLIENTES")
@NamedQueries({
    @NamedQuery(name = "TbClientes.findAll", query = "SELECT t FROM TbClientes t")})
public class TbClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DS_CLIENTE")
    private String dsCliente;
    @Column(name = "NR_CPF_CNPJ14")
    private String nrCpfCnpj14;
    @Column(name = "DT_CONTRATO")
    @Temporal(TemporalType.DATE)
    private Date dtContrato;
    @Column(name = "DS_LOGRADOURO")
    private String dsLogradouro;
    @Column(name = "DS_BAIRRO")
    private String dsBairro;
    @Column(name = "DS_MUNICIPIO")
    private String dsMunicipio;
    @Column(name = "DS_UF")
    private String dsUf;
    @Column(name = "DS_CEP")
    private String dsCep;
    @Column(name = "DS_EMAIL")
    private String dsEmail;

    public TbClientes() {
    }

    public TbClientes(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDsCliente() {
        return dsCliente;
    }

    public void setDsCliente(String dsCliente) {
        this.dsCliente = dsCliente;
    }

    public String getNrCpfCnpj14() {
        return nrCpfCnpj14;
    }

    public void setNrCpfCnpj14(String nrCpfCnpj14) {
        this.nrCpfCnpj14 = nrCpfCnpj14;
    }

    public Date getDtContrato() {
        return dtContrato;
    }

    public void setDtContrato(Date dtContrato) {
        this.dtContrato = dtContrato;
    }

    public String getDsLogradouro() {
        return dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsMunicipio() {
        return dsMunicipio;
    }

    public void setDsMunicipio(String dsMunicipio) {
        this.dsMunicipio = dsMunicipio;
    }

    public String getDsUf() {
        return dsUf;
    }

    public void setDsUf(String dsUf) {
        this.dsUf = dsUf;
    }

    public String getDsCep() {
        return dsCep;
    }

    public void setDsCep(String dsCep) {
        this.dsCep = dsCep;
    }
    
    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
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
        if (!(object instanceof TbClientes)) {
            return false;
        }
        TbClientes other = (TbClientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "principal.models.TbClientes[ id=" + id + " ]";
    }
    
}
