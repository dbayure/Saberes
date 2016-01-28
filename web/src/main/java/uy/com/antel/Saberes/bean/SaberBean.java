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

	private static final long serialVersionUID = 700416364811225450L;
	@Inject
	private RegistroSaber registroSaber;
	
	public void registrar() {
		try {
			if (registroSaber.buscarSaberRepetido()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error el saber ingresado ya existe", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			else{ 
			registroSaber.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber fue registrado correctamente ", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No fue posible registrar el saber ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Saber saber = ((Saber) event.getObject());
           
            try {
            	registroSaber.modificar(saber);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber " + saber.getNombre() + " fue modificado correctamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No fue posible modifcar el saber " + saber.getNombre(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del saber " + ((Saber) event.getObject()).getNombre(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroSaber.eliminar(id);
			FacesMessage msg = new FacesMessage("El saber "+ id.toString() + " fue eliminado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error no fue posible eliminar el saber "+ id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public ArrayList<Saber> buscarPorInstitucion(Institucion institucion, Long tipo){
		try {
			return registroSaber.buscarPorInstitucion(institucion.getId(),tipo);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("No se encontraron saberes para la institución: " + institucion.getNombre(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return null;
	}
	
	
}

