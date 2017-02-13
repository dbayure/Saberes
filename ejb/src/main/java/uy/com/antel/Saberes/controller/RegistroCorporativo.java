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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.SaberPersona;


@Stateful
@Model
public class RegistroCorporativo {

	@Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

		@Inject
		private RegistroPersona rp;
	   
	   @Inject
	   private Event<Corporativo> corporativoEventSrc;

	   private Corporativo newCorporativo;
	   
	   public void registro(String usuario) throws Exception {
	      log.info("Registro " + newCorporativo.getSaber().getNombre());
	      Persona p = rp.buscarPersonaPorUsr(usuario);
	      List<SaberPersona> saberes = new ArrayList<SaberPersona>();
	      saberes.add(newCorporativo);
	      p.setSaberes(saberes);
	      em.persist(newCorporativo);
	      corporativoEventSrc.fire(newCorporativo);
	      initNewCorporativo();
	   }
	   
	   public String getDescripcion(String cod){
		   Query q = em.createQuery("select p from Gpatabla where p.codtab=?");
		   q.setParameter(1,cod);
		   return String.valueOf(q.getFirstResult());
	   }
	   
	   public void registro(Long id) throws Exception {
		      Persona p = rp.buscarPersona(id);
		      List<SaberPersona> saberes = new ArrayList<SaberPersona>();
		      saberes.add(newCorporativo);
		      p.setSaberes(saberes);
		      em.persist(newCorporativo);
		      corporativoEventSrc.fire(newCorporativo);
		      initNewCorporativo();
		   }
	   
	   public void modificar(Corporativo corporativo) throws Exception {
		   log.info("Modifico " + corporativo);
		   em.merge(corporativo);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Corporativo corporativo = em.find(Corporativo.class, id);
		   em.remove(corporativo);
		   corporativoEventSrc.fire(newCorporativo);
	   }
	
	   public void setNewCorporativo(Corporativo newCorporativo) {
			this.newCorporativo = newCorporativo;
		}

	   @PostConstruct
	   public void initNewCorporativo() {
		   newCorporativo = new Corporativo();
	   }
	   
	   public Corporativo obtenerCorpPorID(long id){
		   return em.find(Corporativo.class, id);
	   }
}
