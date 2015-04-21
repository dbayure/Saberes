package uy.com.antel.Saberes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.SaberPersona;


@Stateful
@Model
public class RegistroNoCorporativo {
	
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
	      Persona p = rp.buscarPersonaPorUsr(usuario);
	      List<SaberPersona> saberes = new ArrayList<SaberPersona>();
	      saberes.add(newNoCorporativo);
	      p.setSaberes(saberes);
	      em.persist(newNoCorporativo);
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
	   }
	   
	   public NoCorporativo obtenerPorID(long id){
		   return em.find(NoCorporativo.class, id);
	   }
	   
}
