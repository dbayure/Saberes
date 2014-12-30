package uy.com.antel.prueba_saberes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Saber")
public class Saber implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nombre;
		
		@ManyToOne(optional=false) 
	    @JoinColumn(name="idTipoSaber", nullable=false, updatable=false)
		private TipoSaber tipoSaber;
		
		@ManyToOne(optional=true) 
	    @JoinColumn(name="idInstitucion", nullable=true, updatable=false)
		private Institucion institucion;
		
		@Temporal(TemporalType.DATE)
		private Date añoPlan;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public TipoSaber getTipoSaber() {
			return tipoSaber;
		}

		public void setTipoSaber(TipoSaber tipoSaber) {
			this.tipoSaber = tipoSaber;
		}

		public Institucion getInstitucion() {
			return institucion;
		}

		public void setInstitucion(Institucion institucion) {
			this.institucion = institucion;
		}

		@Temporal(TemporalType.DATE)
		public Date getAñoPlan() {
			return añoPlan;
		}

		public void setAñoPlan(Date añoPlan) {
			this.añoPlan = añoPlan;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((añoPlan == null) ? 0 : añoPlan.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((institucion == null) ? 0 : institucion.hashCode());
			result = prime * result
					+ ((nombre == null) ? 0 : nombre.hashCode());
			result = prime * result
					+ ((tipoSaber == null) ? 0 : tipoSaber.hashCode());
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
			Saber other = (Saber) obj;
			if (añoPlan == null) {
				if (other.añoPlan != null)
					return false;
			} else if (!añoPlan.equals(other.añoPlan))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (institucion == null) {
				if (other.institucion != null)
					return false;
			} else if (!institucion.equals(other.institucion))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			if (tipoSaber == null) {
				if (other.tipoSaber != null)
					return false;
			} else if (!tipoSaber.equals(other.tipoSaber))
				return false;
			return true;
		}
		
		
		
}
