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

import uy.com.antel.Saberes.model.Saber;


@RequestScoped
public class SaberListProducer {

	@Inject
	private EntityManager em;

	private List<Saber> saberes;


	@Produces
	@Named
	public List<Saber> getSaberes() {
		return saberes;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Saber saber) {
		retrieveAllOrderedByName();
	}

	@PostConstruct
	public void retrieveAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saber> criteria = cb.createQuery(Saber.class);
		Root<Saber> saber = criteria.from(Saber.class);
		criteria.select(saber);
		criteria.where(
				cb.or(
						cb.notEqual(saber.get("institucion"), 6), 
						cb.isNull(saber.get("institucion"))
						)
					);
		criteria.orderBy(cb.asc(saber.get("nombre")));
		saberes = em.createQuery(criteria).getResultList();
	}
}
