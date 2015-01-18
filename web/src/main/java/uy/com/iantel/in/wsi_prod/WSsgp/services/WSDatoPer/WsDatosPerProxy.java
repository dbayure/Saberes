package uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer;

public class WsDatosPerProxy implements uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer {
  private String _endpoint = null;
  private uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer wsDatosPer = null;
  
  public WsDatosPerProxy() {
    _initWsDatosPerProxy();
  }
  
  public WsDatosPerProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsDatosPerProxy();
  }
  
  private void _initWsDatosPerProxy() {
    try {
      wsDatosPer = (new uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerServiceLocator()).getWSDatoPer();
      if (wsDatosPer != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsDatosPer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsDatosPer)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsDatosPer != null)
      ((javax.xml.rpc.Stub)wsDatosPer)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer getWsDatosPer() {
    if (wsDatosPer == null)
      _initWsDatosPerProxy();
    return wsDatosPer;
  }
  
  public WebServices.sgp.antel.com.DatoPer getDatosPer(java.lang.String sCedula, java.lang.String sAppl, java.lang.String sPass) throws java.rmi.RemoteException{
    if (wsDatosPer == null)
      _initWsDatosPerProxy();
    return wsDatosPer.getDatosPer(sCedula, sAppl, sPass);
  }
  
  
}