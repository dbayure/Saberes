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
@Table(name = "gpaparev")
public class Gpaparev implements Serializable {

	/** *  */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cedula;
	private String id_evento;
	private String aprobacion;
	private Integer puntaje;
	private Integer cant_asistencia;
	private String usid;
	private Date fecha_act;
	private Date hora_act;
	private String tipo_evento;
	private String evaluado;
	private String modalidad;
	private String rol;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	public String getId_evento() {
		return id_evento;
	}
	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}
	public String getAprobacion() {
		return aprobacion;
	}
	public void setAprobacion(String aprobacion) {
		this.aprobacion = aprobacion;
	}
	public Integer getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	public Integer getCant_asistencia() {
		return cant_asistencia;
	}
	public void setCant_asistencia(Integer cant_asistencia) {
		this.cant_asistencia = cant_asistencia;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public Date getFecha_act() {
		return fecha_act;
	}
	public void setFecha_act(Date fecha_act) {
		this.fecha_act = fecha_act;
	}
	public Date getHora_act() {
		return hora_act;
	}
	public void setHora_act(Date hora_act) {
		this.hora_act = hora_act;
	}
	public String getTipo_evento() {
		return tipo_evento;
	}
	public void setTipo_evento(String tipo_evento) {
		this.tipo_evento = tipo_evento;
	}
	public String getEvaluado() {
		return evaluado;
	}
	public void setEvaluado(String evaluado) {
		this.evaluado = evaluado;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aprobacion == null) ? 0 : aprobacion.hashCode());
		result = prime * result
				+ ((cant_asistencia == null) ? 0 : cant_asistencia.hashCode());
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result
				+ ((evaluado == null) ? 0 : evaluado.hashCode());
		result = prime * result
				+ ((fecha_act == null) ? 0 : fecha_act.hashCode());
		result = prime * result
				+ ((hora_act == null) ? 0 : hora_act.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((id_evento == null) ? 0 : id_evento.hashCode());
		result = prime * result
				+ ((modalidad == null) ? 0 : modalidad.hashCode());
		result = prime * result + ((puntaje == null) ? 0 : puntaje.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result
				+ ((tipo_evento == null) ? 0 : tipo_evento.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
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
		Gpaparev other = (Gpaparev) obj;
		if (aprobacion == null) {
			if (other.aprobacion != null)
				return false;
		} else if (!aprobacion.equals(other.aprobacion))
			return false;
		if (cant_asistencia == null) {
			if (other.cant_asistencia != null)
				return false;
		} else if (!cant_asistencia.equals(other.cant_asistencia))
			return false;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (evaluado == null) {
			if (other.evaluado != null)
				return false;
		} else if (!evaluado.equals(other.evaluado))
			return false;
		if (fecha_act == null) {
			if (other.fecha_act != null)
				return false;
		} else if (!fecha_act.equals(other.fecha_act))
			return false;
		if (hora_act == null) {
			if (other.hora_act != null)
				return false;
		} else if (!hora_act.equals(other.hora_act))
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
		if (modalidad == null) {
			if (other.modalidad != null)
				return false;
		} else if (!modalidad.equals(other.modalidad))
			return false;
		if (puntaje == null) {
			if (other.puntaje != null)
				return false;
		} else if (!puntaje.equals(other.puntaje))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (tipo_evento == null) {
			if (other.tipo_evento != null)
				return false;
		} else if (!tipo_evento.equals(other.tipo_evento))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}

}
