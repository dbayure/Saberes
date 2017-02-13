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
package uy.com.antel.Saberes.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import uy.com.antel.Saberes.controller.RegistroSaber;
import uy.com.antel.Saberes.model.Institucion;
import uy.com.antel.Saberes.model.Saber;
import uy.com.antel.Saberes.model.TipoSaber;

@ManagedBean
@RequestScoped
public class SaberBean implements Serializable {

	private static final long serialVersionUID = 700416364811225450L;
	@Inject
	private RegistroSaber registroSaber;
	private boolean mostrarPlan;
	private boolean mostrarInstitucion;

	public void registrar() {
		try {
			if (registroSaber.buscarSaberRepetido(registroSaber.getNewSaber().getNombre())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el saber ingresado ya existe",
						"");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				registroSaber.registro();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"El saber fue registrado correctamente ", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No fue posible registrar el saber ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onEdit(RowEditEvent event) {
		Saber saber = ((Saber) event.getObject());

		try {
			if (registroSaber.buscarSaberRepetido(saber.getNombre())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el saber ingresado ya existe",
						"");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				registroSaber.modificar(saber);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"El saber " + saber.getNombre() + " fue modificado correctamente", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No fue posible modificar el saber " + saber.getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(
				"Se canceló la modificación del saber " + ((Saber) event.getObject()).getNombre(), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void eliminar(Long id) {
		try {
			registroSaber.eliminar(id);
			FacesMessage msg = new FacesMessage("El saber " + id.toString() + " fue eliminado correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error no fue posible eliminar el saber " + id.toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public ArrayList<Saber> buscarPorInstitucion(Institucion institucion, Long tipo) {
		try {
			return registroSaber.buscarPorInstitucion(institucion.getId(), tipo);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(
					"No se encontraron saberes para la institución: " + institucion.getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return null;
	}

	// procedimiento que se utiliza para ocultar campos según el tipo de saber
	// en el alta de saber
	public void rellenarListener(ValueChangeEvent changeEvent) {

		String tipoSaber = ((TipoSaber) changeEvent.getNewValue()).getNombre().toString();

		// Formal curricular o Formal no corporativo o No Formal Cursos
		if (tipoSaber != null && (tipoSaber.startsWith("Formal Curricular")
				|| tipoSaber.startsWith("Formal No Corporativo") || tipoSaber.startsWith("No Formal Cursos"))) {
			setMostrarPlan(true);
			setMostrarInstitucion(true);
		} else {
			setMostrarPlan(false);
			setMostrarInstitucion(false);
		}
	}

	public boolean getMostrarPlan() {
		return mostrarPlan;
	}

	public void setMostrarPlan(boolean mostrarPlan) {
		this.mostrarPlan = mostrarPlan;
	}

	public boolean getMostrarInstitucion() {
		return mostrarInstitucion;
	}

	public void setMostrarInstitucion(boolean mostrarInstitucion) {
		this.mostrarInstitucion = mostrarInstitucion;
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		try {
			if (newValue != null && !newValue.equals(oldValue)) {
				DataTable d = (DataTable) event.getSource();
				Saber saber = (Saber) d.getRowData();
				boolean repetido = registroSaber.buscarSaberRepetido(saber.getNombre());
				if (repetido) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el saber ingresado ya existe",
							"");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					registroSaber.modificar(saber);
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber fue modificado exitosamente", "");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el saber", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
