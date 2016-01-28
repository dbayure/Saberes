package uy.com.antel.Saberes.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.xml.rpc.ServiceException;
import org.jboss.security.SecurityContextAssociation;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import WebServices.sgp.antel.com.DatoPer;
import uy.com.antel.Saberes.controller.RegistroCorporativo;
import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.controller.RegistroPersona;
import uy.com.antel.Saberes.controller.RegistroRol;
import uy.com.antel.Saberes.data.ConocimientosListProducer;
import uy.com.antel.Saberes.model.Comprobante;
import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Rol;
import uy.com.antel.Saberes.model.Saber;
import uy.com.antel.Saberes.model.SaberPersona;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerService;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerServiceLocator;

@ManagedBean
@SessionScoped
public class PersonaBean {

	private String usuario;
	private long idPersona;
	private String rutaPDF;
	private String rutaIMG;
	protected boolean mostrar = false;
	private String motivoRechazo;
	private int btnRechazo = -1;
	private int btnComentario = -1;
	private boolean gridRechazo = false;
	private StreamedContent fotoUsuario;
	private StreamedContent streamedContent;
	private int idFilaActualizar;
	private List<NoCorporativo> listaNoCorporativoPersona = new ArrayList<NoCorporativo>();
	private Comprobante comprobCursos;
	List<Corporativo> listacorp;
	private List<Persona> listaPersonas = new ArrayList<Persona>();
	private Rol rol = null;
	private int timeout;
	
	@Inject
	private ConocimientosListProducer listaConocimientos;
	
	@Inject
	private RegistroPersona registroPersona;

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;

	@Inject
	private RegistroCorporativo registroCorporativo;
	
	@Inject
	private RegistroRol registroRol;
	
	List<Persona> listPersonas;
	
	private List<Saber> allSaberes;

	private UploadedFile file;
    
    public int getTimeout() {
        return timeout;
    }
 
    public void increment() {
        timeout++;
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

			String apellido = datos.getPriape().trim() + " "+ datos.getSegape().trim();
			String nombre = datos.getNombre().trim();
			Calendar fNacim = datos.getFNacim();
			Calendar fIngreso = datos.getFIngre();
			String tel = datos.getTDirecPer().trim();
			String Of = datos.getOficina();
			String interno = datos.getTInterPer().trim();
			String clase = datos.getClasePago().trim();
			String descClase = datos.getDescClasePago().trim();
			String division = datos.getDivision();
			String descdivision = datos.getDescDivision();			
			String area = datos.getDescArea().trim();
			String unidad = datos.getDescUnidad().trim();
			String situacion = datos.getDescCsilac().trim();
			String depto = datos.getDescDepartamento().trim();
			String direccion = datos.getDescEdificio().trim();
			String localidad = datos.getDescLocalidad().trim();
			String regimen = datos.getDescRegimen().trim();
			String fax = datos.getFaxUnidad().trim();
			String piso = datos.getPlanta().trim();
			String email = datos.getEmail().trim();
			String profesion = datos.getDescProfesion().trim();
			String sexo = datos.getSexo().trim();
			String jornada = datos.getDescJornada().trim();
			
			aPersona.setApellido(apellido);
			aPersona.setNombre(nombre);
			aPersona.setFechaIngreso(fIngreso.getTime());
			aPersona.setFechaNacimiento(fNacim.getTime());
			aPersona.setTelDirecto(tel);
			aPersona.setOficina(Of);
			aPersona.setTelInterno(interno);
			aPersona.setClase(clase);
			aPersona.setDescClase(descClase);
			aPersona.setDivision(division);
			aPersona.setDescDivision(descdivision);
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
			aPersona.setSexo(sexo);
			aPersona.setProfesion(profesion);
			aPersona.setJornadaLaboral(jornada);
			aPersona.setUsuario(this.usuario);

			if (personaRegistrada()){
				registroPersona.modificar(aPersona);
			}
			else{
				registroPersona.registro();
			}
			this.setPersonaBean(this.usuario);

		}

		catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no fue posible registrar a la persona ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public boolean personaRegistrada() {
		return (registroPersona.buscarPersonaPorUsr(this.usuario) != null);
	}

	private DatoPer obtenerDatosPersona(String usuario) {

		WsDatosPerService service = new WsDatosPerServiceLocator();
		WsDatosPer llamada;
		try {
			llamada = service.getWSDatoPer();
			String sCedula = getCi(usuario);
			String sAppl = "SABERES";
			String sPass = "SABERES";
			DatoPer dp = llamada.getDatosPer(sCedula, sAppl, sPass);
			return dp;
		} catch (ServiceException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error intentando conectarse al dominio ", "");  
            FacesContext.getCurrentInstance().addMessage(null, msg); 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void setPersonaBean(String usr) {
		registroPersona.setPersonaPorUsr(usr);
	}

	public void upload(FileUploadEvent event) {
		String userName = SecurityContextAssociation.getPrincipal().getName();
	    String rutaArchivoUsuario = rutaIMG+userName+".jpg";
		FacesMessage msg = new FacesMessage("La imagen fue actualizada correctamente " + event.getFile().getFileName(), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			registroPersona.copyFile(rutaArchivoUsuario, event.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getCi(String ci) {
		List<String> conv = Arrays.asList("a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k");
		if (ci.length() < 7) {
			return "a" + ci.substring(1);
		} else {
			int primerLetra = conv.indexOf(ci.substring(0, 1));
			return primerLetra + ci.substring(1);
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló la modificación de: " + ((Persona) event.getObject()).getNombre() + " "+((Persona) event.getObject()).getApellido(), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onEdit(RowEditEvent event) {  
		Persona persona = ((Persona) event.getObject());
            try {
            	if (rol == null){
            		registroPersona.modificar(persona);
            	}
            	else{
            		registroPersona.modificarRol(persona, rol);
            	}
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol del usuario " + persona.getNombre()+" "+persona.getApellido() + " fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el rol del usuario " + persona.getNombre()+" "+persona.getApellido(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onEditField(ValueChangeEvent event) {  
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		p.setTelCelular((String) event.getNewValue());
           
            try {
            	registroPersona.modificar(p);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos del teléfono celular fueron modificados correctamente ","");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el número de telefono celular", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public List<NoCorporativo> obtenerSaberesPersona(long id) {
		Persona p = registroPersona.encontrarPorId(id);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			if (sp instanceof NoCorporativo) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				if (nc.getValidado() == 'P') {
					listanc.add(nc);
				}
			}
		}
		return listanc;
	}

	public List<NoCorporativo> obtenerNoCorporativos() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("Formal No Corporativo")) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}

	public List<Corporativo> obtenerCorporativos() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		if (this.listacorp == null){
			this.listacorp = new ArrayList<Corporativo>();
			for (SaberPersona sp : p.getSaberes()) {
				String ts = sp.getSaber().getTipoSaber().getNombre();
				if (ts.contains("Formal Corporativo")) {
					Corporativo nc = registroCorporativo.obtenerCorpPorID(sp.getId());
					this.listacorp.add(nc);
				}
			}
		}
		return listacorp;
	}

	public List<NoCorporativo> obtenerCurriculares() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("Formal Curricular")) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}

	public List<NoCorporativo> obtenerCursos() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("No Formal Cursos")) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}

