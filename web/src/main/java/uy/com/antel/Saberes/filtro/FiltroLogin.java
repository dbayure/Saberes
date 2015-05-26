package uy.com.antel.Saberes.filtro;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jboss.security.SecurityContextAssociation;

import uy.com.antel.Saberes.bean.PersonaBean;

/**
 * Servlet Filter implementation class FiltroLogin
 */
public class FiltroLogin implements Filter {

	@Inject
	PersonaBean persona;
	
    /**
     * Default constructor. 
     */
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String userName = SecurityContextAssociation.getPrincipal().getName();

		persona.setUsuario(userName);

		persona.registrar();

		System.out.println("Yeeey! Get me here and find me in the database: "+ userName);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
