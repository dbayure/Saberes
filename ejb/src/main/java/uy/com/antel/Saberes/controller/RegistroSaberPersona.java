package uy.com.antel.Saberes.controller;

import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import uy.com.antel.Saberes.model.SaberPersona;


@Stateful
@Model
public class RegistroSaberPersona {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<SaberPersona> saberPersonaEventSrc;

	   private SaberPersona newSaberPersona;
	   
	   @Produces
	   @Named
	   public SaberPersona getNewSaberPersona() {
	      return newSaberPersona;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newSaberPersona.getSaber().getNombre());
	      em.persist(newSaberPersona);
	      saberPersonaEventSrc.fire(newSaberPersona);
	   }
	   
	   public void modificar(SaberPersona saberPersona) throws Exception {
		   log.info("Modifico " + saberPersona);
		   em.merge(saberPersona);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   SaberPersona saberPersona = em.find(SaberPersona.class, id);
		   em.remove(saberPersona);
		   saberPersonaEventSrc.fire(newSaberPersona);
	   }

	public void setNewSaberPersona(SaberPersona newSaberPersona) {
		this.newSaberPersona = newSaberPersona;
	}
	   
	   

}
