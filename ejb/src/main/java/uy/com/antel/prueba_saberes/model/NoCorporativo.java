package uy.com.antel.prueba_saberes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "NoCorporativo")
public class NoCorporativo extends SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean validado;
	private boolean completo;	
	
	public NoCorporativo() {
	}
	public boolean isValidado() {
		return validado;
	}
	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	public boolean isCompleto() {
		return completo;
	}
	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (completo ? 1231 : 1237);
		result = prime * result + (validado ? 1231 : 1237);
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
		NoCorporativo other = (NoCorporativo) obj;
		if (completo != other.completo)
			return false;
		if (validado != other.validado)
			return false;
		return true;
	}
	
	
		
}
