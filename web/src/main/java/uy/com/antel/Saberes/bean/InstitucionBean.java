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

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import uy.com.antel.Saberes.controller.RegistroInstitucion;
import uy.com.antel.Saberes.model.Institucion;

@ManagedBean
@RequestScoped
@ServletSecurity(
@HttpConstraint(rolesAllowed = {"BASICO"}))
public class InstitucionBean {

	@Inject
	private RegistroInstitucion registroInstitucion;
	
	public void registrar() {
		try {
			if (registroInstitucion.buscarInstitucionRepetida()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error la institución ingresada ya existe", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			else{ 
			registroInstitucion.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Institución registrada con éxito ", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar la institución", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
            try {
            	if(newValue != null && !newValue.equals(oldValue)) {
            	    DataTable d = (DataTable) event.getSource();
            	    Institucion institucion = (Institucion) d.getRowData();
            	    registroInstitucion.modificar(institucion);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La institución fue modificada exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar la institución", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación de la institución ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		String nombreInstitucion="";
		try {
			nombreInstitucion = registroInstitucion.buscar(id).getNombre();
			registroInstitucion.eliminar(id);
			FacesMessage msg = new FacesMessage("La institución " + nombreInstitucion + " fue eliminada exitosamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar la institución " + nombreInstitucion, "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public List<Institucion> obtenerInstitucionesFiltradas(String letra){
		List<Institucion> instituciones = registroInstitucion.ObtenerInsituciones(letra);
		return instituciones;
	}
}

