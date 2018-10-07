/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "parte", catalog = "flotilla", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parte.findAll", query = "SELECT p FROM Parte p")
    , @NamedQuery(name = "Parte.findByIdParte", query = "SELECT p FROM Parte p WHERE p.idParte = :idParte")
    , @NamedQuery(name = "Parte.findByNombre", query = "SELECT p FROM Parte p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Parte.findByActivo", query = "SELECT p FROM Parte p WHERE p.activo = :activo")})
public class Parte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parte", nullable = false)
    private Integer idParte;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 155)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Lob
    @Column(name = "observaciones", length = 65535)
    private String observaciones;
    @JoinColumn(name = "id_sub_tipo_parte", referencedColumnName = "id_sub_tipo_parte", nullable = false)
    @ManyToOne(optional = false)
    private SubTipoParte idSubTipoParte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParte")
    private List<ModeloParte> modeloParteList;

    public Parte() {
    }

    public Parte(Integer idParte) {
        this.idParte = idParte;
    }

    public Parte(Integer idParte, String nombre, boolean activo) {
        this.idParte = idParte;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getIdParte() {
        return idParte;
    }

    public void setIdParte(Integer idParte) {
        this.idParte = idParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public SubTipoParte getIdSubTipoParte() {
        return idSubTipoParte;
    }

    public void setIdSubTipoParte(SubTipoParte idSubTipoParte) {
        this.idSubTipoParte = idSubTipoParte;
    }

    @XmlTransient
    public List<ModeloParte> getModeloParteList() {
        return modeloParteList;
    }

    public void setModeloParteList(List<ModeloParte> modeloParteList) {
        this.modeloParteList = modeloParteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParte != null ? idParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parte)) {
            return false;
        }
        Parte other = (Parte) object;
        if ((this.idParte == null && other.idParte != null) || (this.idParte != null && !this.idParte.equals(other.idParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Parte[ idParte=" + idParte + " ]";
    }
    
}
