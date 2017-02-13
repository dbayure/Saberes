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

import uy.com.antel.Saberes.model.SaberPersona;

@RequestScoped
public class SaberPersonaListProducer {

   @Inject
   private EntityManager em;

   private List<SaberPersona> saberesPersona;

   @Produces
   @Named
   public List<SaberPersona> getSaberPersona() {
      return saberesPersona;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SaberPersona saberesPersona) {
	      retrieveAllOrderedById();
   }

   @PostConstruct
   public void retrieveAllOrderedById() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<SaberPersona> criteria = cb.createQuery(SaberPersona.class);
      Root<SaberPersona> saberPersona = criteria.from(SaberPersona.class);
      criteria.select(saberPersona).orderBy(cb.asc(saberPersona.get("id")));
      saberesPersona = em.createQuery(criteria).getResultList();
   }
}