	public List<NoCorporativo> obtenerConocimientos() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("No Formal Conocimientos")) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}
	
	public void obtenerRolUsuarioLogueado(){
		String userName = null;
		try {
			userName = SecurityContextAssociation.getPrincipal().getName();
			Persona p = registroPersona.buscarPersonaPorUsr(userName);
			setRol(p.getRol());
		} catch (NullPointerException e) {
			setRol(null);
		}
	}
	
	public List<Persona> listaPersonasPorValidar(){
		List<Persona> listPersonasPronta = registroPersona.buscarPersonaPendienteValidar();
//		List<Persona> listPersonasPronta = new ArrayList<Persona>();
//		for (Persona per : listPersonasValidar) {
//			if(faltaValidar(per.getId()) == true){
//				listPersonasPronta.add(per);
//				}
//		}
		return listPersonasPronta;
	}

	public String convertirValidacion(char var) {
		if (var == 'P')
			return "Pendiente de Validación";
		else if (var == 'V')
			return "Validado";
		else
			return "No Validado";

	}

	public String convertirAprobacion(boolean aprobacion) {
		if (aprobacion == true)
			return "Aprobado";
		else
			return "No Aprobado";
	}

	public boolean faltaValidar(long id) {
		boolean falta = false;
		if (obtenerSaberesPersona(id).size() > 0) {
			falta = true;
		}
		return falta;
	}

	public void validarUnaPersona(long id) {
		listaNoCorporativoPersona = obtenerSaberesPersona(id);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario.toLowerCase();
	}
	
	public String getUsuario() {
		return usuario;
	}

	public List<NoCorporativo> getListaNoCorporativoPersona() {
		return listaNoCorporativoPersona;
	}

	public void setListaNoCorporativoPersona(
			List<NoCorporativo> listaNoCorporativoPersona) {
		this.listaNoCorporativoPersona = listaNoCorporativoPersona;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public void asignarId(long id) {
		this.idPersona = id;
		obtenerRuta();
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	@PostConstruct
	public void obtenerRuta() {
		Properties p = new Properties();
		String archivoPropiedades = System.getProperty("user.dir") + "/Conf/app-properties/saberes.properties";
		try {
			p.load(new FileInputStream(archivoPropiedades));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rutaPDF = p.getProperty("urlPDF");
		rutaIMG = p.getProperty("urlIMG");
	}


	public void abrirPdf(long idComprob) {
		try {
			String rutaArchivoPDF = rutaPDF + idComprob + ".pdf";
			FileInputStream fis = new FileInputStream(rutaArchivoPDF);
			setStreamedContent(new DefaultStreamedContent(fis,"application/pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validarNoCorporativo(long idNoCorporativo, long idPersona) {

		Persona p = registroPersona.encontrarPorId(idPersona);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		List<SaberPersona> listanc2 = new ArrayList<SaberPersona>();
		for (SaberPersona sp : p.getSaberes()) {
			if (sp instanceof NoCorporativo) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		for (NoCorporativo ncorp : listanc) {
			if (ncorp.getId().equals(idNoCorporativo)) {
				ncorp.setValidado('V');
				try {
					registroNoCorporativo.modificar(ncorp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			listanc2.add(ncorp);
		}
		p.setSaberes(listanc2);
		try {
			registroPersona.modificar(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rechazarNoCorporativo(long idNoCorporativo, long idPersona) {
		Persona p = registroPersona.encontrarPorId(idPersona);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		List<SaberPersona> listanc2 = new ArrayList<SaberPersona>();
		for (SaberPersona sp : p.getSaberes()) {
			if (sp instanceof NoCorporativo) {
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp.getId());
				listanc.add(nc);
			}
		}
		for (NoCorporativo ncorp : listanc) {
			if (ncorp.getId().equals(idNoCorporativo)) {
				ncorp.setValidado('R');
				ncorp.setMensaje(motivoRechazo);
				try {
					registroNoCorporativo.modificar(ncorp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			listanc2.add(ncorp);
		}
		p.setSaberes(listanc2);
		try {
			registroPersona.modificar(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarbtnRechazo() {
		btnRechazo = idFilaActualizar;
		btnComentario = idFilaActualizar;
	}

	public void mostrarGridRechazo() {
		gridRechazo = true;
	}

	public void motivoRechazoNoCorporativo(long idNoCorporativo) {
		NoCorporativo nc = registroNoCorporativo.obtenerPorID(idNoCorporativo);
		setMotivoRechazo(nc.getMensaje());
		mostrarGridRechazo();
	}

	public String getRutaIMG() {
		obtenerRuta();
		return rutaIMG;
	}

	public void setRutaIMG(String rutaIMG) {
		this.rutaIMG = rutaIMG;
	}

	public String getRutaPDF() {
		obtenerRuta();
		return rutaPDF;
	}

	public void setRutaPDF(String rutaPDF) {
		this.rutaPDF = rutaPDF;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setFotoUsuario(StreamedContent foto) {
		this.fotoUsuario = foto;
	}
	
	public StreamedContent getFotoUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		try {

			BufferedImage bufferedImg = null;
			String rutaArchivoUsuario = rutaIMG + userName + ".jpg";
			try {
				bufferedImg = ImageIO.read(new java.io.File(rutaArchivoUsuario));
			} catch (IOException e) {
				String cod_foto = String.valueOf((registroPersona.getCI(userName)*7+327));
				URL url = new URL("http://sgp.in.iantel.com.uy/fotos/"+cod_foto+".JPG");
				bufferedImg = ImageIO.read(url);
			}

			if (bufferedImg != null) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(bufferedImg, "jpg", os);
				setFotoUsuario(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()),"image/jpg"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fotoUsuario;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public boolean isGridRechazo() {
		return gridRechazo;
	}

	public void setGridRechazo(boolean gridRechazo) {
		this.gridRechazo = gridRechazo;
	}

	public int getIdFilaActualizar() {
		return idFilaActualizar;
	}
	
	public void setIdFilaActualizar(int idFilaActualizar) {
		this.idFilaActualizar = idFilaActualizar;
	}

	public int getBtnRechazo() {
		return btnRechazo;
	}

	public void setBtnRechazo(int btnRechazo) {
		this.btnRechazo = btnRechazo;
	}

	public int getBtnComentario() {
		return btnComentario;
	}

	public void setBtnComentario(int btnComentario) {
		this.btnComentario = btnComentario;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}
	
	public Comprobante getComprobCursos() {
		return comprobCursos;
	}

	public void setComprobCursos(Comprobante comprobCursos) {
		this.comprobCursos = comprobCursos;
	}

	public String getUsrImg() {
		return SecurityContextAssociation.getPrincipal().getName();
	}

	public void setUsrImg(String usrImg) {
	}
	
	public List<Persona> getListPersonas() {
		return this.listPersonas;
	}

	public void setListPersonas(List<Persona> listPersonas) {
		this.listPersonas = listPersonas;
	}
	
	public Persona getPersona(){
		String userName = SecurityContextAssociation.getPrincipal().getName();
		return registroPersona.buscarPersonaPorUsr(userName);
	}

	public List<Saber> completeTheme(String query) {
    	if (this.allSaberes == null)
    		this.allSaberes = listaConocimientos.getConocimientos();
        List<Saber> filteredSaber = new ArrayList<Saber>();
         
        for (Saber saber : this.allSaberes) {
        	String nombre = saber.getNombre();
        	if ((nombre != null) && (!nombre.equals(""))){
        	 if(nombre.toLowerCase().startsWith(query)) {
                 filteredSaber.add(saber);
             }
        	}        	
		}
         
        return filteredSaber;
    }
    
	public List<Persona> buscarPersonaPorUsuario(){
		Persona p = registroPersona.buscarPersonaPorUsr(usuario);
		if (p == null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ingresado no existe en la base de datos" , "");  
            FacesContext.getCurrentInstance().addMessage(null, msg); 
			return null;
		}
		else{
		List<Persona> lp = new ArrayList<Persona>();
		lp.add(p);
		setListaPersonas(lp);
		return lp;
		}
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	
	public RegistroRol getRegistroRol() {
		return registroRol;
	}

	public void setRegistroRol(RegistroRol registroRol) {
		this.registroRol = registroRol;
	}

	public RegistroPersona getRegistroPersona() {
		return registroPersona;
	}

	public void setRegistroPersona(RegistroPersona registroPersona) {
		this.registroPersona = registroPersona;
	}

	public Rol getRol() {
		obtenerRolUsuarioLogueado();
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}