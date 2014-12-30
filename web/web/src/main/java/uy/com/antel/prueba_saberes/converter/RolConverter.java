package uy.com.antel.prueba_saberes.converter;

import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import uy.com.antel.prueba_saberes.model.Rol;


@FacesConverter(forClass = Rol.class, value = "rolConverter")
public class RolConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			value = ((HttpServletRequest) context.getExternalContext().getRequest()).getParameter(component.getClientId()+"_input");
//			return null;
		}
		Rol rol = null;
		try {
			ObjectMapper mapper = new ObjectMapper();	
			String requestScheme = context.getExternalContext().getRequestScheme();
			String requestServerName = context.getExternalContext().getRequestServerName();
			int requestServerPort = context.getExternalContext().getRequestServerPort();
			String requestContextPath = context.getExternalContext().getRequestContextPath();
			rol = mapper.readValue(new URL( requestScheme + "://" + requestServerName + ":"  + requestServerPort + requestContextPath + "/rest/roles/" + value), Rol.class);
		}
		catch(Exception e) {
			throw new ConverterException();
		}
		return rol;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return "";
        } else {
        	return  String.valueOf(((Rol)value).getId());
        }		
	}

//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Rol rol = em.find(Rol.class, Long.getLong(value));
//		return rol;
//	}

	

	
}
