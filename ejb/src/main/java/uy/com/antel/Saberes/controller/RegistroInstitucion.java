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
import javax.persistence.Query;

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
		   Query q = em.createQuery("SELECT i FROM Institucion i WHERE UPPER(TRANSLATE(i.nombre, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
			"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
		   q.setParameter(1,newInstitucion.getNombre());
		   if (q.getResultList().isEmpty()){
			   return false;
		   }
		   else{
			   return true;
		   }
	   }
	   
	   public List<Institucion> ObtenerInsituciones(String letra) {
		   List<Institucion> instFiltradas = new ArrayList<Institucion>();
	         
	        for (int i = 0; i < instituciones.getInstituciones().size(); i++) {
	            Institucion institucion = instituciones.getInstituciones().get(i);
	            if(institucion.getNombre().toLowerCase().startsWith(letra)) {
	            	instFiltradas.add(institucion);
	            }
	        }
	        return instFiltradas;
	    }

	   public char getGrupoInsituciones(Institucion institucion) {
	        return institucion.getNombre().charAt(0);
	    }
	   
	   @PostConstruct
	   public void initNewInstitucion() {
		   newInstitucion = new Institucion();
	   }
}
