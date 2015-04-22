package uy.com.antel.Saberes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "NoCorporativo")
public class NoCorporativo extends SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	private char validado;
	private String mensaje;

	@ManyToOne(optional=true) 
    @JoinColumn(name="idOrigen", nullable=true, updatable=false)
	private Origen origen;
	
	public NoCorporativo() {
		this.validado = 'P';
	}

	public char getValidado() {
		return validado;
	}

	public void setValidado(char validado) {
		this.validado = validado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		result = prime * result + validado;
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
		NoCorporativo other = (NoCorporativo) obj;
		if (mensaje == null) {
			if (other.mensaje != null)
				return false;
		} else if (!mensaje.equals(other.mensaje))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		if (validado != other.validado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NoCorporativo [validado=" + validado + ", mensaje=" + mensaje
				+ ", origen=" + origen + "]";
	}

	
}
