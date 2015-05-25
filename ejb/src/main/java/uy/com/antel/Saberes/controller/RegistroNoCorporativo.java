package uy.com.antel.Saberes.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		log.info("Registro " + newNoCorporativo.getSaber().getNombre());
		Persona pe = rp.buscarPersonaPorUsr(usuario);
		List<SaberPersona> saberes = new ArrayList<SaberPersona>();
		saberes.add(newNoCorporativo);
		pe.setSaberes(saberes);
		em.persist(newNoCorporativo);
		noCorporativoEventSrc.fire(newNoCorporativo);
		initNewNoCorporativo();
	}
	
	public void registro(String usuario, String nombreArchivo,InputStream inputComprobante) throws Exception {
		log.info("Registro " + newNoCorporativo.getSaber().getNombre());
		Persona pe = rp.buscarPersonaPorUsr(usuario);
		List<SaberPersona> saberes = new ArrayList<SaberPersona>();
		saberes.add(newNoCorporativo);
		pe.setSaberes(saberes);
		newNoCorporativo.getComprobante().setNombre(nombreArchivo);
		em.persist(newNoCorporativo);

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
		String rutaPDF = p.getProperty("urlPDF");
		copyFile(Long.valueOf(newNoCorporativo.getComprobante().getId())
				+ ".pdf", inputComprobante, rutaPDF);
		noCorporativoEventSrc.fire(newNoCorporativo);
		initNewNoCorporativo();
	}

	public void modificar(NoCorporativo noCorporativo) throws Exception {
		log.info("Modifico " + noCorporativo);
		em.merge(noCorporativo);
	}

	public void eliminar(Long id) throws Exception {
		log.info("Elimino " + id);
		NoCorporativo noCorporativo = em.find(NoCorporativo.class, id);
		em.remove(noCorporativo);
		noCorporativoEventSrc.fire(newNoCorporativo);
	}

	@PostConstruct
	public void initNewNoCorporativo() {
		newNoCorporativo = new NoCorporativo();
		newNoCorporativo.setComprobante(new Comprobante());
	}

	public NoCorporativo obtenerPorID(long id) {
		return em.find(NoCorporativo.class, id);
	}

	public void copyFile(String fileName, InputStream in, String destination) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("Nuevo archivo creado!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
