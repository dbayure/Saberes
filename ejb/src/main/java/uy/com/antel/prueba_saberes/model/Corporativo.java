package uy.com.antel.prueba_saberes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Corporativo")
public class Corporativo extends SaberPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean aprobo;
	private String puntaje;
	private String duracion;
	private String libreta;
	private String rolSaber;
	private String clase;
	private String internoExterno;
	
	public Corporativo() {
		super();
	}
	
	public boolean isAprobo() {
		return aprobo;
	}
	public void setAprobo(boolean aprobo) {
		this.aprobo = aprobo;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
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
}
