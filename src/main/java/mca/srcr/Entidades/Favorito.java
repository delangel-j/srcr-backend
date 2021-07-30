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
@Table(name = "favorito")
public class Favorito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_favorito")
    private Integer idFavorito;
    @JoinColumn(name = "id_cliente", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "id_platillo", referencedColumnName = "id_platillo")
    @ManyToOne(optional = false)
    private Platillo platillo;

    public Favorito() {
    }

    public Favorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavorito != null ? idFavorito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorito)) {
            return false;
        }
        Favorito other = (Favorito) object;
        if ((this.idFavorito == null && other.idFavorito != null) || (this.idFavorito != null && !this.idFavorito.equals(other.idFavorito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mca.srcr.Entidades.Favorito[ idFavorito=" + idFavorito + " ]";
    }
    
}
