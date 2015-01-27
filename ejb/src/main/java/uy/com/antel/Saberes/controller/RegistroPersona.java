package uy.com.antel.Saberes.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

@Stateful
@Model
public class RegistroPersona {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;
	   
	   @Inject
	   private RegistroRol rol;

//	   @Inject
//	   private Event<SaberPersona> saberPersonaEventSrc;

	   private Persona newPersona;
	   
	   
//	   private SaberPersona _saberPersona;
	   
	   private String destination="/home/jsanguinetti/Descargas/prueba";
	   
	   @Produces
	   @Named
	   public Persona getNewPersona() {
	      return newPersona;
	   }
	   
//	   @Produces
//	   @Named
//	   public SaberPersona getSaberPersona() {
//			return _saberPersona;
//		}

		public void registro() throws Exception {
			log.info("Registro " + newPersona.getNombre());
			newPersona.setRol(rol.buscarPerfilBasico());
			em.persist(newPersona);
			// saberPersonaEventSrc.fire(_saberPersona);
			initNewPersona();
		}
	   
	   public void modificar(Persona persona) throws Exception {
		   log.info("Modifico " + persona);
		   em.merge(persona);
	   }
	   
	   public void copyFile(String fileName, InputStream in) {
	       try {
	          
	          
	            // write the inputStream to a FileOutputStream
	            OutputStream out = new FileOutputStream(new File(destination + fileName));
	          
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
	   
//	   public void ingresarSaberPersona () throws Exception{
//
//		   actualPersona.getSaberes().add(_saberPersona);
//			//init();
//		}
		
//		public void eliminarSaberPersona (long idPersona, long idSaberPersona) throws Exception {
//			log.info("Se va a eliminar el saber " + idSaberPersona + "de la persona " + idPersona);
//			SaberPersona sp = em.find(SaberPersona.class, idSaberPersona);
//			Persona p = em.find(Persona.class,idPersona);
//			p.getSaberes().remove(sp);
//			saberPersonaEventSrc.fire(_saberPersona);
//			em.remove(sp);
//			em.merge(p);
//			
//			log.info("saberes restantes en la persona: " + p.getSaberes().size());
//			log.info("persona: " + p.getId());
//		}
		
		public Persona buscarPersona(long idp){
			Persona p = em.find(Persona.class, idp);
			return p;
		}

		public void setPersonaPorUsr(String usr) {
			Query q = em
					.createQuery("Select p from Persona p where p.usuario = ?1");
			q.setParameter(1, usr);
	
			List <Persona> resultado = q.getResultList();
	
			if (!resultado.isEmpty())
				this.newPersona = resultado.get(0);
	
		}
		
		public Persona buscarPersonaPorUsr(String usr) {
			Query q = em
					.createQuery("Select p from Persona p where p.usuario = ?1");
			q.setParameter(1, usr);
	
			List <Persona> resultado = q.getResultList();
	
			if (resultado.isEmpty())
				return null;
			return resultado.get(0);
	
		}
		
//		public void modificarSaberPersona(Persona p){
//			em.merge(p);
//			log.info("La persona " + p.getNombre() + "fue modificada correctamente");
//		}
		
   
}
