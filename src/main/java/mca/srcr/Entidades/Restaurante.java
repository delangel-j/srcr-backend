/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.srcr.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "restaurante")
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restaurante")
    private Integer idRestaurante;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "horario_apertura")
    @Temporal(TemporalType.TIME)
    private Date horarioApertura;
    @Basic(optional = false)
    @Column(name = "horario_cierre")
    @Temporal(TemporalType.TIME)
    private Date horarioCierre;
    @Basic(optional = false)
    @Column(name = "img_url")
    private String imgUrl;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
    private Set<Sucursal> sucursalSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
    private Set<Platillo> platilloSet;

    public Restaurante() {
    }

    public Restaurante(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Restaurante(Integer idRestaurante, String usuario, String contrasenia, String nombre, Date horarioApertura, Date horarioCierre, String imgUrl) {
        this.idRestaurante = idRestaurante;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.imgUrl = imgUrl;
    }

    public Integer getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(Date horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public Date getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(Date horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Sucursal> getSucursalSet() {
        return sucursalSet;
    }

    public void setSucursalSet(Set<Sucursal> sucursalSet) {
        this.sucursalSet = sucursalSet;
    }

    public Set<Platillo> getPlatilloSet() {
        return platilloSet;
    }

    public void setPlatilloSet(Set<Platillo> platilloSet) {
        this.platilloSet = platilloSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurante != null ? idRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurante)) {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if ((this.idRestaurante == null && other.idRestaurante != null) || (this.idRestaurante != null && !this.idRestaurante.equals(other.idRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mca.srcr.Entidades.Restaurante[ idRestaurante=" + idRestaurante + " ]";
    }
    
}
