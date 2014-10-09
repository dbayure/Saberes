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
public abstract class SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false) 
    @JoinColumn(name="idSaber", nullable=false, updatable=false)
	private Saber saber;
	
	public SaberPersona() {
		super();
		saber = new Saber();		
	}
	
	public Saber getSaber() {
		return saber;
	}

	public void setSaber(Saber saber) {
		this.saber = saber;
	}

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	
	private ArrayList<String> comprobantes;

	public ArrayList<String> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(ArrayList<String> comprobantes) {
		this.comprobantes = comprobantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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

		
	
}
