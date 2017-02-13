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

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import uy.com.antel.Saberes.controller.RegistroSaberPersona;
import uy.com.antel.Saberes.model.SaberPersona;

@ManagedBean
@RequestScoped
public class SaberPersonaBean {

	@Inject
	private RegistroSaberPersona registroSaberPersona;
	
    private UploadedFile file;
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Exito el archivo " + file.getFileName() + " fue subido correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
	public void registrar() {
		try {
			registroSaberPersona.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber de la persona fue registrado correctamente ", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar el saber de la persona ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		SaberPersona saberPersona = ((SaberPersona) event.getObject());         
            try {
            	registroSaberPersona.modificar(saberPersona);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber de la persona " + saberPersona.getSaber().getNombre() + " fue modificado correctamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al intentar modificar el saber de la persona " + saberPersona.getSaber().getNombre(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del saber de la persona " + ((SaberPersona) event.getObject()).getSaber().getNombre(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroSaberPersona.eliminar(id);
			FacesMessage msg = new FacesMessage("El saber de la persona " + id.toString() + " fue eliminado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error no fue posible eliminar el saber de la persona " +  id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


}

