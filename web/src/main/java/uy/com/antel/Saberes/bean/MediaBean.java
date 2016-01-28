package uy.com.antel.Saberes.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;


@ManagedBean
public class MediaBean {

	
	private String rutaPDF;
	private DefaultStreamedContent download;
	private boolean mostrarPDF = false;

	public void setDownload(DefaultStreamedContent download) {
	    this.download = download;
	}

	public DefaultStreamedContent getDownload() throws Exception {
	    System.out.println("GET = " + download.getName());
	    return download;
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		rutaPDF = p.getProperty("urlPDF");

		System.out.println("ruta seleccionada para archivos pdf " + rutaPDF);
	}	

	public void prepDownload(long idComprob) throws Exception {
		
		String rutaArchivoPDF = rutaPDF + idComprob + ".pdf";
		
	    File file = new File(rutaArchivoPDF);
	    if(file.exists()){
	    	FileInputStream input = new FileInputStream(file);
	    
	    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    	setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    	System.out.println("PREP = " + download.getName());
	    	setMostrarPDF(true);
	    }
	}

	public boolean isMostrarPDF() {
		return mostrarPDF;
	}

	public void setMostrarPDF(boolean mostrarPDF) {
		this.mostrarPDF = mostrarPDF;
	}



}

