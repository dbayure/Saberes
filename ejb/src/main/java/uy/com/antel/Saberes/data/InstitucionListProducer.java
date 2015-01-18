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

import uy.com.antel.Saberes.model.Institucion;


@RequestScoped
public class InstitucionListProducer {

   @Inject
   private EntityManager em;

   private List<Institucion> instituciones;


   @Produces
   @Named
   public List<Institucion> getInstituciones() {
      return instituciones;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Institucion instituciones) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Institucion> criteria = cb.createQuery(Institucion.class);
      Root<Institucion> institucion = criteria.from(Institucion.class);
      criteria.select(institucion).orderBy(cb.asc(institucion.get("nombre")));
      instituciones = em.createQuery(criteria).getResultList();
   }
}
