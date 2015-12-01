package uy.com.antel.Saberes.converter;

import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import uy.com.antel.Saberes.model.Saber;


@FacesConverter(forClass = Saber.class, value = "saberConverter")
public class SaberConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			value = ((HttpServletRequest) context.getExternalContext().getRequest()).getParameter(component.getClientId()+"_input");
//			return null;
		}
		Saber saber = null;
		try {
			ObjectMapper mapper = new ObjectMapper();	
			String requestScheme = context.getExternalContext().getRequestScheme();
			String requestServerName = context.getExternalContext().getRequestServerName();
			int requestServerPort = context.getExternalContext().getRequestServerPort();
			String requestContextPath = context.getExternalContext().getRequestContextPath();
			saber = mapper.readValue(new URL( requestScheme + "://" + requestServerName + ":"  + requestServerPort + requestContextPath + "/rest/saberes/" + value), Saber.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return saber;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return "";
        } else {
        	return  String.valueOf(((Saber)value).getId());
        }		
	}

}
