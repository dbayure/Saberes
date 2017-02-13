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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import uy.com.antel.Saberes.model.Comprobante;
import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.Institucion;
import uy.com.antel.Saberes.model.NoCorporativo;
import uy.com.antel.Saberes.model.Origen;
import uy.com.antel.Saberes.model.Persona;
import uy.com.antel.Saberes.model.Rol;
import uy.com.antel.Saberes.model.Saber;
import uy.com.antel.Saberes.model.SaberPersona;
import uy.com.antel.Saberes.model.TipoSaber;

@RequestScoped
public class ExtrasListProducer {
	
	@Inject
	private EntityManager em;
	
    public List<Object[]> obtenerCorporativoPorFechayRol(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<Corporativo> sc = criterio.from(Corporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
                
        Path<String> ruta = sc.get("rolSaber");
        Path<Number> fechainicio = sp.get("fechaInicio");
        Path<String> nombresaber = s.get("nombre");
        
        criterio.multiselect(cb.max(fechainicio), cb.count(sc), nombresaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        		       (cb.equal(sp.get("saber").get("id"), s.get("id"))),
        		       (cb.equal(p.get("id"), sp.get("persona"))),
		 			   (cb.equal(sp.get("id"), sc.get("id"))),
		 			   (cb.like(ruta, "CERTIFICA%")));
        criterio.groupBy(nombresaber);
        List<Object[]> saberesPorFechayRol = em.createQuery(criterio).getResultList();
        return saberesPorFechayRol;
    }
    
    public List<Object[]> obtenerSaberFormalCurricularPorUsuario(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<NoCorporativo> nc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
        Root<Comprobante> c = criterio.from(Comprobante.class);
        Root<Institucion> i = criterio.from(Institucion.class);
        Root<TipoSaber> t = criterio.from(TipoSaber.class);

                
        Path<String> institucion = i.get("nombre");
        Path<String> nombreSaber = s.get("nombre");
        Path<String> duracion = s.get("duracion");
        Path<Number> fechaInicio = sp.get("fechaInicio");
        Path<Number> fechaFin = sp.get("fechaFin");
        Path<String> aprobacion = sp.get("aprobacion");
        Path<Number> idSaber = sp.get("id");
        Path<String> validado = nc.get("validado");
        Path<Number> comprobante = c.get("id");
        Path<String> tipoSaber = t.get("nombre");
        
        criterio.multiselect(institucion, nombreSaber, fechaInicio, fechaFin, aprobacion, duracion, validado, comprobante, idSaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        			  (cb.equal(p.get("id"), sp.get("persona"))),
        		      (cb.equal(sp.get("saber").get("id"), s.get("id"))),
		 			  (cb.equal(sp.get("id"), nc.get("id"))),
		 			  (cb.equal(sp.get("comprobante").get("id"), c.get("id"))),
		 			  (cb.equal(s.get("institucion").get("id"), i.get("id"))),
		 			  (cb.equal(s.get("tipoSaber").get("id"), t.get("id"))),
		 			  (cb.like(tipoSaber, "Formal Curricular")));
        
        List<Object[]> corporativosPorFechaTol = em.createQuery(criterio).getResultList();
        return corporativosPorFechaTol;
    }
    
    public List<Object[]> obtenerSaberFormalNoCorporativoPorUsuario(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<NoCorporativo> nc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
        Root<Comprobante> c = criterio.from(Comprobante.class);
        Root<Institucion> i = criterio.from(Institucion.class);
        Root<TipoSaber> t = criterio.from(TipoSaber.class);

                
        Path<String> institucion = i.get("nombre");
        Path<String> nombreSaber = s.get("nombre");
        Path<String> duracion = s.get("duracion");
        Path<Number> fechaInicio = sp.get("fechaInicio");
        Path<Number> fechaFin = sp.get("fechaFin");
        Path<String> aprobacion = sp.get("aprobacion");
        Path<Number> idSaber = sp.get("id");
        Path<String> validado = nc.get("validado");
        Path<Number> comprobante = c.get("id");
        Path<String> tipoSaber = t.get("nombre");
        
        criterio.multiselect(institucion, nombreSaber, fechaInicio, fechaFin, aprobacion, duracion, validado, comprobante, idSaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        			  (cb.equal(p.get("id"), sp.get("persona"))),
        		      (cb.equal(sp.get("saber").get("id"), s.get("id"))),
		 			  (cb.equal(sp.get("id"), nc.get("id"))),
		 			  (cb.equal(sp.get("comprobante").get("id"), c.get("id"))),
		 			  (cb.equal(s.get("institucion").get("id"), i.get("id"))),
		 			  (cb.equal(s.get("tipoSaber").get("id"), t.get("id"))),
		 			  (cb.like(tipoSaber, "Formal No Corporativo")));
        
        List<Object[]> formalNoCorporativos = em.createQuery(criterio).getResultList();
        return formalNoCorporativos;
    }
    
    public List<Object[]> obtenerSaberFormalCorporativoPorUsuario(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<Corporativo> sc = criterio.from(Corporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
        Root<TipoSaber> t = criterio.from(TipoSaber.class);
                
        Path<String> nombreSaber = s.get("nombre");
        Path<String> duracion = s.get("duracion");
        Path<Number> fechaInicio = sp.get("fechaInicio");
        Path<Number> fechaFin = sp.get("fechaFin");
        Path<String> aprobacion = sp.get("aprobacion");
        Path<Number> puntaje = sc.get("puntaje");
        Path<Number> libreta = sc.get("libreta");
        Path<String> rolSaber = sc.get("rolSaber");
        Path<String> tipoSaber = t.get("nombre");
        
        criterio.multiselect(nombreSaber, fechaInicio, fechaFin, aprobacion, puntaje, duracion, libreta, rolSaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        			  (cb.equal(p.get("id"), sp.get("persona"))),
        		      (cb.equal(sp.get("saber").get("id"), s.get("id"))),
		 			  (cb.equal(sp.get("id"), sc.get("id"))),
		 			  (cb.equal(s.get("tipoSaber").get("id"), t.get("id"))),
		 			  (cb.like(tipoSaber, "Formal Corporativo")),
        			  (cb.like(rolSaber, "ASISTENTE")));
        
        List<Object[]> formalCorporatovos = em.createQuery(criterio).getResultList();
        return formalCorporatovos;
    }

    public List<Object[]> obtenerSaberNoFormalConocimientosPorUsuario(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<NoCorporativo> nc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
        Root<Comprobante> c = criterio.from(Comprobante.class);
        Root<Institucion> i = criterio.from(Institucion.class);
        Root<TipoSaber> t = criterio.from(TipoSaber.class);
        Root<Origen> o = criterio.from(Origen.class);

        Path<String> nombreSaber = s.get("nombre");
        Path<Number> fechaInicio = sp.get("fechaInicio");
        Path<Number> fechaFin = sp.get("fechaFin");
        Path<Number> idSaber = sp.get("id");
        Path<String> validado = nc.get("validado");
        Path<String> tipoSaber = t.get("nombre");
        Path<String> origen = o.get("descripcion");
        
        criterio.multiselect( nombreSaber, origen, fechaInicio, fechaFin, validado, idSaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        			  (cb.equal(p.get("id"), sp.get("persona"))),
        		      (cb.equal(sp.get("saber").get("id"), s.get("id"))),
		 			  (cb.equal(sp.get("id"), nc.get("id"))),
		 			  (cb.equal(nc.get("origen").get("id"), o.get("id"))),
		 			  (cb.equal(sp.get("comprobante").get("id"), c.get("id"))),
		 			  (cb.equal(s.get("institucion").get("id"), i.get("id"))),
		 			  (cb.equal(s.get("tipoSaber").get("id"), t.get("id"))),
		 			  (cb.like(tipoSaber, "No Formal Conocimientos")));
        
        List<Object[]> noFormalConocimientos = em.createQuery(criterio).getResultList();
        return noFormalConocimientos;
    }
    
    public List<Object[]> obtenerSaberNoFormalCursosPorUsuario(Persona persona) {
    	String usuario = persona.getUsuario();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<NoCorporativo> nc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        Root<Saber> s = criterio.from(Saber.class);
        Root<Comprobante> c = criterio.from(Comprobante.class);
        Root<Institucion> i = criterio.from(Institucion.class);
        Root<TipoSaber> t = criterio.from(TipoSaber.class);

                
        Path<String> institucion = i.get("nombre");
        Path<String> nombreSaber = s.get("nombre");
        Path<String> duracion = s.get("duracion");
        Path<Number> fechaInicio = sp.get("fechaInicio");
        Path<Number> fechaFin = sp.get("fechaFin");
        Path<Number> idSaber = sp.get("id");
        Path<String> validado = nc.get("validado");
        Path<Number> comprobante = c.get("id");
        Path<String> tipoSaber = t.get("nombre");
        
        criterio.multiselect(institucion, nombreSaber, fechaInicio, fechaFin, duracion, validado, comprobante, idSaber);
        criterio.where(cb.and(cb.equal(p.get("usuario"), usuario)),
        			  (cb.equal(p.get("id"), sp.get("persona"))),
        		      (cb.equal(sp.get("saber").get("id"), s.get("id"))),
		 			  (cb.equal(sp.get("id"), nc.get("id"))),
		 			  (cb.equal(sp.get("comprobante").get("id"), c.get("id"))),
		 			  (cb.equal(s.get("institucion").get("id"), i.get("id"))),
		 			  (cb.equal(s.get("tipoSaber").get("id"), t.get("id"))),
		 			  (cb.like(tipoSaber, "No Formal Cursos")));
        
        List<Object[]> noFormalCursos = em.createQuery(criterio).getResultList();
        return noFormalCursos;
    }
    
    public List<Object[]> obtenerPersonasPorIdUsuario() {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criterio = cb.createQuery(Object[].class);
        
        Root<Persona> p = criterio.from(Persona.class);
        
        Path<Number> id = p.get("id");
        Path<String> usuario = p.get("usuario");
        
        criterio.multiselect(id,usuario);
        List<Object[]> personaIdUsuario = em.createQuery(criterio).getResultList();
        return personaIdUsuario;
    }
    
    public Persona obtenerPersonaPorUsuario(String usuario) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criterio = cb.createQuery(Persona.class);

        Root<Persona> p = criterio.from(Persona.class);
        criterio.select(p);
        criterio.where(cb.equal(p.get("usuario"), usuario));
        try {
        	Persona persona = em.createQuery(criterio).getSingleResult();
        	return persona;
		} catch (NoResultException e) {
			return null;
		}
    }

    public List<Persona> obtenerPersonasConSaberesPendienteDeValidar() {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criterio = cb.createQuery(Persona.class);
        
        Root<NoCorporativo> snc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        
        Join<Persona, SaberPersona> joinpersona = sp.join("persona");
        
        Path<String> validado = snc.get("validado");
        
        criterio.select(p);
        criterio.distinct(true);
        criterio.where(cb.equal(p.get("id"), joinpersona.get("id")),
	 			   	  (cb.equal(sp.get("id"), snc.get("id"))),
	 			   	  (cb.like(validado, "P")));
        
        List<Persona> personas = em.createQuery(criterio).getResultList();
        return personas;
    }
    
    public List<NoCorporativo> obtenerSaberesPendientesDeValidarPorPersona(Long id) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NoCorporativo> criterio = cb.createQuery(NoCorporativo.class);
        
        Root<NoCorporativo> snc = criterio.from(NoCorporativo.class);
        Root<SaberPersona> sp = criterio.from(SaberPersona.class);
        Root<Persona> p = criterio.from(Persona.class);
        
        Join<Persona, SaberPersona> joinpersona = sp.join("persona");
        
        Path<String> validado = snc.get("validado");
        
        criterio.select(snc);
        criterio.where(cb.equal(p.get("id"), id),
        			  (cb.equal(p.get("id"), joinpersona.get("id"))),
	 			   	  (cb.equal(sp.get("id"), snc.get("id"))),
	 			   	  (cb.like(validado, "P")));
        
        List<NoCorporativo> pendientes = em.createQuery(criterio).getResultList();
        return pendientes;
    }
    
