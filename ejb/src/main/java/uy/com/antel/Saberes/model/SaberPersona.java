package uy.com.antel.Saberes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	private ArrayList<String> comprobantes;
	
	private Boolean aprobacion;
	
	private String comentarios;

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

	public ArrayList<String> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(ArrayList<String> comprobantes) {
		this.comprobantes = comprobantes;
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

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aprobacion == null) ? 0 : aprobacion.hashCode());
		result = prime * result
				+ ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result
				+ ((comprobantes == null) ? 0 : comprobantes.hashCode());
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
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (comprobantes == null) {
			if (other.comprobantes != null)
				return false;
		} else if (!comprobantes.equals(other.comprobantes))
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
				+ fechaInicio + ", fechaFin=" + fechaFin + ", comprobantes="
				+ comprobantes + ", aprobacion=" + aprobacion
				+ ", comentarios=" + comentarios + "]";
	}
	
	
	
}
