package uy.com.antel.prueba_saberes.data;

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

import uy.com.antel.prueba_saberes.model.TipoSaber;


@RequestScoped
public class TipoSaberListProducer {

	@Inject
	private EntityManager em;

	private List<TipoSaber> tipoSaberes;


	@Produces
	@Named
	public List<TipoSaber> getTipoSaberes() {
		return tipoSaberes;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoSaber tipoSaber) {
		retrieveAllOrderedByName();
	}

	@PostConstruct
	public void retrieveAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipoSaber> criteria = cb.createQuery(TipoSaber.class);
		Root<TipoSaber> tipoSaber = criteria.from(TipoSaber.class);
		criteria.select(tipoSaber).orderBy(cb.asc(tipoSaber.get("nombre")));
		tipoSaberes = em.createQuery(criteria).getResultList();
	}
}
