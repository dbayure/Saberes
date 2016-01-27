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
import javax.persistence.Query;

import uy.com.antel.Saberes.model.Origen;


@Stateful
@Model
public class RegistroOrigen {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Origen> origenEventSrc;

	   private Origen newOrigen;
	   
	   @Produces
	   @Named
	   public Origen getNewOrigen() {
	      return newOrigen;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newOrigen.getDescripcion());
	      em.persist(newOrigen);
	      origenEventSrc.fire(newOrigen);
	      initNewOrigen();
	   }
	   
	   public void modificar(Origen origen) throws Exception {
		   log.info("Modifico " + origen);
		   em.merge(origen);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Origen origen = em.find(Origen.class, id);
		   em.remove(origen);
		   origenEventSrc.fire(newOrigen);
	   }
	   
	   public boolean buscarOrigenRepetido() {
		   Query q = em.createQuery("SELECT o FROM Origen o WHERE UPPER(TRANSLATE(o.descripcion, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
			"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
		   q.setParameter(1,newOrigen.getDescripcion());
		   if (q.getResultList().isEmpty()){
			   return false;
		   }
		   else{
			   return true;
		   }
	   }

	   @PostConstruct
	   public void initNewOrigen() {
		   newOrigen = new Origen();
	   }
}
