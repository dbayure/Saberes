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
public class ConocimientosListProducer {

	@Inject
	private EntityManager em;

	private List<Saber> conocimientos;
	
	@Produces
	@Named
	public List<Saber> getConocimientos() {
		return conocimientos;
	}
	
	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Saber saber) {
		retrieveConocimientosOrderedByName();
	}

	@PostConstruct
	public void retrieveConocimientosOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saber> criteria = cb.createQuery(Saber.class);
		Root<Saber> saber = criteria.from(Saber.class);
		criteria.where(cb.equal(saber.get("tipoSaber"), new Integer(4)));
		criteria.select(saber).orderBy(cb.asc(saber.get("nombre")));
		conocimientos = em.createQuery(criteria).getResultList();
	}
}
