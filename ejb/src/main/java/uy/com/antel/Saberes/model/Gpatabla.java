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
@Table(name = "gpatabla")
public class Gpatabla implements Serializable {

	/** *  */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codtab;
	private String cvalor;
	private String dvalor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodtab() {
		return codtab;
	}
	public void setCodtab(String codtab) {
		this.codtab = codtab;
	}
	public String getCvalor() {
		return cvalor;
	}
	public void setCvalor(String cvalor) {
		this.cvalor = cvalor;
	}
	public String getDvalor() {
		return dvalor;
	}
	public void setDvalor(String dvalor) {
		this.dvalor = dvalor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
