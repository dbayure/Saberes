/*
SABERES - Registro de conocimientos, aptitudes del personal de la empresa
Copyright (C) 2009  ANTEL
This file is part of SABERES.
SABERES is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 
*/
package uy.com.antel.Saberes.bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;

import org.jboss.security.SecurityContextAssociation;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import uy.com.antel.Saberes.controller.RegistroNoCorporativo;
import uy.com.antel.Saberes.controller.RegistroPersona;
import uy.com.antel.Saberes.controller.RegistroRol;
import uy.com.antel.Saberes.model.Comprobante;
import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Rol;

@ManagedBean
@SessionScoped
public class PersonaBean {

//	El atributo usuario viene cargado en del filtro, este levanta el usuario logueado y lo guarda aca
	private String usuario;
	private String usuarioRol;
	private long idPersona;
	private long idNoCorporativo;
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
	private List<Persona> listaPersonas;
	private List<Persona> listaPersonaRol = new ArrayList<Persona>();
	private Rol rol = null;
	private Rol cambioRol = null;
	private int timeout;
	private Persona persona;
	private boolean listaVacia;
    private CroppedImage croppedImage;
    private String newImageName;
    private String oldImageName;
    private UploadedFile imgOriginal;
	
	@Inject
	private RegistroPersona registroPersona;

	@Inject
	private RegistroNoCorporativo registroNoCorporativo;
	
	@Inject
	private RegistroRol registroRol;
	
//	List<Persona> listPersonas;

	private UploadedFile file;
    
