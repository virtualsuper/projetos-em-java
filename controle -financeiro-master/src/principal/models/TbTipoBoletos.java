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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ANDRE PORTO
 */
@Entity
@Table(name = "TB_TIPO_BOLETOS")
@NamedQueries({
    @NamedQuery(name = "TbTipoBoletos.findAll", query = "SELECT t FROM TbTipoBoletos t")})
public class TbTipoBoletos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DS_TIPO_BOLETO")
    private String dsTipoBoleto;

    public TbTipoBoletos() {
    }

    public TbTipoBoletos(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDsTipoBoleto() {
        return dsTipoBoleto;
    }

    public void setDsTipoBoleto(String dsTipoBoleto) {
        this.dsTipoBoleto = dsTipoBoleto;
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
        if (!(object instanceof TbTipoBoletos)) {
            return false;
        }
        TbTipoBoletos other = (TbTipoBoletos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsTipoBoleto;
    }
    
}
