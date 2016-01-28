package uy.com.antel.Saberes.bean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import uy.com.antel.Saberes.controller.RegistroComprobante;
import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.model.Institucion;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Saber;

@ManagedBean
@ViewScoped
public class NoCorporativoBean {

	private Institucion institucionSeleccionadaCurricular;
	private Institucion institucionSeleccionada;
	private Institucion institucionSeleccionadaCurso;
	private UploadedFile uf;
	private long personaId;
	private boolean mostrarBtnComprobante = false;
	
	private ArrayList<Saber> saberPorInst;

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;

	@Inject
	private RegistroComprobante registroComprobante;

	@Inject
	PersonaBean persona;

	@Inject
	private SaberBean saberBean;

	public String registrar() {
		try {
			String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			registroNoCorporativo.registro(usuario);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,	"El saber fue registrado correctamente ", "");
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, msg);
		} 
		catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,	"No fue posible registrar el saber ", "");
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, msg);
		}
		return "persona.jsf?faces-redirect=true";
	}

	public void onEdit(RowEditEvent event) {
		NoCorporativo noCorporativo = ((NoCorporativo) event.getObject());
		try {
			//registroNoCorporativo.modificar(noCorporativo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El saber " + noCorporativo.getSaber().getNombre() + " fue modificado exitosamente", "" );
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
		catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No fue posible modificar el saber " + noCorporativo.getSaber().getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló la modificiación del saber " + ((NoCorporativo) event.getObject()).getSaber().getNombre(), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void eliminar(Long id) {
		try {
			registroNoCorporativo.eliminar(id);
			FacesMessage msg = new FacesMessage("El saber " + id.toString() + " fue eliminado correctamente ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
		catch (Exception e) {
			FacesMessage msg = new FacesMessage("No fue posible eliminar el saber " + id.toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void saberesNoCorporativosPorTipo(Long tipo) {
		if (tipo.longValue() == 2) {
			saberPorInst = this.saberBean.buscarPorInstitucion(this.institucionSeleccionada, tipo);
		} 
		else if (tipo.longValue() == 1) {
			saberPorInst = this.saberBean.buscarPorInstitucion(this.institucionSeleccionadaCurricular, tipo);
		} 
			else if (tipo.longValue() == 5) {
				saberPorInst = this.saberBean.buscarPorInstitucion(this.institucionSeleccionadaCurso, tipo);
			}
	}
	
	public void asignarIdBusqueda(long id){
		personaId = id;
	}
	
	public ArrayList<Saber> getSaberPorInst() {
		return saberPorInst;
	}

	public void setSaberPorInst(ArrayList<Saber> saberPorInst) {
		this.saberPorInst = saberPorInst;
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

	public UploadedFile getUf() {
		return uf;
	}
	
	public void setUf(UploadedFile uf) {
		registroComprobante.getNewComprobante().setUploadedFile(uf.getContents());
		registroComprobante.getNewComprobante().setNombre(uf.getFileName());
		this.uf = uf;
	}
	
	public long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(long personaId) {
		this.personaId = personaId;
	}

	public void mostrarBotonComprobante(long id){
		setMostrarBtnComprobante(registroNoCorporativo.ExisteComprobantePorId(id));
	}

	public boolean isMostrarBtnComprobante() {
		return mostrarBtnComprobante;
	}

	public void setMostrarBtnComprobante(boolean mostrarBtnComprobante) {
		this.mostrarBtnComprobante = mostrarBtnComprobante;
	}
}
