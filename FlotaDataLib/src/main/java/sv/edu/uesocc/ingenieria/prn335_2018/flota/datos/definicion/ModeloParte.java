/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "modelo_parte", catalog = "flotilla", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModeloParte.findAll", query = "SELECT m FROM ModeloParte m")
    , @NamedQuery(name = "ModeloParte.findByIdModeloParte", query = "SELECT m FROM ModeloParte m WHERE m.idModeloParte = :idModeloParte")
    , @NamedQuery(name = "ModeloParte.findByActivo", query = "SELECT m FROM ModeloParte m WHERE m.activo = :activo")})
public class ModeloParte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modelo_parte", nullable = false)
    private Long idModeloParte;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Lob
    @Column(name = "observaciones", length = 65535)
    private String observaciones;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo", nullable = false)
    @ManyToOne(optional = false)
    private Modelo idModelo;
    @JoinColumn(name = "id_parte", referencedColumnName = "id_parte", nullable = false)
    @ManyToOne(optional = false)
    private Parte idParte;

    public ModeloParte() {
    }

    public ModeloParte(Long idModeloParte) {
        this.idModeloParte = idModeloParte;
    }

    public ModeloParte(Long idModeloParte, boolean activo) {
        this.idModeloParte = idModeloParte;
        this.activo = activo;
    }

    public Long getIdModeloParte() {
        return idModeloParte;
    }

    public void setIdModeloParte(Long idModeloParte) {
        this.idModeloParte = idModeloParte;
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

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Parte getIdParte() {
        return idParte;
    }

    public void setIdParte(Parte idParte) {
        this.idParte = idParte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModeloParte != null ? idModeloParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModeloParte)) {
            return false;
        }
        ModeloParte other = (ModeloParte) object;
        if ((this.idModeloParte == null && other.idModeloParte != null) || (this.idModeloParte != null && !this.idModeloParte.equals(other.idModeloParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.ModeloParte[ idModeloParte=" + idModeloParte + " ]";
    }
    
}
