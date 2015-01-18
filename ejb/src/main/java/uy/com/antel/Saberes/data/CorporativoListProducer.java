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

import uy.com.antel.Saberes.model.Corporativo;



@RequestScoped
public class CorporativoListProducer {

	@Inject
	private EntityManager em;

	private List<Corporativo> corporativos;


	@Produces
	@Named
	public List<Corporativo> getCorporativos() {
		return corporativos;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Corporativo corporativo) {
		retrieveAllOrderedByName();
	}

	@PostConstruct
	public void retrieveAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Corporativo> criteria = cb.createQuery(Corporativo.class);
		Root<Corporativo> corporativo = criteria.from(Corporativo.class);
		criteria.select(corporativo).orderBy(cb.asc(corporativo.get("corporativo")));
		corporativos = em.createQuery(criteria).getResultList();
	}
}
