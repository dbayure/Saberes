package uy.com.antel.Saberes.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
		try {
			registroOrigen.eliminar(id);
			FacesMessage msg = new FacesMessage("El origen " + id.toString() + " se eliminó correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar el origen " + id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
}

