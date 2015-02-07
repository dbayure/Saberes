package uy.com.antel.Saberes.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import uy.com.antel.Saberes.controller.RegistroSaber;
import uy.com.antel.Saberes.model.Institucion;
import uy.com.antel.Saberes.model.Saber;

@ManagedBean
@RequestScoped
public class SaberBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 700416364811225450L;
	@Inject
	private RegistroSaber registroSaber;
	
	public void registrar() {
		try {
			registroSaber.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Saber saber = ((Saber) event.getObject());
           
            try {
            	registroSaber.modificar(saber);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", saber.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", saber.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Saber) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroSaber.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public ArrayList<Saber> buscarPorInstitucion(Institucion institucion){
		try {
			return registroSaber.buscarPorInstitucion(institucion.getId());
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("No se encontraron saberes para ", institucion.getNombre());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return null;
	}
	
	
}

