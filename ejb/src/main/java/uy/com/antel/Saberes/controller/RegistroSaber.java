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
import uy.com.antel.Saberes.model.Saber;


@Stateful
@Model
public class RegistroSaber {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Saber> saberEventSrc;

	   private Saber newSaber;
	   
	   @Inject
	   private ExtrasListProducer elp;
	   
	   @Produces
	   @Named
	   public Saber getNewSaber() {
	      return newSaber;
	   }

	   @PostConstruct
	   public void initNewSaber() {
		   newSaber = new Saber();
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newSaber.getNombre());
	      em.persist(newSaber);
	      saberEventSrc.fire(newSaber);
	      initNewSaber();
	   }
	   
	   public void modificar(Saber saber) throws Exception {
		   log.info("Modifico " + saber);
		   em.merge(saber);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Saber saber = em.find(Saber.class, id);
		   em.remove(saber);
		   saberEventSrc.fire(newSaber);
	   }

		public Saber buscarPorCodGicca(Integer codeCurso) {
			Saber resultado = elp.buscarPorCodGicca(codeCurso);
			
			if (resultado == null)
				return null;
			return resultado;
		}
	   
		public ArrayList<Saber> buscarPorInstitucion(Long idInstitucion, Long tipo) {
			List <Saber> resultado = elp.buscarPorInstitucion(idInstitucion, tipo);
			
			if (resultado.isEmpty())
				return null;
			return (ArrayList<Saber>) resultado;
		}
	
		 public boolean buscarSaberRepetido(String nombre) {
			 boolean repetido = elp.buscarSaberRepetido(nombre);
			 return repetido;
		 }
}
