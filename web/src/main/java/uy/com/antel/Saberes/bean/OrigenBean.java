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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import uy.com.antel.Saberes.controller.RegistroOrigen;
import uy.com.antel.Saberes.model.Origen;

@ManagedBean
@RequestScoped
public class OrigenBean {

	@Inject
	private RegistroOrigen registroOrigen;
	
	public void registrar() {
		try {
			if (registroOrigen.buscarOrigenRepetido()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el origen ingresado ya existe", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			else{ 
			registroOrigen.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El origen fue registrado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar el origen ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
            Origen origen = ((Origen) event.getObject());
           
            try {
            	registroOrigen.modificar(origen);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El origen " + origen.getDescripcion() + " fue modificado exitosamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al intentar modificar el origen " + origen.getDescripcion(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del origen " + ((Origen) event.getObject()).getDescripcion(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		String nombreOrigen="";
		try {
			nombreOrigen = registroOrigen.buscar(id).getDescripcion();
			registroOrigen.eliminar(id);
			FacesMessage msg = new FacesMessage("El origen " + nombreOrigen + " se eliminó correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar el origen " + nombreOrigen, "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
	        try {
	        	if(newValue != null && !newValue.equals(oldValue)) {
	        	    DataTable d = (DataTable) event.getSource();
	        	    Origen o = (Origen) d.getRowData();
	        	    registroOrigen.modificar(o);
	            }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El origen fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el oigen", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
	}
}

