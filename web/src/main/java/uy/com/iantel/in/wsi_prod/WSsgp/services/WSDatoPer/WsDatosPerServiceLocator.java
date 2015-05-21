/**
 * WsDatosPerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer;

@SuppressWarnings("all")
public class WsDatosPerServiceLocator extends org.apache.axis.client.Service implements uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPerService {

    public WsDatosPerServiceLocator() {
    }


    public WsDatosPerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsDatosPerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSDatoPer
    private java.lang.String WSDatoPer_address = "http://wsi-prod.in.iantel.com.uy/WSsgp/services/WSDatoPer";

    public java.lang.String getWSDatoPerAddress() {
        return WSDatoPer_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSDatoPerWSDDServiceName = "WSDatoPer";

    public java.lang.String getWSDatoPerWSDDServiceName() {
        return WSDatoPerWSDDServiceName;
    }

    public void setWSDatoPerWSDDServiceName(java.lang.String name) {
        WSDatoPerWSDDServiceName = name;
    }

    public uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer getWSDatoPer() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSDatoPer_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSDatoPer(endpoint);
    }

    public uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer getWSDatoPer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WSDatoPerSoapBindingStub _stub = new uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WSDatoPerSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSDatoPerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSDatoPerEndpointAddress(java.lang.String address) {
        WSDatoPer_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WsDatosPer.class.isAssignableFrom(serviceEndpointInterface)) {
                uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WSDatoPerSoapBindingStub _stub = new uy.com.iantel.in.wsi_prod.WSsgp.services.WSDatoPer.WSDatoPerSoapBindingStub(new java.net.URL(WSDatoPer_address), this);
                _stub.setPortName(getWSDatoPerWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSDatoPer".equals(inputPortName)) {
            return getWSDatoPer();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wsi-prod.in.iantel.com.uy/WSsgp/services/WSDatoPer", "wsDatosPerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wsi-prod.in.iantel.com.uy/WSsgp/services/WSDatoPer", "WSDatoPer"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSDatoPer".equals(portName)) {
            setWSDatoPerEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
