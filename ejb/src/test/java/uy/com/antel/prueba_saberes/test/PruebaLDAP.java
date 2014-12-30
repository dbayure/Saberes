package uy.com.antel.prueba_saberes.test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.security.acl.Group;
import java.util.HashMap;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.callback.UsernamePasswordHandler;
import org.junit.Test;


public class PruebaLDAP {

	
	 static
	   {
	      try
	      {
	         Configuration.setConfiguration(new TestConfig());
	         System.out.println("Installed TestConfig as JAAS Configuration");
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	   }
	 
	 static class TestConfig extends Configuration
	   {
	      public void refresh()
	      {
	      }

	      public AppConfigurationEntry[] getAppConfigurationEntry(String name)
	      {
	         AppConfigurationEntry[] entry = null;
	         try
	         {
	            Class[] parameterTypes = {};
	            Method m = getClass().getDeclaredMethod(name, parameterTypes);
	            Object[] args = {};
	            entry = (AppConfigurationEntry[]) m.invoke(this, args);
	         }
	         catch(Exception e)
	         {
	         }
	         return entry;
	      }

	      AppConfigurationEntry[] testLdapExample1()
	      {
	         String name = "org.jboss.security.auth.spi.LdapLoginModule";
	         HashMap options = new HashMap();
	         options.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
	         options.put("java.naming.security.authentication", "simple");
	         options.put("java.naming.provider.url", "ldap://net.in.iantel.com.uy:389/");
	         options.put("rolesCtxDN", "cn=users,dc=net,dc=in,dc=iantel,dc=com,dc=uy");
	         options.put("matchOnUserDN", "true");
	         options.put("principalDNSuffix", "@net.in.iantel.com.uy");         
	         options.put("uidAttributeID", "sAMAccountName");
	         options.put("roleAttributeID", "memberOf");
	         options.put("roleAttributeIsDN", "true");
	         
	         AppConfigurationEntry ace = new AppConfigurationEntry(name,
	         AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
	         AppConfigurationEntry[] entry = {ace};
	         return entry;
	      }
	      
	      
	   }

	   public PruebaLDAP()
	   {
		   
	   }
	
	@Test
	public void test() throws LoginException {
		  
		  System.out.println("testLdapExample1");
	      //UsernamePasswordHandler handler = new UsernamePasswordHandler("e102830", "supernegritos12345".toCharArray());
		  UsernamePasswordHandler handler = new UsernamePasswordHandler("e138704", "".toCharArray());
	      LoginContext lc = new LoginContext("testLdapExample1", handler);
	      lc.login();

	      Subject subject = lc.getSubject();
	      System.out.println("Subject: "+subject);

	      Set groups = subject.getPrincipals(Group.class);
	      assertTrue("Principals contains your-uid", subject.getPrincipals().contains(new SimplePrincipal("e138704")));
	      Group roles = (Group) groups.iterator().next();
	      assertTrue("your-uid-role is a role", roles.isMember(new SimplePrincipal("e138704")));

	      lc.logout();
	}

}
