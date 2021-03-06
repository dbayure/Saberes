/*
SABERES - Registro de conocimientos, aptitudes del personal de la empresa
Copyright (C) 2009  ANTEL
This file is part of SABERES.
SABERES is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 
*/
package uy.com.antel.Saberes.model;

import java.io.Serializable;
import java.text.ParseException;
import javax.persistence.Entity;
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
	private Integer duracion;
	private String libreta;
	private String rolSaber;
	private Integer idEventoGicca;
	private Integer asistencia;
	private String cod_area;

	public Corporativo() {
		super();
	}
	
	public Integer getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	public Integer getDuracion() {
		return duracion;
	}
	
	public String getDuracionStr() throws ParseException {
		String duracionFormat = String.valueOf(duracion);
		if(duracionFormat.length()>3){
			String hora = duracionFormat.substring(0, 2);
			String min = duracionFormat.substring(2);
			return hora + ":" + min + " Hs";
		}
		else{
			if(duracionFormat.length()==3){
				String hora = duracionFormat.substring(0, 1);
				String min = duracionFormat.substring(1);
				return hora + ":" + min + " Hs";
			}
			if(duracion>0)
				return duracionFormat.substring(0, 2) + " Min";
			else
				return "0";
		}
	}

	
	public void setDuracion(Integer duracion) {
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

	public String getCod_area() {
		return cod_area;
	}

	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((asistencia == null) ? 0 : asistencia.hashCode());
		result = prime * result
				+ ((cod_area == null) ? 0 : cod_area.hashCode());
		result = prime * result
				+ ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result
				+ ((idEventoGicca == null) ? 0 : idEventoGicca.hashCode());
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
		if (cod_area == null) {
			if (other.cod_area != null)
				return false;
		} else if (!cod_area.equals(other.cod_area))
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
