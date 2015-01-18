package uy.com.antel.Saberes.filtro;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName="loginFiltro",urlPatterns="/paginas/formulario/j_security_check")
public class FiltroLogin implements Filter {
	
	 protected FilterConfig filterConfig;
	
    public FiltroLogin() {
    }

	public void destroy() {
		 this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
	    HttpServletResponse res = (HttpServletResponse)response;

	      // pre login action
	      
	      // get username 
	      String username = req.getParameter("j_username");
	      System.out.println("ESTOY");
	      
	      // call next filter in the chain : let j_security_check authenticate 
	      // user
	      chain.doFilter(request, response);

	      // post login action
	}

	public void init(FilterConfig fConfig) throws ServletException {
		 this.filterConfig = filterConfig;
	}

}
