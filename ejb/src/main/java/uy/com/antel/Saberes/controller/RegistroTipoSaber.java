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

import uy.com.antel.Saberes.model.TipoSaber;


@Stateful
@Model
public class RegistroTipoSaber {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<TipoSaber> tipoSaberEventSrc;

	   private TipoSaber newTipoSaber;
	   
	   @Produces
	   @Named
	   public TipoSaber getNewTipoSaber() {
	      return newTipoSaber;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTipoSaber.getNombre());
	      em.persist(newTipoSaber);
	      tipoSaberEventSrc.fire(newTipoSaber);
	      initNewTipoSaber();
	   }
	   
	   public void modificar(TipoSaber tipoSaber) throws Exception {
		   log.info("Modifico " + tipoSaber);
		   em.merge(tipoSaber);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   TipoSaber tipoSaber = em.find(TipoSaber.class, id);
		   em.remove(tipoSaber);
		   tipoSaberEventSrc.fire(newTipoSaber);
	   }
	   
	   public TipoSaber buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   TipoSaber tipoSaber = em.find(TipoSaber.class, id);
		   return tipoSaber;
	   }

	   @PostConstruct
	   public void initNewTipoSaber() {
		   newTipoSaber = new TipoSaber();
	   }
}
