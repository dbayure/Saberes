/*
SABERES - Registro de conocimientos, aptitudes del personal de la empresa
Copyright (C) 2009  ANTEL
This file is part of SABERES.
SABERES is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 
*/
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
