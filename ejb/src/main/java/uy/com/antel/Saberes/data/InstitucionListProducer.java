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

import uy.com.antel.Saberes.model.Institucion;


@RequestScoped
public class InstitucionListProducer {

   @Inject
   private EntityManager em;

   private List<Institucion> instituciones;


   @Produces
   @Named
   public List<Institucion> getInstituciones() {
      return instituciones;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Institucion instituciones) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Institucion> criteria = cb.createQuery(Institucion.class);
      Root<Institucion> institucion = criteria.from(Institucion.class);
      criteria.select(institucion).orderBy(cb.asc(institucion.get("nombre")));
      instituciones = em.createQuery(criteria).getResultList();
   }
   
   public Institucion getInstitucionPorNombre(String nombre){
	  Institucion inst = new Institucion();
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Institucion> criteria = cb.createQuery(Institucion.class);
      Root<Institucion> institucion = criteria.from(Institucion.class);
      criteria.select(institucion);
      criteria.where(cb.equal(institucion.get("nombre"), nombre));
      inst = em.createQuery(criteria).getSingleResult();
      return inst;
   }
}
