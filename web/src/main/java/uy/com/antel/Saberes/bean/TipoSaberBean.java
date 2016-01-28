package uy.com.antel.Saberes.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
		try {
			registroTipoSaber.eliminar(id);
			FacesMessage msg = new FacesMessage("El tipo de saber " + id.toString() + " fue eliminado correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error no fue posible eliminar el tipo de saber " + id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
}

