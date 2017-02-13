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
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import uy.com.antel.Saberes.data.ExtrasListProducer;
import uy.com.antel.Saberes.data.InstitucionListProducer;
import uy.com.antel.Saberes.model.Institucion;


@Stateful
@Model
public class RegistroInstitucion {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Institucion> institucionEventSrc;
	   
	   @Inject
	   private InstitucionListProducer instituciones;
	   
	   @Inject
	   private ExtrasListProducer elp;

	   private Institucion newInstitucion;
	   
	   @Produces
	   @Named
	   public Institucion getNewInstitucion() {
	      return newInstitucion;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newInstitucion.getNombre());
	      em.persist(newInstitucion);
	      institucionEventSrc.fire(newInstitucion);
	      initNewInstitucion();
	   }
	   
	   public void modificar(Institucion institucion) throws Exception {
		   log.info("Modifico " + institucion);
		   em.merge(institucion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Institucion institucion = em.find(Institucion.class, id);
		   em.remove(institucion);
		   institucionEventSrc.fire(newInstitucion);
	   }
	   
	   public boolean buscarInstitucionRepetida() {
		   return elp.buscarInstitucionRepetida(newInstitucion.getNombre());
	   }
	   
	   public List<Institucion> ObtenerInsituciones(String letra) {
		   List<Institucion> instFiltradas = new ArrayList<Institucion>();
	         
	        for (int i = 0; i < instituciones.getInstituciones().size(); i++) {
	            Institucion institucion = instituciones.getInstituciones().get(i);
	            if((institucion.getNombre().toLowerCase().startsWith(letra))||(institucion.getNombre().startsWith(letra))) {
	            	instFiltradas.add(institucion);
	            }
	        }
	        return instFiltradas;
	    }
	   
	   public Institucion buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Institucion institucion = em.find(Institucion.class, id);
		   return institucion;
	   }

	   public char getGrupoInsituciones(Institucion institucion) {
	        return institucion.getNombre().charAt(0);
	    }
	   
	   @PostConstruct
	   public void initNewInstitucion() {
		   newInstitucion = new Institucion();
	   }
}
