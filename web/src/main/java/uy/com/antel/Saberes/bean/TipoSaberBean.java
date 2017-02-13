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

import uy.com.antel.Saberes.controller.RegistroTipoSaber;
import uy.com.antel.Saberes.model.TipoSaber;

@ManagedBean
@RequestScoped
public class TipoSaberBean {

	@Inject
	private RegistroTipoSaber registroTipoSaber;
	
	public void registrar() {
		try {
			registroTipoSaber.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de saber fue registrado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar el tipo de saber ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		TipoSaber tipoSaber = ((TipoSaber) event.getObject());
           
            try {
            	registroTipoSaber.modificar(tipoSaber);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de saber " + tipoSaber.getNombre() + " fue modificado correctamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible modificar el tipo de saber " + tipoSaber.getNombre(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del tipo de saber " + ((TipoSaber) event.getObject()).getNombre(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		String nombreTipoSaber="";
		try {
			nombreTipoSaber = registroTipoSaber.buscar(id).getNombre();
			registroTipoSaber.eliminar(id);
			FacesMessage msg = new FacesMessage("El tipo de saber " + nombreTipoSaber + " fue eliminado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error no fue posible eliminar el tipo de saber " + nombreTipoSaber, "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
            try {
            	if(newValue != null && !newValue.equals(oldValue)) {
            	    DataTable d = (DataTable) event.getSource();
            	    TipoSaber ts = (TipoSaber) d.getRowData();
            	    registroTipoSaber.modificar(ts);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de saber fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el tipo de saber", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
}

