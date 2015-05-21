package uy.com.antel.Saberes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "gpacurre")
public class Gpacurre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date f_com_efec;
	private Date f_fin_efec;
	private String libreta;
	private String id_evento;
	private String modalidad;
	private Integer cod_curso;
	private Integer cant_horas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getF_com_efec() {
		return f_com_efec;
	}
	public void setF_com_efec(Date f_com_efec) {
		this.f_com_efec = f_com_efec;
	}
	public Date getF_fin_efec() {
		return f_fin_efec;
	}
	public void setF_fin_efec(Date f_fin_efec) {
		this.f_fin_efec = f_fin_efec;
	}
	public String getLibreta() {
		return libreta;
	}
	public void setLibreta(String libreta) {
		this.libreta = libreta;
	}
	public String getId_evento() {
		return id_evento;
	}
	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public Integer getCod_curso() {
		return cod_curso;
	}
	public void setCod_curso(Integer cod_curso) {
		this.cod_curso = cod_curso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCant_horas() {
		return cant_horas;
	}
	public void setCant_horas(Integer cant_horas) {
		this.cant_horas = cant_horas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cant_horas == null) ? 0 : cant_horas.hashCode());
		result = prime * result
				+ ((cod_curso == null) ? 0 : cod_curso.hashCode());
		result = prime * result
				+ ((f_com_efec == null) ? 0 : f_com_efec.hashCode());
		result = prime * result
				+ ((f_fin_efec == null) ? 0 : f_fin_efec.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((id_evento == null) ? 0 : id_evento.hashCode());
		result = prime * result + ((libreta == null) ? 0 : libreta.hashCode());
		result = prime * result
				+ ((modalidad == null) ? 0 : modalidad.hashCode());
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
		Gpacurre other = (Gpacurre) obj;
		if (cant_horas == null) {
			if (other.cant_horas != null)
				return false;
		} else if (!cant_horas.equals(other.cant_horas))
			return false;
		if (cod_curso == null) {
			if (other.cod_curso != null)
				return false;
		} else if (!cod_curso.equals(other.cod_curso))
			return false;
		if (f_com_efec == null) {
			if (other.f_com_efec != null)
				return false;
		} else if (!f_com_efec.equals(other.f_com_efec))
			return false;
		if (f_fin_efec == null) {
			if (other.f_fin_efec != null)
				return false;
		} else if (!f_fin_efec.equals(other.f_fin_efec))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_evento == null) {
			if (other.id_evento != null)
				return false;
		} else if (!id_evento.equals(other.id_evento))
			return false;
		if (libreta == null) {
			if (other.libreta != null)
				return false;
		} else if (!libreta.equals(other.libreta))
			return false;
		if (modalidad == null) {
			if (other.modalidad != null)
				return false;
		} else if (!modalidad.equals(other.modalidad))
			return false;
		return true;
	}

}
