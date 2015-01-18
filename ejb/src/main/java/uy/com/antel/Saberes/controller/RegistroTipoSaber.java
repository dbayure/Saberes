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

import uy.com.antel.Saberes.model.TipoSaber;


@Stateful
@Model
public class RegistroTipoSaber {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<TipoSaber> tipoSaberEventSrc;

	   private TipoSaber newTipoSaber;
	   
	   @Produces
	   @Named
	   public TipoSaber getNewTipoSaber() {
	      return newTipoSaber;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTipoSaber.getNombre());
	      em.persist(newTipoSaber);
	      tipoSaberEventSrc.fire(newTipoSaber);
	      initNewTipoSaber();
	   }
	   
	   public void modificar(TipoSaber tipoSaber) throws Exception {
		   log.info("Modifico " + tipoSaber);
		   em.merge(tipoSaber);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   TipoSaber tipoSaber = em.find(TipoSaber.class, id);
		   em.remove(tipoSaber);
		   tipoSaberEventSrc.fire(newTipoSaber);
	   }

	   @PostConstruct
	   public void initNewTipoSaber() {
		   newTipoSaber = new TipoSaber();
	   }
}
