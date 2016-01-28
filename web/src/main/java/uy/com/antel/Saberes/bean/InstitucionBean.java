package uy.com.antel.Saberes.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;

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
	
	public void onEdit(RowEditEvent event) {  
            Institucion institucion = ((Institucion) event.getObject());
           
            try {
            	registroInstitucion.modificar(institucion);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La institución " + institucion.getNombre() + " fue modificada con éxito", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error la institución " + institucion.getNombre() + " no pudo ser modificada","");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló la modificación de la institución ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroInstitucion.eliminar(id);
			FacesMessage msg = new FacesMessage("La institución " + id.toString() + " fue eliminada exitosamente", "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar la institución " + id.toString(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public List<Institucion> obtenerInstitucionesFiltradas(String letra){
		List<Institucion> instituciones = registroInstitucion.ObtenerInsituciones(letra);
		return instituciones;
	}
}

