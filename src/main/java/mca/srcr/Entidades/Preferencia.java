/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.srcr.Entidades;

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

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "preferencia")
public class Preferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_preferencia")
    private Integer idPreferencia;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "id_cliente", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Preferencia() {
    }

    public Preferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public Integer getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPreferencia != null ? idPreferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferencia)) {
            return false;
        }
        Preferencia other = (Preferencia) object;
        if ((this.idPreferencia == null && other.idPreferencia != null) || (this.idPreferencia != null && !this.idPreferencia.equals(other.idPreferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mca.srcr.Entidades.Preferencia[ idPreferencia=" + idPreferencia + " ]";
    }
    
}
