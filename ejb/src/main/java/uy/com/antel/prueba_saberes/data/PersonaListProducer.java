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

import uy.com.antel.prueba_saberes.model.Persona;
import uy.com.antel.prueba_saberes.model.Saber;


@RequestScoped
public class PersonaListProducer {

	@Inject
	private EntityManager em;

	private List<Persona> personas;


	@Produces
	@Named
	public List<Persona> getPersonas() {
		return personas;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Persona persona) {
		retrieveAllOrderedByName();
	}

	private void retrieveAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Persona> criteria = cb.createQuery(Persona.class);
		Root<Persona> persona = criteria.from(Persona.class);
		criteria.select(persona).orderBy(cb.asc(persona.get("nombre")));
		persona = (Root<Persona>) em.createQuery(criteria).getResultList();
		
	}


}
