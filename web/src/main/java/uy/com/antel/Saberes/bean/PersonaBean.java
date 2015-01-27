package uy.com.antel.Saberes.bean;


import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Null;
import javax.xml.rpc.ServiceException;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import WebServices.sgp.antel.com.DatoPer;
import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.controller.RegistroPersona;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Rol;
import uy.com.antel.Saberes.model.SaberPersona;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerService;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerServiceLocator;

@ManagedBean
@RequestScoped
public class PersonaBean {

	private String usuario;
	
	@Inject
	private RegistroPersona registroPersona;
	
	@Inject
	private RegistroNoCorporativo registroNoCorporativo;
	
	private UploadedFile file;
	
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
	
	public void registrar() {
		FacesMessage msg;
	
		try {
			
			Persona aPersona;
			if (personaRegistrada())
				aPersona = registroPersona.buscarPersonaPorUsr(this.usuario);
			else
				aPersona = registroPersona.getNewPersona();
				
				
			DatoPer datos = obtenerDatosPersona(this.getUsuario());
			
			String apellido = datos.getPriape().trim()+" "+datos.getSegape().trim();
			String nombre = datos.getNombre().trim();
			Calendar fNacim = datos.getFNacim();
			Calendar fIngreso = datos.getFIngre();
			String tel = datos.getTDirecPer().trim();
			String telOf = datos.getOficina();
			String interno = datos.getTInterPer().trim();
			String clase = datos.getClasePago().trim();
			String descClase = datos.getDescClasePago().trim();
			String division = datos.getDivision();
			String area = datos.getDescArea().trim();
			String unidad = datos.getDescUnidad().trim();
			String situacion = datos.getDescCsilac().trim();
			String depto = datos.getDescDepartamento().trim();
			String direccion = datos.getDescEdificio().trim();
			String localidad = datos.getDescLocalidad().trim();
			String regimen = datos.getDescRegimen().trim();
			String fax = datos.getFaxUnidad();
			String piso = datos.getPlanta();
			String email = datos.getEmail();

			aPersona.setApellido(apellido);
			aPersona.setNombre(nombre);
			aPersona.setFechaIngreso(fIngreso.getTime());
			aPersona.setFechaNacimiento(fNacim.getTime());
			aPersona.setTelDirecto(tel);
			aPersona.setOficina(telOf);
			aPersona.setTelInterno(interno);
			aPersona.setClase(clase);
			aPersona.setDescClase(descClase);
			aPersona.setDivision(division);
			aPersona.setArea(area);
			aPersona.setUnidad(unidad);
			aPersona.setSituacion(situacion);
			aPersona.setDepartamento(depto);
			aPersona.setDireccion(direccion);
			aPersona.setLocalidad(localidad);
			aPersona.setRegimen(regimen);
			aPersona.setFax(fax);
			aPersona.setPiso(piso);
			aPersona.setCorreo(email);
			aPersona.setUsuario(this.usuario);
			
			if (personaRegistrada())
				registroPersona.modificar(aPersona);
			else
				registroPersona.registro();
			
			this.setPersonaBean(this.usuario);
			
//			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		 
	}
	
	public boolean personaRegistrada() {
		return (registroPersona.buscarPersonaPorUsr(this.usuario) != null);
	}

	private DatoPer obtenerDatosPersona(String usuario)  {
		
		WsDatosPerService service = new WsDatosPerServiceLocator();
		WsDatosPer llamada;
		try {
			llamada = service.getWSDatoPer();
			String sCedula = getCi(usuario);
			String sAppl = "SABERES";
			String sPass = "SABERES";
			
			return llamada.getDatosPer(sCedula, sAppl, sPass);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;


	}
	
	public void setPersonaBean (String usr ){
		registroPersona.setPersonaPorUsr(usr);
	}
	
	private String getCi(String ci) {
		List<String> conv = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k");
		if (ci.length() < 7){
			return "a"+ci.substring(1);
		}else{
			int primerLetra = conv.indexOf(ci.substring(0,1));
			return primerLetra + ci.substring(1); 
		}
		
	}

	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Rol) event.getObject()).getRol());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }

	public String getUsuario() {
		return usuario;
	}

	public boolean faltaValidar(long id){
		Persona p = registroPersona.encontrarPorId(id);
		boolean validar = false;
		int i = 0;
		for (SaberPersona sp : p.getSaberes()){
			if(sp instanceof NoCorporativo){
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				if(nc.isValidado()){
					i++;
				}
			}
		if (i > 0){
			validar =  true;
			}
		}
		return validar;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
