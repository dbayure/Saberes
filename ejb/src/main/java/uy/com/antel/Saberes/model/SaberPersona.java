package uy.com.antel.Saberes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "SaberPersona")
public class SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false) 
    @JoinColumn(name="idSaber", nullable=false, updatable=false)
	private Saber saber;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Comprobante comprobante;
	
	private Boolean aprobacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Saber getSaber() {
		return saber;
	}

	public void setSaber(Saber saber) {
		this.saber = saber;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getAprobacion() {
		return aprobacion;
	}

	public void setAprobacion(Boolean aprobacion) {
		this.aprobacion = aprobacion;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aprobacion == null) ? 0 : aprobacion.hashCode());
		result = prime * result
				+ ((comprobante == null) ? 0 : comprobante.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saber == null) ? 0 : saber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaberPersona other = (SaberPersona) obj;
		if (aprobacion == null) {
			if (other.aprobacion != null)
				return false;
		} else if (!aprobacion.equals(other.aprobacion))
			return false;
		if (comprobante == null) {
			if (other.comprobante != null)
				return false;
		} else if (!comprobante.equals(other.comprobante))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saber == null) {
			if (other.saber != null)
				return false;
		} else if (!saber.equals(other.saber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaberPersona [id=" + id + ", saber=" + saber + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", comprobante="
				+ comprobante + ", aprobacion=" + aprobacion + "]";
	}



	
}
