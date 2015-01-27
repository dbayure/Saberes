package uy.com.antel.Saberes.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import uy.com.antel.Saberes.model.Saber;


@Stateful
@Model
public class RegistroSaber {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Saber> saberEventSrc;

	   private Saber newSaber;
	   
	   @Produces
	   @Named
	   public Saber getNewSaber() {
	      return newSaber;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newSaber.getNombre());
	      em.persist(newSaber);
	      saberEventSrc.fire(newSaber);
	      initNewSaber();
	   }
	   
	   public void modificar(Saber saber) throws Exception {
		   log.info("Modifico " + saber);
		   em.merge(saber);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Saber saber = em.find(Saber.class, id);
		   em.remove(saber);
		   saberEventSrc.fire(newSaber);
	   }

	   @PostConstruct
	   public void initNewSaber() {
		   newSaber = new Saber();
	   }
	  
}
