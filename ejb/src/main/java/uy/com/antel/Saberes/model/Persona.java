package uy.com.antel.Saberes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false) 
    @JoinColumn(name="idRol", nullable=false, updatable=false)
	private Rol rol;
	
	@OneToMany
	@JoinColumn(name="idSaberPersona", nullable=false, updatable=false)
	private List<SaberPersona> saberes;
	
	private String nombre;
	private String apellido;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private String sexo;
	private String clase;
	private String jornadaLaboral;
	private String departamento;
	private String localidad;
	private String correo;
	private String foto;
	private String unidad;
	private String piso;
	private String oficina;
	private String telDirecto;
	private String telInterno;
	private String telCelular;
	private String Fax;
	
	public Persona(){
		saberes = new ArrayList<SaberPersona>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol idRol) {
		this.rol = idRol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getJornadaLaboral() {
		return jornadaLaboral;
	}
	public void setJornadaLaboral(String jornadaLaboral) {
		this.jornadaLaboral = jornadaLaboral;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getTelDirecto() {
		return telDirecto;
	}
	public void setTelDirecto(String telDirecto) {
		this.telDirecto = telDirecto;
	}
	public String getTelInterno() {
		return telInterno;
	}
	public void setTelInterno(String telInterno) {
		this.telInterno = telInterno;
	}
	public String getTelCelular() {
		return telCelular;
	}
	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public List<SaberPersona> getSaberes() {
		return saberes;
	}

	public void setSaberes(List<SaberPersona> saberes) {
		this.saberes = saberes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Fax == null) ? 0 : Fax.hashCode());
		result = prime * result
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result
				+ ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result
				+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result
				+ ((jornadaLaboral == null) ? 0 : jornadaLaboral.hashCode());
		result = prime * result
				+ ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((oficina == null) ? 0 : oficina.hashCode());
		result = prime * result + ((piso == null) ? 0 : piso.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result
				+ ((telCelular == null) ? 0 : telCelular.hashCode());
		result = prime * result
				+ ((telDirecto == null) ? 0 : telDirecto.hashCode());
		result = prime * result
				+ ((telInterno == null) ? 0 : telInterno.hashCode());
		result = prime * result + ((unidad == null) ? 0 : unidad.hashCode());
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
		Persona other = (Persona) obj;
		if (Fax == null) {
			if (other.Fax != null)
				return false;
		} else if (!Fax.equals(other.Fax))
			return false;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (jornadaLaboral == null) {
			if (other.jornadaLaboral != null)
				return false;
		} else if (!jornadaLaboral.equals(other.jornadaLaboral))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (oficina == null) {
			if (other.oficina != null)
				return false;
		} else if (!oficina.equals(other.oficina))
			return false;
		if (piso == null) {
			if (other.piso != null)
				return false;
		} else if (!piso.equals(other.piso))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (telCelular == null) {
			if (other.telCelular != null)
				return false;
		} else if (!telCelular.equals(other.telCelular))
			return false;
		if (telDirecto == null) {
			if (other.telDirecto != null)
				return false;
		} else if (!telDirecto.equals(other.telDirecto))
			return false;
		if (telInterno == null) {
			if (other.telInterno != null)
				return false;
		} else if (!telInterno.equals(other.telInterno))
			return false;
		if (unidad == null) {
			if (other.unidad != null)
				return false;
		} else if (!unidad.equals(other.unidad))
			return false;
		return true;
	}
	

}
