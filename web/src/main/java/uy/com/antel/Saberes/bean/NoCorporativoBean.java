package uy.com.antel.Saberes.bean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.model.Institucion;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Saber;

@ManagedBean
@RequestScoped
public class NoCorporativoBean {

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;
	
	private String text;
	
	@Inject
	PersonaBean persona;
	
    @Inject
	private SaberBean saberBean;
	
    private Institucion institucionSeleccionadaCurricular;
    private Institucion institucionSeleccionada;
    private Institucion institucionSeleccionadaCurso;
   
    private UploadedFile file;
    
    private ArrayList<Saber> saberPorInst;
    

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
	public void registrar() {
		try {
			String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			registroNoCorporativo.registro(usuario);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		NoCorporativo noCorporativo = ((NoCorporativo) event.getObject());
           
            try {
            	registroNoCorporativo.modificar(noCorporativo);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", noCorporativo.getSaber().getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", noCorporativo.getSaber().getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((NoCorporativo) event.getObject()).getSaber().getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(Long id) {
		try {
			registroNoCorporativo.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", id.toString());  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void saberesNoCorporativosPorTipo(Long tipo){
		ArrayList<Saber> resultado = new ArrayList<Saber>();
		
		if (tipo.longValue() == 2){
			resultado = this.saberBean.buscarPorInstitucion(this.institucionSeleccionada);
		}else if (tipo.longValue() == 1){
			resultado = this.saberBean.buscarPorInstitucion(this.institucionSeleccionadaCurricular);
		} else if (tipo.longValue() == 5){
			resultado = this.saberBean.buscarPorInstitucion(this.institucionSeleccionadaCurso);
		} 
		ArrayList <Saber> cursos = new ArrayList<Saber>();
		for (Saber s: resultado){ 
			if (s.getTipoSaber().getId() == tipo.longValue())
				cursos.add(s);
		}
      	saberPorInst = cursos;
	}

    public ArrayList<Saber> getSaberPorInst() {
		return saberPorInst;
	}

	public void setSaberPorInst(ArrayList<Saber> saberPorInst) {
		this.saberPorInst = saberPorInst;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Institucion getInstitucionSeleccionada() {
		return institucionSeleccionada;
	}

	public void setInstitucionSeleccionada(Institucion institucionSeleccionada) {
		this.institucionSeleccionada = institucionSeleccionada;
	}

	public Institucion getInstitucionSeleccionadaCurricular() {
		return institucionSeleccionadaCurricular;
	}

	public void setInstitucionSeleccionadaCurricular(
			Institucion institucionSeleccionadaCurricular) {
		this.institucionSeleccionadaCurricular = institucionSeleccionadaCurricular;
	}

	public Institucion getInstitucionSeleccionadaCurso() {
		return institucionSeleccionadaCurso;
	}

	public void setInstitucionSeleccionadaCurso(
			Institucion institucionSeleccionadaCurso) {
		this.institucionSeleccionadaCurso = institucionSeleccionadaCurso;
	}
}