    public int getTimeout() {
        return timeout;
    }
    
	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = new ArrayList<Persona>();
		this.listaPersonas = listaPersonas;
	}

	public List<Persona> getListaPersonaRol() {
		return listaPersonaRol;
	}

	public void setListaPersonaRol(List<Persona> listaPersonaRol) {
		this.listaPersonaRol = listaPersonaRol;
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
		
    public Rol getCambioRol() {
		return cambioRol;
	}

	public void setCambioRol(Rol cambioRol) {
		this.cambioRol = cambioRol;
	}

	public void increment() {
        timeout++;
    }
    
	public void setUsuario(String usuario) {
		this.usuario = usuario.toLowerCase();
	}
	
	public String getUsuario() {
		String usuario = SecurityContextAssociation.getPrincipal().getName();
		return usuario;
	}

	public String getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(String usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public List<NoCorporativo> getListaNoCorporativoPersona() {
		return listaNoCorporativoPersona;
	}

	public void setListaNoCorporativoPersona(List<NoCorporativo> listaNoCorporativoPersona) {
		this.listaNoCorporativoPersona = listaNoCorporativoPersona;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}
	
	public long getIdNocorporativo() {
		return idNoCorporativo;
	}

	public void setIdNoCorporatvo(long idNoCorporativo) {
		this.idNoCorporativo = idNoCorporativo;
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
	
	public Persona getPersona(){
		persona = buscarPersonaPorUsuario();
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isListaVacia() {
		return listaVacia;
	}

	public void setListaVacia(boolean listaVacia) {
		this.listaVacia = listaVacia;
	}
	
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }
 
    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 100000);
         
        return String.valueOf(i);
    }
     
    public String getNewImageName() {
        return newImageName;
    }
     
    public String getOldImageName() {
        String userName = SecurityContextAssociation.getPrincipal().getName();
        this.oldImageName = "/resources/uploadPicsTmp/" + userName + ".jpg";
        return oldImageName;
    }
        
	public UploadedFile getImgOriginal() {
		return imgOriginal;
	}

	public void setImgOriginal(UploadedFile imgOriginal) {
		this.imgOriginal = imgOriginal;
	}

	public void registrar(String usuario) {
		Persona p = registroPersona.buscarPersonaPorUsr(usuario);
		try {
			if (p != null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error el usuario ingresado ya existe","");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				registroPersona.registro();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,	"El usuario fue registrado correctamente ", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No fue posible registrar el usuario ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public boolean personaRegistrada() {
		return (registroPersona.buscarPersonaPorUsr(usuario) != null);
	}

	public void setPersonaBean(String usuario) {
		Persona persona = registroPersona.buscarPersonaPorUsr(usuario);
		registroPersona.setPersona(persona);
	}

	public void upload(FileUploadEvent event) throws IOException {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		String cp = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/uploadPicsTmp/");
	    String rutaArchivoUsuarioTmp = cp+"/"+userName+".jpg";
	    String rutaArchivoUsuario = rutaIMG + userName + ".jpg";
		try { 
			registroPersona.copyFile(rutaArchivoUsuarioTmp, event.getFile().getInputstream());
			registroPersona.copyFile(rutaArchivoUsuario, event.getFile().getInputstream());	
	        // reads input image
	        File inputFile = new File(rutaArchivoUsuarioTmp);
	        BufferedImage inputImage = ImageIO.read(inputFile);
	        int ancho = inputImage.getWidth();
	        int alto = inputImage.getHeight();
	        if(ancho > 800 && alto > 600){
		        // creates output image
		        BufferedImage outputImage = new BufferedImage(800,600, inputImage.getType());
		 
		        // scales the input image to the output image
		        Graphics2D g2d = outputImage.createGraphics();
		        g2d.drawImage(inputImage, 0, 0, 800, 600, null);
		        g2d.dispose();
		 
		        // writes to output file
		        ImageIO.write(outputImage, "jpg", new File(rutaArchivoUsuarioTmp));
	        }

		
		} catch (IOException e) {
			e.printStackTrace();
		}
		recargarPagina();
	    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("dlgRecIm");
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló la modificación de: " + ((Persona) event.getObject()).getNombre() + " "+((Persona) event.getObject()).getApellido(), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onCellEdit(CellEditEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
	    Rol r = (Rol) newValue;
	    Persona p = getListaPersonaRol().get(0);
            try {
            	if(newValue != null && !newValue.equals(oldValue)) {
            		registroPersona.modificarRol(p, r);
                }
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El rol del usuario " + p.getNombre()+" "+p.getApellido() + " fue modificado exitosamente" , "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el rol del usuario " + p.getNombre()+" "+p.getApellido(), "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onEditField(ValueChangeEvent event) {  
		Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();
	    
	    if(newValue != null && !newValue.equals(oldValue) && !newValue.toString().isEmpty()){
			String userName = SecurityContextAssociation.getPrincipal().getName();
			Persona p = registroPersona.buscarPersonaPorUsr(userName);
			p.setTelCelular((String) newValue);
	        try {
	        	registroPersona.modificar(p);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos del teléfono celular fueron modificados correctamente ","");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar el número de telefono celular", "");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
		}
	    if(newValue.toString().isEmpty()){
			String userName = SecurityContextAssociation.getPrincipal().getName();
			Persona p = registroPersona.buscarPersonaPorUsr(userName);
			p.setTelCelular((String) newValue);
	        try {
	        	registroPersona.modificar(p);
			} catch (Exception e) {
			}
	    }
    }
	
	public void eliminar(Long id) {
		Persona persona = new Persona();
		persona = registroPersona.buscarPersona(id);
		try {
			registroPersona.eliminar(id);
			
			FacesMessage msg = new FacesMessage("El usuario " + persona.getUsuario() + " se eliminó correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al intentar eliminar el usuario "+ persona.getUsuario(), "");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public List<NoCorporativo> obtenerSaberesPorPersonaPendienteDeValidar(long id) {
		List<NoCorporativo> pendientes = registroPersona.noCorporativosPendienteValidacionPorPersona(id);
		return pendientes;
	}

	public List<Object[]> obtenerFormalNoCorporativos() {
		List<Object[]> sfnc = registroPersona.formalNoCorportativoPorUsuario();
        return sfnc;
	}

	public List<Object[]> obtenerFormalCorporativos() {
    	List<Object[]> sfc = registroPersona.formalCorporativoPorUsuario();
        return sfc;
	}

    public List<Object[]> obtenerFormalCurriculares() {
    	List<Object[]> sfc = registroPersona.formalCurricularPorUsuario();
        return sfc;
    }
    
    public List<Object[]> obtenerNoFormalCursos() {
    	List<Object[]> snfc = registroPersona.noFormalCursosPorUsuario();
        return snfc;
    }
    
    public List<Object[]> obtenerNoFormalConocimientos() {
    	List<Object[]> snfc = registroPersona.noFormalConocimientosPorUsuario();
        return snfc;
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
		setListaPersonas(registroPersona.buscarPersonasPendienteValidar());
		if(listaPersonas != null){
			setListaVacia(false);
		}
		else{
			setListaVacia(true);
		}
		return listaPersonas;
	}

	public String convertirValidacion(char var) {
		if (var == 'P')
			return "Pendiente de Validación";
		else if (var == 'V')
			return "Validado";
		else
			return "Rechazado";

	}

	public String convertirAprobacion(boolean aprobacion) {
		if (aprobacion == true)
			return "Aprobado";
		else
			return "Reprobado";
	}
	
	public String convertirFinalizacion(boolean aprobacion) {
		if (aprobacion == true)
			return "Finalizado";
		else
			return "En curso";
	}

	public boolean faltaValidar(long id) {
		boolean falta = false;
		if (listaPersonasPorValidar().size() > 0) {
			falta = true;
		}
		return falta;
	}

	public void validarUnaPersona(long id) {
		listaNoCorporativoPersona = obtenerSaberesPorPersonaPendienteDeValidar(id);
	}

	@PostConstruct
	public void obtenerRuta() {
		Properties p = new Properties();
		String archivoPropiedades = System.getProperty("user.dir") + "/Conf/saberes.properties";
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
		List<NoCorporativo> listanc = registroPersona.noCorporativosPendienteValidacionPorPersona(idPersona);
		for (NoCorporativo ncorp : listanc) {
			if (ncorp.getId().equals(idNoCorporativo)) {
				ncorp.setValidado("V");
				try {
					registroNoCorporativo.modificar(ncorp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void rechazarNoCorporativo() {
		List<NoCorporativo> listanc = registroPersona.noCorporativosPendienteValidacionPorPersona(idPersona);
		for (NoCorporativo ncorp : listanc) {
			if (ncorp.getId().equals(idNoCorporativo)) {
				ncorp.setValidado("R");
				ncorp.setMensaje(motivoRechazo);
				try {
					registroNoCorporativo.modificar(ncorp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
	}

	public StreamedContent getFotoUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		try {

			BufferedImage bufferedImg = null;
			String rutaArchivoUsuario = rutaIMG + userName + ".jpg";
			try {
				bufferedImg = ImageIO.read(new File(rutaArchivoUsuario));
			} catch (IOException e) {
				String cp = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/gfx/");
			    String rutaAvatar = cp+"/"+"avatar.jpg";
				bufferedImg = ImageIO.read(new File (rutaAvatar));
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
    
	public Persona buscarPersonaPorUsuario(){
		Persona persona = registroPersona.buscarPersonaPorUsr(getUsuario());
		return persona;
	}
	
	public void buscarPersonaAsignarRol(){
		Persona persona = registroPersona.buscarPersonaPorUsr(getUsuarioRol());
		List<Persona> lp = getListaPersonaRol();
		lp.add(persona);
		setListaPersonaRol(lp);
	}

    public List<Object[]> obtenerCorporativosPorFechayRol() {
    	List<Object[]> sc = registroPersona.CorporativosPorFechayRol();
        return sc;
    }
  
    public void definirSaberRechazado(long idPersona, long idNoCorporativo){
    	setIdPersona(idPersona);
    	setIdNoCorporatvo(idNoCorporativo);
    }
    
    public void crop() throws IOException {
        if(croppedImage == null) {
            return;
        }
        String userName = SecurityContextAssociation.getPrincipal().getName();
        setNewImageName(getRandomImageName());
        String newFileName = rutaIMG + userName + ".jpg";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
        recargarPagina();
    }
         
    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }
    
    public void updateImg(){
	    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("dlgRecIm cropped");
    }
    
    public void recargarPagina() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect("/Saberes-web/paginas/persona/principal.jsf");
    }
}