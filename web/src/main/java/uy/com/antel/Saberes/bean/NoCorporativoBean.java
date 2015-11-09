package uy.com.antel.Saberes.bean;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class NoCorporativoBean {

	private Institucion institucionSeleccionadaCurricular;
	private Institucion institucionSeleccionada;
	private Institucion institucionSeleccionadaCurso;
	private String nombreArchivo;
	private UploadedFile uf;

	private ArrayList<Saber> saberPorInst;

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;

	@Inject
	private RegistroComprobante registroComprobante;

	@Inject
	PersonaBean persona;

	@Inject
	private SaberBean saberBean;

	public void registrar() {
		try {
			String usuario = FacesContext.getCurrentInstance()
					.getExternalContext().getRemoteUser();
			System.out.println("valor del usuario a registrar " + usuario);
			registroNoCorporativo.registro(usuario);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se registró ", "con éxito!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Error al registrar ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onEdit(RowEditEvent event) {
		NoCorporativo noCorporativo = ((NoCorporativo) event.getObject());

		try {
			registroNoCorporativo.modificar(noCorporativo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se modificó ", noCorporativo.getSaber().getNombre());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Error al modificar ", noCorporativo.getSaber().getNombre());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló modificar ",
				((NoCorporativo) event.getObject()).getSaber().getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void eliminar(Long id) {
		try {
			registroNoCorporativo.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", id.toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar",
					id.toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void saberesNoCorporativosPorTipo(Long tipo) {
		if (tipo.longValue() == 2) {
			saberPorInst = this.saberBean.buscarPorInstitucion(
					this.institucionSeleccionada, tipo);
		} else if (tipo.longValue() == 1) {
			saberPorInst = this.saberBean.buscarPorInstitucion(
					this.institucionSeleccionadaCurricular, tipo);
		} else if (tipo.longValue() == 5) {
			saberPorInst = this.saberBean.buscarPorInstitucion(
					this.institucionSeleccionadaCurso, tipo);
		}
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

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		registroComprobante.getNewComprobante().setUploadedFile(
				uf.getContents());
		registroComprobante.getNewComprobante().setNombre(uf.getFileName());
		;
		this.uf = uf;
	}

}
