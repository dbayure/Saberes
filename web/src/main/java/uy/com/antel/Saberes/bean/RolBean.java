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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import uy.com.antel.Saberes.controller.RegistroRol;
import uy.com.antel.Saberes.model.Rol;

@ManagedBean
@SessionScoped
public class RolBean {

	@Inject
	private RegistroRol registroRol;
	
	public void registrar() {
		try {
			if (registroRol.buscarRolRepetido()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el rol ingresado ya existe", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			else{ 
			registroRol.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol fue registrado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar el rol ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
            try {
            	if(newValue != null && !newValue.equals(oldValue)) {
            	    DataTable d = (DataTable) event.getSource();
            	    Rol rol = (Rol) d.getRowData();
            	    if ( event.getRowIndex() == 3){
            		    rol.setRol(newValue.toString());
            	    }
            	    else{
            		    rol.setDescripcion(newValue.toString());
            	    }
            		registroRol.modificar(rol);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el rol", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del rol " + ((Rol) event.getObject()).getRol(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		String nombreRol="";
		try {
			nombreRol = registroRol.buscar(id).getRol();
			registroRol.eliminar(id);
			
			FacesMessage msg = new FacesMessage("El rol " + nombreRol + " se eliminó correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar el rol "+ nombreRol, "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void buscar(Long id) {
		try {
			registroRol.buscar(id);
			FacesMessage msg = new FacesMessage("El rol " + id.toString() + " fue encontrado exitosamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("No fue posible encontrar el rol "+ id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
}
