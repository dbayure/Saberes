package uy.com.antel.Saberes.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol fue registador correctamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar el rol ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
            Rol rol = ((Rol) event.getObject());
           
            try {
            	registroRol.modificar(rol);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol " + rol.getRol() + " fue modificado exitosamente", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al intentar modificar el rol " + rol.getRol(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación del rol " + ((Rol) event.getObject()).getRol(), "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroRol.eliminar(id);
			FacesMessage msg = new FacesMessage("El rol " + id.toString() + " se eliminó correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar el rol " + id.toString(), "");  
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
