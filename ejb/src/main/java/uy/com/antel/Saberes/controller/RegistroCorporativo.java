package uy.com.antel.Saberes.controller;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Query;

import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Saber;
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
	   
	   private static String SELECT_CORPORATIVOS = "select cedula, gpacurre.cod_curso,gpacurso.nombre,f_com_efec,f_fin_efec, aprobacion,puntaje,cant_asistencia,rol from gpaparev inner join gpacurre on (gpaparev.id_evento = gpacurre.id_evento) inner join gpacurso on (gpacurso.cod_curso=gpacurre.cod_curso) where cedula=? and aprobacion='S'";

	   
	   @Produces
	   @Named
	   public Corporativo getNewCorporativo() {
	      return newCorporativo;
	   }
	   
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
	   
	   public void registro(Long id) throws Exception {
//		      log.info("Registro " + newCorporativo.getSaber().getNombre());
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
	   
	   /*Metodo que se encarga de obtener los nuevos cursos corporativos desde la tabla GPACURSO, GPAPAREV y GPACURRE*/
		public  List<Object[]> getCursosCorporativosPersonas(Integer cedula){
			Query q = em.createNativeQuery(SELECT_CORPORATIVOS);
			q.setParameter(1,cedula);
			return q.getResultList();
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