	public boolean buscarSaberRepetido(String nombre) {
		 Query q = em.createQuery("SELECT s FROM Saber s WHERE UPPER(TRANSLATE(s.nombre, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
					"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
		 q.setParameter(1,nombre);
		 if (q.getResultList().isEmpty()){
			 return false;
		 	}
		 else{
			return true;
		}
	  }
 
	public List<Saber> buscarPorInstitucion(Long idInstitucion, Long tipo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Saber> criterio = cb.createQuery(Saber.class);
	     
	    Root<Saber> s = criterio.from(Saber.class);	
	    
	    criterio.select(s);
	    criterio.where(cb.and(cb.equal(s.get("institucion"), idInstitucion)),
	    			   cb.equal(s.get("tipoSaber"), tipo));
		List <Saber> resultado = em.createQuery(criterio).getResultList();
	
		return resultado;
	}
	
	public Saber buscarPorCodGicca(Integer codeCurso) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Saber> criterio = cb.createQuery(Saber.class);
	     
	    Root<Saber> s = criterio.from(Saber.class);	
	    
	    criterio.select(s);
	    criterio.where(cb.equal(s.get("codgicca"), codeCurso));
	    
		Saber resultado = em.createQuery(criterio).getSingleResult();
		
		if (resultado == null){
			return null;
		}
		else{
			return resultado;
		}
	}
	
