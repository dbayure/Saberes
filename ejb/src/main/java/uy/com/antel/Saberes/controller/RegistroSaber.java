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
import javax.persistence.Query;

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

	   @PostConstruct
	   public void initNewSaber() {
		   newSaber = new Saber();
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

		public Saber buscarPorCodGicca(Integer codeCurso) {
			Query q = em.createQuery("from Saber as saber where saber.codgicca = ?");
			q.setParameter(1, codeCurso);
			List <Saber> resultado = q.getResultList();
			
			if (resultado.isEmpty())
				return null;
			return (Saber) resultado.get(0);
		}
	   
	public ArrayList<Saber> buscarPorInstitucion(Long idInstitucion, Long tipo) {
		Query q = em.createQuery("from Saber as saber where saber.institucion.id = ? and saber.tipoSaber.id = ?");
		q.setParameter(1, idInstitucion);
		q.setParameter(2, tipo);
		List <Saber> resultado = q.getResultList();
		
		if (resultado.isEmpty())
			return null;
		return (ArrayList<Saber>) resultado;
	}
}
