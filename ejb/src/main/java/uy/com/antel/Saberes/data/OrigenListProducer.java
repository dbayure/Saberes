package uy.com.antel.Saberes.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.antel.Saberes.model.Origen;

@RequestScoped
public class OrigenListProducer {

   @Inject
   private EntityManager em;

   private List<Origen> origenes;

   @Produces
   @Named
   public List<Origen> getOrigenes() {
      return origenes;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Origen origenes) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Origen> criteria = cb.createQuery(Origen.class);
      Root<Origen> origen = criteria.from(Origen.class);
      criteria.select(origen).orderBy(cb.asc(origen.get("descripcion")));
      origenes = em.createQuery(criteria).getResultList();
   }
}
