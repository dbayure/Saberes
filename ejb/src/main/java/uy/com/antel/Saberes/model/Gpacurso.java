package uy.com.antel.Saberes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "gpacurso")
public class Gpacurso implements Serializable {

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
	
	private String nombre;
	private String ext_int;
	private Integer cod_curso;
	private String cod_area;
	
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

	public String getExt_int() {
		return ext_int;
	}
	public void setExt_int(String ext_int) {
		this.ext_int = ext_int;
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
	public String getCod_area() {
		return cod_area;
	}
	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}
	
}
