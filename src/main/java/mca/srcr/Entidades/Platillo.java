/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.srcr.Entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "platillo")
public class Platillo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_platillo")
    private Integer idPlatillo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "calorias")
    private double calorias;
    @Basic(optional = false)
    @Column(name = "gramos_por_porcion")
    private double gramosPorPorcion;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "platillo")
    private Set<Favorito> favoritoSet;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
    @ManyToOne(optional = false)
    private Restaurante restaurante;

    public Platillo() {
    }

    public Platillo(Integer idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public Platillo(Integer idPlatillo, String nombre, double calorias, double gramosPorPorcion, double precio) {
        this.idPlatillo = idPlatillo;
        this.nombre = nombre;
        this.calorias = calorias;
        this.gramosPorPorcion = gramosPorPorcion;
        this.precio = precio;
    }

    public Integer getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Integer idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getGramosPorPorcion() {
        return gramosPorPorcion;
    }

    public void setGramosPorPorcion(double gramosPorPorcion) {
        this.gramosPorPorcion = gramosPorPorcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Set<Favorito> getFavoritoSet() {
        return favoritoSet;
    }

    public void setFavoritoSet(Set<Favorito> favoritoSet) {
        this.favoritoSet = favoritoSet;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatillo != null ? idPlatillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Platillo)) {
            return false;
        }
        Platillo other = (Platillo) object;
        if ((this.idPlatillo == null && other.idPlatillo != null) || (this.idPlatillo != null && !this.idPlatillo.equals(other.idPlatillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mca.srcr.Entidades.Platillo[ idPlatillo=" + idPlatillo + " ]";
    }
    
}
