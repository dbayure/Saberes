package uy.com.antel.Saberes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Corporativo")
public class Corporativo extends SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Quitar el campo de aprobación.
	//private boolean aprobo;
	private Integer puntaje;
	private String duracion;
	private String libreta;
	private String rolSaber;
	private String clase;
	private String internoExterno;
	private Integer idEventoGicca;
	private Integer asistencia;

	public Corporativo() {
		super();
	}
	
	public Integer getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getLibreta() {
		return libreta;
	}
	public void setLibreta(String libreta) {
		this.libreta = libreta;
	}
	public String getRolSaber() {
		return rolSaber;
	}
	public void setRolSaber(String rolSaber) {
		this.rolSaber = rolSaber;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getInternoExterno() {
		return internoExterno;
	}
	public void setInternoExterno(String internoExterno) {
		this.internoExterno = internoExterno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdEventoGicca() {
		return idEventoGicca;
	}

	public void setIdEventoGicca(Integer idEventoGicca) {
		this.idEventoGicca = idEventoGicca;
	}
	
	public Integer getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Integer asistencia) {
		this.asistencia = asistencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((asistencia == null) ? 0 : asistencia.hashCode());
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result
				+ ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result
				+ ((idEventoGicca == null) ? 0 : idEventoGicca.hashCode());
		result = prime * result
				+ ((internoExterno == null) ? 0 : internoExterno.hashCode());
		result = prime * result + ((libreta == null) ? 0 : libreta.hashCode());
		result = prime * result + ((puntaje == null) ? 0 : puntaje.hashCode());
		result = prime * result
				+ ((rolSaber == null) ? 0 : rolSaber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corporativo other = (Corporativo) obj;
		if (asistencia == null) {
			if (other.asistencia != null)
				return false;
		} else if (!asistencia.equals(other.asistencia))
			return false;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (idEventoGicca == null) {
			if (other.idEventoGicca != null)
				return false;
		} else if (!idEventoGicca.equals(other.idEventoGicca))
			return false;
		if (internoExterno == null) {
			if (other.internoExterno != null)
				return false;
		} else if (!internoExterno.equals(other.internoExterno))
			return false;
		if (libreta == null) {
			if (other.libreta != null)
				return false;
		} else if (!libreta.equals(other.libreta))
			return false;
		if (puntaje == null) {
			if (other.puntaje != null)
				return false;
		} else if (!puntaje.equals(other.puntaje))
			return false;
		if (rolSaber == null) {
			if (other.rolSaber != null)
				return false;
		} else if (!rolSaber.equals(other.rolSaber))
			return false;
		return true;
	}
	
	
	
	
}
