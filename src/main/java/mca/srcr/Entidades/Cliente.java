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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "calorias_desayuno")
    private int caloriasDesayuno;
    @Basic(optional = false)
    @Column(name = "calorias_almuerzo")
    private int caloriasAlmuerzo;
    @Basic(optional = false)
    @Column(name = "calorias_cena")
    private int caloriasCena;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Id
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Favorito> favoritoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Preferencia> preferenciaSet;

    public Cliente() {
    }

    public Cliente(String correo) {
        this.correo = correo;
    }

    public Cliente(String correo, int caloriasDesayuno, int caloriasAlmuerzo, int caloriasCena, String nombre, String contrasena) {
        this.correo = correo;
        this.caloriasDesayuno = caloriasDesayuno;
        this.caloriasAlmuerzo = caloriasAlmuerzo;
        this.caloriasCena = caloriasCena;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getCaloriasDesayuno() {
        return caloriasDesayuno;
    }

    public void setCaloriasDesayuno(int caloriasDesayuno) {
        this.caloriasDesayuno = caloriasDesayuno;
    }

    public int getCaloriasAlmuerzo() {
        return caloriasAlmuerzo;
    }

    public void setCaloriasAlmuerzo(int caloriasAlmuerzo) {
        this.caloriasAlmuerzo = caloriasAlmuerzo;
    }

    public int getCaloriasCena() {
        return caloriasCena;
    }

    public void setCaloriasCena(int caloriasCena) {
        this.caloriasCena = caloriasCena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<Favorito> getFavoritoSet() {
        return favoritoSet;
    }

    public void setFavoritoSet(Set<Favorito> favoritoSet) {
        this.favoritoSet = favoritoSet;
    }

    public Set<Preferencia> getPreferenciaSet() {
        return preferenciaSet;
    }

    public void setPreferenciaSet(Set<Preferencia> preferenciaSet) {
        this.preferenciaSet = preferenciaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mca.srcr.Entidades.Cliente[ correo=" + correo + " ]";
    }
    
}
