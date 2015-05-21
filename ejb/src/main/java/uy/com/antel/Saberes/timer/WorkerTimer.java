package uy.com.antel.Saberes.timer;

import java.util.Date;


import javax.ejb.Singleton;
import javax.inject.Inject;

import uy.com.antel.Saberes.controller.RegistroCorporativo;
import uy.com.antel.Saberes.controller.RegistroSaber;
import uy.com.antel.Saberes.model.Corporativo;
import uy.com.antel.Saberes.model.Saber;

@Singleton
public class WorkerTimer {
	
	@Inject
	private RegistroCorporativo controladorCorporativo;
	
	@Inject
	private RegistroSaber controladorSaber;
	
    public WorkerTimer() {
        // TODO Auto-generated constructor stub
    }
	
    public void trabajar(Object[] obj, Long id) throws Exception {
			Integer cod = (Integer)(obj[1]);
			Saber saberCorporativo = controladorSaber.buscarPorCodGicca(cod);
			Date f_ini = (java.util.Date) (obj[2]); 
			Date f_fin = (java.util.Date) (obj[3]);
			Integer puntaje = (Integer)(obj[4]);
			Integer asistencia = (Integer)(obj[5]);
			String rol = (String) (obj[6]);
			String libreta = (String) (obj[7]);
			String cod_area = (String) (obj[8]);
			Integer duracion = (Integer)(obj[9]);
					
			
			Corporativo cursoC = new Corporativo();
			cursoC.setIdEventoGicca(cod);
			cursoC.setSaber(saberCorporativo);
			cursoC.setFechaInicio(f_ini);
			cursoC.setFechaFin(f_fin);
			cursoC.setRolSaber(rol);
			cursoC.setAprobacion(true);
			cursoC.setPuntaje((int)(puntaje/100));
			cursoC.setAsistencia(asistencia);
			cursoC.setLibreta(libreta);
			cursoC.setDuracion(duracion);
			cursoC.setCod_area(cod_area);
			
			controladorCorporativo.setNewCorporativo(cursoC);
			controladorCorporativo.registro(id);
			}
}