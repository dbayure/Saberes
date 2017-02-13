package uy.com.antel.Saberes.bean;

 
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {
     
	private static final long serialVersionUID = 1L;
	
    private Map<String,Map<String,String>> pais = new HashMap<String, Map<String,String>>();
	private Map<String, Map<String,String>> ciudades = new HashMap<String, Map<String,String>>();
    private String country; 
    private String city;  
    private String barrio;  
    private Map<String,String> countries;
    private Map<String,String> cities;
    private Map<String,String> barrios;
    private boolean mostrarcombo = false;
     
    public boolean isMostrarcombo() {
		return mostrarcombo;
	}

	public void setMostrarcombo(boolean mostrarcombo) {
		this.mostrarcombo = mostrarcombo;
	}

	@PostConstruct
    public void init() {
    	Map<String,String> bar = new HashMap<String, String>();
    	Map<String,String> ciu = new HashMap<String, String>();
    	
    	countries  = new HashMap<String, String>();
        countries.put("USA", "USA");
        countries.put("Germany", "Germany");
        countries.put("Brazil", "Brazil");
         
        ciu.put("New York", "New York");
        ciu.put("San Francisco", "San Francisco");
        ciu.put("Denver", "Denver");
        
        pais.put("USA", ciu);
        
        bar = new HashMap<String, String>();
        bar.put("ny1", "ny1");
        bar.put("ny2", "ny2");
        bar.put("ny3", "ny3");
        ciudades.put("New York", bar);
        
        bar = new HashMap<String, String>();
        bar.put("sf1", "sf1");
        bar.put("sf2", "sf2");
        bar.put("sf3", "sf3");
        ciudades.put("San Francisco", bar);
        
        bar = new HashMap<String, String>();
        bar.put("B1", "B1");
        bar.put("b2", "b2");
        bar.put("b3", "b3");
        ciudades.put("Central Parck", bar);
         
        ciu = new HashMap<String, String>();
        ciu.put("Berlin", "Berlin");
        ciu.put("Munich", "Munich");
        ciu.put("Frankfurt", "Frankfurt");
        
        pais.put("Germany", ciu);
        
        bar = new HashMap<String, String>();
        bar.put("be1", "be1");
        bar.put("be2", "be2");
        bar.put("be3", "be3");
        ciudades.put("Berlin", bar);
        
        bar = new HashMap<String, String>();
        bar.put("mu1", "mu1");
        bar.put("mu2", "mu2");
        bar.put("mu3", "mu3");
        ciudades.put("Munich", bar);
        
        bar = new HashMap<String, String>();
        bar.put("fr1", "fr1");
        bar.put("fr2", "fr2");
        bar.put("fr3", "fr3");
        ciudades.put("Frankfurt", bar);
        
        ciu = new HashMap<String, String>();
        ciu.put("Sao Paolo", "Sao Paolo");
        ciu.put("Rio de Janerio", "Rio de Janerio");
        ciu.put("Salvador", "Salvador");
        
        pais.put("Brazil", ciu);
        
        bar = new HashMap<String, String>();
        bar.put("sp1", "sp1");
        bar.put("sp2", "sp2");
        bar.put("sp3", "sp3");
        ciudades.put("Sao Paolo", bar);
        
        bar = new HashMap<String, String>();
        bar.put("rio1", "rio1");
        bar.put("rio2", "rio2");
        bar.put("rio3", "rio3");
        ciudades.put("Rio de Janerio", bar);
        
        bar = new HashMap<String, String>();
        bar.put("sal1", "sal1");
        bar.put("sal2", "sal2");
        bar.put("sal3", "sal3");
        ciudades.put("Salvador", bar);
    }
 
    public Map<String, Map<String, String>> getPais() {
        return pais;
    }
    
    public Map<String, Map<String, String>> getCiudades() {
        return ciudades;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getBarrio() {
        return barrio;
    }
 
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
 
    public Map<String, String> getCountries() {
        return countries;
    }
 
    public  Map<String, String> getCities() {
        return cities;
    }
 
    public Map<String, String> getBarrios() {
        return barrios;
    }
    
    public void onCountryChange() {
    	System.out.println("valor del mostrar " + mostrarcombo);
        if(country !=null && !country.equals("")){
    		setMostrarcombo(true);
            cities = pais.get(country);
        }
        else{
            cities = new HashMap<String, String>();
            setMostrarcombo(false);
        }
        System.out.println("valor del mostrar " + mostrarcombo);
    }
     
    public void onCityChange() {
        if(city !=null && !city.equals("")){
            barrios = ciudades.get(city);

        }
        else{
            barrios = new HashMap<String, String>();
        }
    }
    
    public void displayLocation() {
        FacesMessage msg;
        if(city != null && country != null)
            msg = new FacesMessage("Selected", city + " of " + country);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void mostrarCombo(){
    	System.out.println("valor del mostrar " + mostrarcombo);
    	if(city !=null && !city.equals(""))
    		setMostrarcombo(false);
    	else
    		setMostrarcombo(true);
    	System.out.println("valor del mostrar " + mostrarcombo);
    }
}