package uy.com.antel.Saberes.timer;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import uy.com.antel.Saberes.controller.RegistroCorporativo;
import uy.com.antel.Saberes.controller.RegistroPersona;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class CargaCorporativos {
	
	@Inject
	private RegistroCorporativo controladorCorporativo;
	
	@Inject
	private RegistroPersona controladorPersona;
	
	@Resource
	private SessionContext ctx;
	
	@EJB
	WorkerTimer worker;
	
    @Resource
    private UserTransaction userTransaction;
   
    public CargaCorporativos() {
        // TODO Auto-generated constructor stub
    }
	
	@Schedule(minute="53", hour="13", dayOfWeek="Mon-Fri", dayOfMonth="*", month="*", year="*", info="TimerCargaExpediente")
    private void scheduledTimeout(final Timer t) throws Exception {
        System.out.println("Ejecutando el timer: " + new java.util.Date());
		List <Object[]> usuarios = controladorPersona.getAllPersonas();
		UserTransaction ut = ctx.getUserTransaction();
		int i = 0;
		for (Object[] p : usuarios) {
			Long id = ((BigInteger)(p[0])).longValue();
			String usuario = ((String)(p[1]));
			List <Object[]> cursos = controladorCorporativo.getCursosCorporativosPersonasGicca((controladorPersona.getCI(usuario)));
			//System.out.println("Usuario: "+usuario);
			for (Object[] obj : cursos) {
				i++;
				ut.begin();
				worker.trabajar(obj, id);	
				ut.commit();
				if ( i % 1000 == 0 ) {
					System.out.println("Proc: "+i);
				}				
			}
		}
		System.out.println("FIN TIMER: Se ejecuto correctamente la actualización");
		
    }
	
	@Timeout
	public void timeout(Timer timer) {
	    System.out.println("Llegamos al time out");
	}
	
}