	public boolean buscarOrigenRepedito(String descripcion) {
		Query q = em.createQuery("SELECT o FROM Origen o WHERE UPPER(TRANSLATE(o.descripcion, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
					"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
		q.setParameter(1,descripcion);
		if (q.getResultList().isEmpty()){
			return false;
		 }else{
			   return true;
		   }
	}
	
	public boolean buscarInstitucionRepetida(String nombre) {
	   Query q = em.createQuery("SELECT i FROM Institucion i WHERE UPPER(TRANSLATE(i.nombre, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
		"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
	   q.setParameter(1,nombre);
	   if (q.getResultList().isEmpty()){
		   return false;
	   }
	   else{
		   return true;
	   }
     }
	
	public Rol buscarPerfilBasico() throws Exception{
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Rol> criterio = cb.createQuery(Rol.class);
		 
		 Root<Rol> rol = criterio.from(Rol.class);	
		    
		 criterio.select(rol);
		 criterio.where(cb.equal(rol.get("rol"), "BASICO"));
		 Rol resultado = em.createQuery(criterio).getSingleResult();
		 return resultado;
	  }
	   
	public boolean buscarRolRepetido(String rol) {
		   Query q = em.createQuery("SELECT r FROM Rol r WHERE UPPER(TRANSLATE(r.rol, 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))" + 
			"LIKE UPPER(TRANSLATE((?1), 'ÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙáàãâäéèêëíìîïóòõôöúùûü', 'AAAAAEEEEIIIIOOOOOUUaaaaaeeeeiiiiooooouuuu'))");
		   q.setParameter(1,rol);
		   if (q.getResultList().isEmpty()){
			   return false;
		   }
		   else{
			   return true;
		   }
	   }
}
