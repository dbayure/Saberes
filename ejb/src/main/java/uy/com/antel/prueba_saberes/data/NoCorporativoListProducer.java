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

import uy.com.antel.prueba_saberes.model.NoCorporativo;


@RequestScoped
public class NoCorporativoListProducer {

	@Inject
	private EntityManager em;

	private List<NoCorporativo> noCorporativos;


	@Produces
	@Named
	public List<NoCorporativo> getNoCorporativos() {
		return noCorporativos;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final NoCorporativo noCorporativo) {
		retrieveAllOrderedByName();
	}

	@PostConstruct
	public void retrieveAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<NoCorporativo> criteria = cb.createQuery(NoCorporativo.class);
		Root<NoCorporativo> noCorporativo = criteria.from(NoCorporativo.class);
		criteria.select(noCorporativo).orderBy(cb.asc(noCorporativo.get("corporativo")));
		noCorporativos = em.createQuery(criteria).getResultList();
	}
}
