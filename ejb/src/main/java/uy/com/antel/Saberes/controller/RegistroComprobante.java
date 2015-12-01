package uy.com.antel.Saberes.controller;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import uy.com.antel.Saberes.model.Comprobante;

@Stateful
@Model
public class RegistroComprobante {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Comprobante> ComprobanteEventSrc;

	private Comprobante newComprobante;

	@Produces
	@Named
	public Comprobante getNewComprobante() {
		return newComprobante;
	}

	public void registro() throws Exception {
		log.info("Registro " + newComprobante.getNombre());
		em.persist(newComprobante);
		ComprobanteEventSrc.fire(newComprobante);
		initNewComprobante();
	}

	public void modificar(Comprobante comprobante) throws Exception {
		log.info("Modifico " + comprobante);
		em.merge(comprobante);
	}

	public void eliminar(Long id) throws Exception {
		log.info("Elimino " + id);
		Comprobante comprobante = em.find(Comprobante.class, id);
		em.remove(comprobante);
		ComprobanteEventSrc.fire(newComprobante);
	}

	@PostConstruct
	public void initNewComprobante() {
		newComprobante = new Comprobante();
	}

}
