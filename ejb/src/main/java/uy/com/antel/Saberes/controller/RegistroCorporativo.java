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

import uy.com.antel.Saberes.model.Corporativo;


@Stateful
@Model
public class RegistroCorporativo {

	@Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Corporativo> corporativoEventSrc;

	   private Corporativo newCorporativo;
	   
	   @Produces
	   @Named
	   public Corporativo getNewCorporativo() {
	      return newCorporativo;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newCorporativo.getSaber().getNombre());
	      em.persist(newCorporativo);
	      corporativoEventSrc.fire(newCorporativo);
	      initNewCorporativo();
	   }
	   
	   public void modificar(Corporativo corporativo) throws Exception {
		   log.info("Modifico " + corporativo);
		   em.merge(corporativo);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Corporativo corporativo = em.find(Corporativo.class, id);
		   em.remove(corporativo);
		   corporativoEventSrc.fire(newCorporativo);
	   }

	   @PostConstruct
	   public void initNewCorporativo() {
		   newCorporativo = new Corporativo();
	   }
}
