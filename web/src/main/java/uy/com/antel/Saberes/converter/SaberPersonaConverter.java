package uy.com.antel.Saberes.converter;

import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import uy.com.antel.Saberes.model.SaberPersona;


@FacesConverter(forClass = SaberPersona.class, value = "saberPersonaConverter")
public class SaberPersonaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			value = ((HttpServletRequest) context.getExternalContext().getRequest()).getParameter(component.getClientId()+"_input");
//			return null;
		}
		SaberPersona saberPersona = null;
		try {
			ObjectMapper mapper = new ObjectMapper();	
			String requestScheme = context.getExternalContext().getRequestScheme();
			String requestServerName = context.getExternalContext().getRequestServerName();
			int requestServerPort = context.getExternalContext().getRequestServerPort();
			String requestContextPath = context.getExternalContext().getRequestContextPath();
			saberPersona = mapper.readValue(new URL( requestScheme + "://" + requestServerName + ":"  + requestServerPort + requestContextPath + "/rest/saberesPersona/" + value), SaberPersona.class);
		}
		catch(Exception e) {
			throw new ConverterException();
		}
		return saberPersona;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return "";
        } else {
        	return  String.valueOf(((SaberPersona)value).getId());
        }		
	}

}
