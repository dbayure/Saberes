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
package uy.com.antel.Saberes.converter;

import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import uy.com.antel.Saberes.model.Rol;


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
			//throw new ConverterException();
			e.printStackTrace();
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
	
}
