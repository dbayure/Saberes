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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.jboss.security.SecurityContextAssociation;

import uy.com.antel.Saberes.data.ExtrasListProducer;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Rol;

@Stateful
@Model
public class RegistroPersona {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private ExtrasListProducer elp;

	private Persona newPersona;

	@Produces
	@Named
	public Persona getNewPersona() {
		return newPersona;
	}

	public void registro() throws Exception {
		log.info("Registro " + newPersona.getNombre());
		newPersona.setRol(newPersona.getRol());
		em.persist(newPersona);
		initNewPersona();
	}

	public void modificar(Persona persona) throws Exception {
		em.merge(persona);
	}
	
   public void eliminar(Long id) throws Exception {
	   log.info("Elimino " + id);
	   Persona persona = em.find(Persona.class, id);
	   em.remove(persona);
   }

	public void modificarRol(Persona persona, Rol rol) throws Exception {
		persona.setRol(rol);
		em.merge(persona);
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void initNewPersona() {
		newPersona = new Persona();
	}

	public Persona buscarPersona(long idp) {
		Persona p = em.find(Persona.class, idp);
		return p;
	}

	public List<Object[]> getAllPersonas() {
		List<Object[]> personas = elp.obtenerPersonasPorIdUsuario();
		return personas;
	}

	public void setPersona(Persona persona) {
		newPersona = persona;
	}

	public Persona buscarPersonaPorUsr(String usr) {
		Persona persona = elp.obtenerPersonaPorUsuario(usr);
		if(persona == null){
			return null;
		}
		else{
			return persona;
		}
	}


	public List<Persona> buscarPersonasPendienteValidar() {
		List<Persona> personas = elp.obtenerPersonasConSaberesPendienteDeValidar();
		if (personas.isEmpty()){
			return null;
		}else{
			return personas;
		}
	}

	public Integer getCI(String usuario) {
		List<String> conv = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		if (usuario != null && !usuario.equals("")) {
			if (usuario.indexOf(0) == 'a') {
				return Integer.parseInt(usuario.substring(1));
			} else {
				int primerDigito = conv.indexOf(usuario.substring(0, 1));
				return Integer.parseInt(primerDigito + usuario.substring(1));
			}
		}
		return 0;
	}

	public Persona encontrarPorId(long id) {
		return em.find(Persona.class, id);
	}

	public List<Object[]> CorporativosPorFechayRol() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> scpf = elp.obtenerCorporativoPorFechayRol(p);
		return scpf;
	}
	
	public List<Object[]> formalCurricularPorUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> sfc = elp.obtenerSaberFormalCurricularPorUsuario(p);
		return sfc;
	}
	
	public List<Object[]> formalNoCorportativoPorUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> sfnc = elp.obtenerSaberFormalNoCorporativoPorUsuario(p);
		return sfnc;
	}

	public List<Object[]> formalCorporativoPorUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> sc = elp.obtenerSaberFormalCorporativoPorUsuario(p);
		for (Object[] o : sc) {
			o[7] = o[7].toString().toLowerCase();
		}
		return sc;
	}
	
	public List<Object[]> noFormalConocimientosPorUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> snfc = elp.obtenerSaberNoFormalConocimientosPorUsuario(p);
		return snfc;
	}
	
	public List<Object[]> noFormalCursosPorUsuario() {
		String userName = SecurityContextAssociation.getPrincipal().getName();
		Persona p = buscarPersonaPorUsr(userName);
		List<Object[]> snfcursos = elp.obtenerSaberNoFormalCursosPorUsuario(p);
		return snfcursos;
	}
	
	public List<NoCorporativo> noCorporativosPendienteValidacionPorPersona(long id) {
		List<NoCorporativo> pendientes = elp.obtenerSaberesPendientesDeValidarPorPersona(id);
		return pendientes;
	}
}
