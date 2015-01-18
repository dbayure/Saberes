package uy.com.antel.Saberes_pruebaWS;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.junit.Test;

import WebServices.sgp.antel.com.DatoPer;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerService;
import uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerServiceLocator;

public class PruebaWS {

	@Test
	public void test() {
		 
		WsDatosPerService service = new WsDatosPerServiceLocator();
		
		try {
			WsDatosPer llamada = service.getWSDatoPer();
			
			String sCedula = "4138704";
			String sAppl = "SABERES";
			String sPass = "SABERES";
			DatoPer datos = llamada.getDatosPer(sCedula, sAppl, sPass);
			
			String nombre = datos.getNombre().trim();
			assertEquals(nombre, "Rodrigo Gonzalo");
			
			String priape = datos.getPriape().trim();
			assertEquals(priape, "Perez");
			
			String segape = datos.getSegape().trim();
			assertEquals(segape, "Haretche");
			
			assertEquals(priape+" "+segape, "Perez Haretche");
			
//			String nombreCompleto = datos.getNombreCompleto().trim();
//			assertEquals(nombreCompleto, "Rodrigo Gonzalo Perez Haretche");
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
