package uy.com.antel.Saberes.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.xml.rpc.ServiceException;

import org.apache.tomcat.jni.File;
import org.jboss.security.SecurityContextAssociation;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import WebServices.sgp.antel.com.DatoPer;
import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.controller.RegistroPersona;
import uy.com.antel.Saberes.model.Corporativo;
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
	private long idPersona;
	private String rutaPDF;
	private String rutaIMG;
	protected boolean mostrar = false;
	private List<NoCorporativo> listaNoCorporativoPersona = new ArrayList<NoCorporativo>();

	@Inject
	private RegistroPersona registroPersona;

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;
	
	private StreamedContent graphicText;

	@Inject
	private RegistroNoCorporativo registroCorporativo;

	private UploadedFile file;

	public void registrar() {
		FacesMessage msg;

		try {

			Persona aPersona;
			if (personaRegistrada())
				aPersona = registroPersona.buscarPersonaPorUsr(this.usuario);
			else
				aPersona = registroPersona.getNewPersona();

			DatoPer datos = obtenerDatosPersona(this.getUsuario());

			String apellido = datos.getPriape().trim() + " "
					+ datos.getSegape().trim();
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

			// msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
			// "Se registró ", "con éxito!");
			// FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Error al registrar ", "");
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

			return llamada.getDatosPer(sCedula, sAppl, sPass);

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;

	}

public void setPersonaBean(String usr) {
	registroPersona.setPersonaPorUsr(usr);
}

public void upload(FileUploadEvent event) {  
  FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
  FacesContext.getCurrentInstance().addMessage(null, msg);
  
   //aca deberia refrescar la imagen
  
  try {
      registroPersona.copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
  } catch (IOException e) {
      e.printStackTrace();
  }

}  

	private String getCi(String ci) {
		List<String> conv = Arrays.asList("a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k");
		if (ci.length() < 7) {
			return "a" + ci.substring(1);
		} else {
			int primerLetra = conv.indexOf(ci.substring(0, 1));
			return primerLetra + ci.substring(1);
		}
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló modificar ",
				((Rol) event.getObject()).getRol());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getUsuario() {
		return usuario;
	}

	public List<NoCorporativo> obtenerSaberesPersona(long id) {
		Persona p = registroPersona.encontrarPorId(id);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			if (sp instanceof NoCorporativo) {
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp
						.getId());
				if (!nc.isValidado()) {
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
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp
						.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}

	public List<Corporativo> obtenerCorporativos() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<Corporativo> listanc = new ArrayList<Corporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("Formal Corporativo")) {
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				Corporativo nc = registroCorporativo.obtenerCorpPorID(sp
						.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}

	public List<NoCorporativo> obtenerCurriculares() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = registroPersona.buscarPersonaPorUsr(userName);
		List<NoCorporativo> listanc = new ArrayList<NoCorporativo>();
		for (SaberPersona sp : p.getSaberes()) {
			String ts = sp.getSaber().getTipoSaber().getNombre();
			if (ts.contains("Formal Curricular")) {
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp
						.getId());
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
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp
						.getId());
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
				System.out.println("Nombre del saber seleccionado "
						+ sp.getSaber().getNombre());
				NoCorporativo nc = registroNoCorporativo.obtenerPorID(sp
						.getId());
				listanc.add(nc);
			}
		}
		return listanc;
	}    

	public String convertirBoolean(Boolean var) {
		if (var)
			return "Si";
		else
			return "No";
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
		this.usuario = usuario;
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
		String archivoPropiedades = System.getProperty("user.dir")
				+ "/Conf/app-properties/saberes.properties";
		System.out.println("valor del archivo de propiedades **************"
				+ archivoPropiedades);
		try {
			p.load(new FileInputStream(archivoPropiedades));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rutaPDF = p.getProperty("urlPDF");
		rutaIMG = p.getProperty("urlIMG");
		System.out.println("ruta seleccionada para archivos pdf " + rutaPDF);
		System.out.println("ruta seleccionada para archivos img " + rutaIMG);
		init();
	}
	
	public void init() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
        try {          
            
            BufferedImage bufferedImg = null;
            try {
            	bufferedImg = ImageIO.read( new java.io.File(rutaIMG+"/Imagenes/"+userName+ ".jpeg"));
            } catch (IOException e) { }
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImg, "jpeg", os);
            setGraphicText(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/jpeg"));
 

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	public String getRutaIMG() {
		obtenerRuta();
		return rutaIMG;
	}

	public void setRutaIMG(String rutaIMG) {
		this.rutaIMG = rutaIMG;
	}

	public String getRutaPDF() {
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

	public StreamedContent getGraphicText() {
		return graphicText;
	}

	public void setGraphicText(StreamedContent graphicText) {
		this.graphicText = graphicText;
	}
}
