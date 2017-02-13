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
package uy.com.antel.Saberes.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import uy.com.antel.Saberes.model.Comprobante;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.SaberPersona;

@Stateful
@Model
public class RegistroNoCorporativo {

	long idComprobante;
		
	@Inject
	private Logger log;

	@Inject
	private RegistroPersona rp;
	
	@Inject
	private RegistroComprobante rc;

	@Inject
	private EntityManager em;

	@Inject
	private Event<NoCorporativo> noCorporativoEventSrc;

	private NoCorporativo newNoCorporativo;

	@Produces
	@Named
	public NoCorporativo getNewNoCorporativo() {
		return newNoCorporativo;
	}
	
	public void registro(String usuario) throws Exception {
		log.info("Registro no corporativo " + newNoCorporativo.getSaber().getNombre());
		log.info("Registro nombre del archivo " + rc.getNewComprobante().getNombre());
		newNoCorporativo.setComprobante(rc.getNewComprobante());
		Persona pe = rp.buscarPersonaPorUsr(usuario);
		newNoCorporativo.setPersona(pe);
		List<SaberPersona> saberes = new ArrayList<SaberPersona>();
		saberes.add(newNoCorporativo);
		pe.setSaberes(saberes);
		em.persist(newNoCorporativo);
		if(newNoCorporativo.getSaber().getTipoSaber().getId() != 4 && rc.getNewComprobante() != null){
			Properties p = new Properties();
			String archivoPropiedades = System.getProperty("user.dir") + "/Conf/saberes.properties";	
			try {
				p.load(new FileInputStream(archivoPropiedades));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String rutaPDF = p.getProperty("urlPDF");
			copyFile(Long.valueOf(newNoCorporativo.getComprobante().getId())
					+ ".pdf", rc.getNewComprobante().getUploadedFile(), rutaPDF);	
		}
		
		noCorporativoEventSrc.fire(newNoCorporativo);
		initNewNoCorporativo();
	}

	public void modificar(NoCorporativo noCorporativo) throws Exception {
		log.info("Modifico " + noCorporativo.getAprobacion() + " - " + noCorporativo.getFechaFin() + " - " + noCorporativo.getFechaInicio());
		em.merge(noCorporativo);
	}

	public void eliminar(Long id) throws Exception {
		log.info("Elimino " + id);
		NoCorporativo noCorporativo = em.find(NoCorporativo.class, id);
		long idpdf = noCorporativo.getComprobante().getId();
		em.remove(noCorporativo);
		noCorporativoEventSrc.fire(newNoCorporativo);
		Properties p = new Properties();
		String archivoPropiedades = System.getProperty("user.dir") + "/Conf/saberes.properties";	
		try {
			p.load(new FileInputStream(archivoPropiedades));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rutaPDF = p.getProperty("urlPDF");
		File pdf = new File(rutaPDF+idpdf+".pdf");
		if(pdf.delete())
			log.info("El archivo fue eliminado correctamente ");
	}

	@PostConstruct
	public void initNewNoCorporativo() {
		newNoCorporativo = new NoCorporativo();
		newNoCorporativo.setComprobante(new Comprobante());
	}

	public NoCorporativo obtenerPorID(long id) {
		return em.find(NoCorporativo.class, id);
	}

	public File obtenerPDFComprobante (String id) throws FileNotFoundException{
		Properties p = new Properties();
		String archivoPropiedades = System.getProperty("user.dir") + "/Conf/saberes.properties";	
		try {
			p.load(new FileInputStream(archivoPropiedades));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rutaPDF = p.getProperty("urlPDF");
		File pdf = new File(rutaPDF+id+".pdf");
		return pdf;
	}
	
	public void copyFile(String fileName, byte[] in, String destination) {
		try {
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));
			out.write(in);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}
	
	public boolean ExisteComprobantePorId(long id){
		Properties p = new Properties();
		String archivoPropiedades = System.getProperty("user.dir") + "/Conf/saberes.properties";	
		try {
			p.load(new FileInputStream(archivoPropiedades));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rutaPDF = p.getProperty("urlPDF");
		File fichero = new File(rutaPDF);
		return fichero.exists();
	}

}
