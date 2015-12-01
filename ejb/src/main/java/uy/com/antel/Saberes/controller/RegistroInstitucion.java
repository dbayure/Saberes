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

import uy.com.antel.Saberes.model.Institucion;


@Stateful
@Model
public class RegistroInstitucion {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Institucion> institucionEventSrc;

	   private Institucion newInstitucion;
	   
	   @Produces
	   @Named
	   public Institucion getNewInstitucion() {
	      return newInstitucion;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newInstitucion.getNombre());
	      em.persist(newInstitucion);
	      institucionEventSrc.fire(newInstitucion);
	      initNewInstitucion();
	   }
	   
	   public void modificar(Institucion institucion) throws Exception {
		   log.info("Modifico " + institucion);
		   em.merge(institucion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Institucion institucion = em.find(Institucion.class, id);
		   em.remove(institucion);
		   institucionEventSrc.fire(newInstitucion);
	   }

	   @PostConstruct
	   public void initNewInstitucion() {
		   newInstitucion = new Institucion();
	   }
}
