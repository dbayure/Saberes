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
import javax.persistence.Query;

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
	   private RegistroRol rol;
	   
	   private Persona newPersona;
	   
	   @Produces
	   @Named
	   public Persona getNewPersona() {
	      return newPersona;
	   }
	   

		public void registro() throws Exception {
			log.info("Registro " + newPersona.getNombre());
			newPersona.setRol(rol.buscarPerfilBasico());
			em.persist(newPersona);
			// saberPersonaEventSrc.fire(_saberPersona);
			initNewPersona();
		}
	   
	   public void modificar(Persona persona) throws Exception {
		   em.merge(persona);
	   }
	   
	   public void modificarRol(Persona persona, Rol rol) throws Exception {
		   persona.setRol(rol);
		   em.merge(persona);
	   }
	   
	   public void copyFile(String fileName, InputStream in) {
	       try {
	          
	          
	            // write the inputStream to a FileOutputStream
	            OutputStream out = new FileOutputStream(new File(fileName));
	          
	            int read = 0;
	            byte[] bytes = new byte[1024];
	          
	            while ((read = in.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	          
	            in.close();
	            out.flush();
	            out.close();
	          
	            System.out.println("New file created!");
	            } catch (IOException e) {
	            	System.out.println(e.getMessage());
	            }
	    }
	   
	   @PostConstruct
	   public void initNewPersona() {
		   newPersona = new Persona();
	   }
		
		public Persona buscarPersona(long idp){
			Persona p = em.find(Persona.class, idp);
			return p;
		}

		@SuppressWarnings("unchecked")
		public List <Object []> getAllPersonas(){
			//Query query = em.createNativeQuery("SELECT id,usuario FROM persona p where p.usuario='e138704'");
			Query query = em.createNativeQuery("SELECT id,usuario FROM persona");
			List <Object []> personas = query.getResultList();
			return personas;
		}

		@SuppressWarnings("unchecked")
		public void setPersonaPorUsr(String usr) {
			Query q = em
			.createQuery("Select p from Persona p where p.usuario = ?1");
			q.setParameter(1, usr);
			List <Persona> resultado = q.getResultList();
			if (!resultado.isEmpty())
			this.newPersona = resultado.get(0);
			}
		
		@SuppressWarnings("unchecked")
		public Persona buscarPersonaPorUsr(String usr) {
		Query q = em.createQuery("Select p from Persona p where p.usuario = ?1");
		q.setParameter(1, usr);
		List <Persona> resultado = q.getResultList();
		
		if (resultado.isEmpty())
			return null;
//		System.out.println("resultado de la consulta " + resultado.get(0).getNombre());
		newPersona = resultado.get(0);
		return resultado.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public List<Persona> buscarPersonaPendienteValidar() {
		Query q = em.createQuery("Select distinct p from Persona p right join p.saberes as s where s.validado = ?1");
		q.setParameter(1, 'P');
		List<Persona> resultado = q.getResultList();
		
		if (resultado.isEmpty())
			return null;
		return resultado;
		}
		
		public Integer getCI(String usuario){
			List<String> conv = Arrays.asList("a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k");
			if (usuario != null && !usuario.equals("")){
				if (usuario.indexOf(0) == 'a') {
					return Integer.parseInt(usuario.substring(1));
				} else {
					int primerDigito = conv.indexOf(usuario.substring(0, 1));
					return Integer.parseInt(primerDigito + usuario.substring(1));
				}
			}
			return 0;
		}
		
		
	   public Persona encontrarPorId(long id){
		   return em.find(Persona.class,id);
	   }

}
