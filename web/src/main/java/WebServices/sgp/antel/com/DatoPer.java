/**
 * DatoPer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WebServices.sgp.antel.com;

public class DatoPer  implements java.io.Serializable {
    private java.util.Calendar FIngre;

    private java.util.Calendar FNacim;

    private java.util.Vector RUFunc;

    private java.lang.String TDirecPer;

    private java.lang.String TInterPer;

    private java.lang.String area;

    private java.lang.String cantPag;

    private java.lang.String cedula;

    private java.lang.String centroResp;

    private java.lang.String clasePago;

    private java.lang.String csilac;

    private java.lang.String departamento;

    private java.lang.String descArea;

    private java.lang.String descClasePago;

    private java.lang.String descCsilac;

    private java.lang.String descDepartamento;

    private java.lang.String descDivision;

    private java.lang.String descEdificio;

    private java.lang.String descJornada;

    private java.lang.String descLocalidad;

    private java.lang.String descProfesion;

    private java.lang.String descRegimen;

    private java.lang.String descUnidad;

    private java.lang.String division;

    private java.lang.String edificio;

    private java.lang.String email;

    private java.lang.String errorSql;

    private boolean esFuncionario;

    private boolean esRP;

    private boolean esRU;

    private java.lang.String faxUnidad;

    private java.lang.String jornada;

    private java.lang.String localidad;

    private java.lang.String nivelJer;

    private java.lang.String nombre;

    private java.lang.String nombreCompleto;

    private java.lang.String oficina;

    private java.lang.String planta;

    private java.lang.String priape;

    private java.lang.String profesion;

    private java.lang.String programa;

    private java.lang.String regimen;

    private java.lang.String segape;

    private java.lang.String sexo;

    private java.lang.String telefonoUnidad;

    private int topeUnidadesRP;

    private int topeUnidadesRU;

    private java.lang.String unidad;

    private java.lang.String[] unidadRP;

    private java.lang.String[] unidadRU;

    private java.util.Vector unidadesRP;

    private java.util.Vector unidadesRU;

    public DatoPer() {
    }

    public DatoPer(
           java.util.Calendar FIngre,
           java.util.Calendar FNacim,
           java.util.Vector RUFunc,
           java.lang.String TDirecPer,
           java.lang.String TInterPer,
           java.lang.String area,
           java.lang.String cantPag,
           java.lang.String cedula,
           java.lang.String centroResp,
           java.lang.String clasePago,
           java.lang.String csilac,
           java.lang.String departamento,
           java.lang.String descArea,
           java.lang.String descClasePago,
           java.lang.String descCsilac,
           java.lang.String descDepartamento,
           java.lang.String descDivision,
           java.lang.String descEdificio,
           java.lang.String descJornada,
           java.lang.String descLocalidad,
           java.lang.String descProfesion,
           java.lang.String descRegimen,
           java.lang.String descUnidad,
           java.lang.String division,
           java.lang.String edificio,
           java.lang.String email,
           java.lang.String errorSql,
           boolean esFuncionario,
           boolean esRP,
           boolean esRU,
           java.lang.String faxUnidad,
           java.lang.String jornada,
           java.lang.String localidad,
           java.lang.String nivelJer,
           java.lang.String nombre,
           java.lang.String nombreCompleto,
           java.lang.String oficina,
           java.lang.String planta,
           java.lang.String priape,
           java.lang.String profesion,
           java.lang.String programa,
           java.lang.String regimen,
           java.lang.String segape,
           java.lang.String sexo,
           java.lang.String telefonoUnidad,
           int topeUnidadesRP,
           int topeUnidadesRU,
           java.lang.String unidad,
           java.lang.String[] unidadRP,
           java.lang.String[] unidadRU,
           java.util.Vector unidadesRP,
           java.util.Vector unidadesRU) {
           this.FIngre = FIngre;
           this.FNacim = FNacim;
           this.RUFunc = RUFunc;
           this.TDirecPer = TDirecPer;
           this.TInterPer = TInterPer;
           this.area = area;
           this.cantPag = cantPag;
           this.cedula = cedula;
           this.centroResp = centroResp;
           this.clasePago = clasePago;
           this.csilac = csilac;
           this.departamento = departamento;
           this.descArea = descArea;
           this.descClasePago = descClasePago;
           this.descCsilac = descCsilac;
           this.descDepartamento = descDepartamento;
           this.descDivision = descDivision;
           this.descEdificio = descEdificio;
           this.descJornada = descJornada;
           this.descLocalidad = descLocalidad;
           this.descProfesion = descProfesion;
           this.descRegimen = descRegimen;
           this.descUnidad = descUnidad;
           this.division = division;
           this.edificio = edificio;
           this.email = email;
           this.errorSql = errorSql;
           this.esFuncionario = esFuncionario;
           this.esRP = esRP;
           this.esRU = esRU;
           this.faxUnidad = faxUnidad;
           this.jornada = jornada;
           this.localidad = localidad;
           this.nivelJer = nivelJer;
           this.nombre = nombre;
           this.nombreCompleto = nombreCompleto;
           this.oficina = oficina;
           this.planta = planta;
           this.priape = priape;
           this.profesion = profesion;
           this.programa = programa;
           this.regimen = regimen;
           this.segape = segape;
           this.sexo = sexo;
           this.telefonoUnidad = telefonoUnidad;
           this.topeUnidadesRP = topeUnidadesRP;
           this.topeUnidadesRU = topeUnidadesRU;
           this.unidad = unidad;
           this.unidadRP = unidadRP;
           this.unidadRU = unidadRU;
           this.unidadesRP = unidadesRP;
           this.unidadesRU = unidadesRU;
    }


    /**
     * Gets the FIngre value for this DatoPer.
     * 
     * @return FIngre
     */
    public java.util.Calendar getFIngre() {
        return FIngre;
    }


    /**
     * Sets the FIngre value for this DatoPer.
     * 
     * @param FIngre
     */
    public void setFIngre(java.util.Calendar FIngre) {
        this.FIngre = FIngre;
    }


    /**
     * Gets the FNacim value for this DatoPer.
     * 
     * @return FNacim
     */
    public java.util.Calendar getFNacim() {
        return FNacim;
    }


    /**
     * Sets the FNacim value for this DatoPer.
     * 
     * @param FNacim
     */
    public void setFNacim(java.util.Calendar FNacim) {
        this.FNacim = FNacim;
    }


    /**
     * Gets the RUFunc value for this DatoPer.
     * 
     * @return RUFunc
     */
    public java.util.Vector getRUFunc() {
        return RUFunc;
    }


    /**
     * Sets the RUFunc value for this DatoPer.
     * 
     * @param RUFunc
     */
    public void setRUFunc(java.util.Vector RUFunc) {
        this.RUFunc = RUFunc;
    }


    /**
     * Gets the TDirecPer value for this DatoPer.
     * 
     * @return TDirecPer
     */
    public java.lang.String getTDirecPer() {
        return TDirecPer;
    }


    /**
     * Sets the TDirecPer value for this DatoPer.
     * 
     * @param TDirecPer
     */
    public void setTDirecPer(java.lang.String TDirecPer) {
        this.TDirecPer = TDirecPer;
    }


    /**
     * Gets the TInterPer value for this DatoPer.
     * 
     * @return TInterPer
     */
    public java.lang.String getTInterPer() {
        return TInterPer;
    }


    /**
     * Sets the TInterPer value for this DatoPer.
     * 
     * @param TInterPer
     */
    public void setTInterPer(java.lang.String TInterPer) {
        this.TInterPer = TInterPer;
    }


    /**
     * Gets the area value for this DatoPer.
     * 
     * @return area
     */
    public java.lang.String getArea() {
        return area;
    }


    /**
     * Sets the area value for this DatoPer.
     * 
     * @param area
     */
    public void setArea(java.lang.String area) {
        this.area = area;
    }


    /**
     * Gets the cantPag value for this DatoPer.
     * 
     * @return cantPag
     */
    public java.lang.String getCantPag() {
        return cantPag;
    }


    /**
     * Sets the cantPag value for this DatoPer.
     * 
     * @param cantPag
     */
    public void setCantPag(java.lang.String cantPag) {
        this.cantPag = cantPag;
    }


    /**
     * Gets the cedula value for this DatoPer.
     * 
     * @return cedula
     */
    public java.lang.String getCedula() {
        return cedula;
    }


    /**
     * Sets the cedula value for this DatoPer.
     * 
     * @param cedula
     */
    public void setCedula(java.lang.String cedula) {
        this.cedula = cedula;
    }


    /**
     * Gets the centroResp value for this DatoPer.
     * 
     * @return centroResp
     */
    public java.lang.String getCentroResp() {
        return centroResp;
    }


    /**
     * Sets the centroResp value for this DatoPer.
     * 
     * @param centroResp
     */
    public void setCentroResp(java.lang.String centroResp) {
        this.centroResp = centroResp;
    }


    /**
     * Gets the clasePago value for this DatoPer.
     * 
     * @return clasePago
     */
    public java.lang.String getClasePago() {
        return clasePago;
    }


    /**
     * Sets the clasePago value for this DatoPer.
     * 
     * @param clasePago
     */
    public void setClasePago(java.lang.String clasePago) {
        this.clasePago = clasePago;
    }


    /**
     * Gets the csilac value for this DatoPer.
     * 
     * @return csilac
     */
    public java.lang.String getCsilac() {
        return csilac;
    }


    /**
     * Sets the csilac value for this DatoPer.
     * 
     * @param csilac
     */
    public void setCsilac(java.lang.String csilac) {
        this.csilac = csilac;
    }


    /**
     * Gets the departamento value for this DatoPer.
     * 
     * @return departamento
     */
    public java.lang.String getDepartamento() {
        return departamento;
    }


    /**
     * Sets the departamento value for this DatoPer.
     * 
     * @param departamento
     */
    public void setDepartamento(java.lang.String departamento) {
        this.departamento = departamento;
    }


    /**
     * Gets the descArea value for this DatoPer.
     * 
     * @return descArea
     */
    public java.lang.String getDescArea() {
        return descArea;
    }


    /**
     * Sets the descArea value for this DatoPer.
     * 
     * @param descArea
     */
    public void setDescArea(java.lang.String descArea) {
        this.descArea = descArea;
    }


    /**
     * Gets the descClasePago value for this DatoPer.
     * 
     * @return descClasePago
     */
    public java.lang.String getDescClasePago() {
        return descClasePago;
    }


    /**
     * Sets the descClasePago value for this DatoPer.
     * 
     * @param descClasePago
     */
    public void setDescClasePago(java.lang.String descClasePago) {
        this.descClasePago = descClasePago;
    }


    /**
     * Gets the descCsilac value for this DatoPer.
     * 
     * @return descCsilac
     */
    public java.lang.String getDescCsilac() {
        return descCsilac;
    }


    /**
     * Sets the descCsilac value for this DatoPer.
     * 
     * @param descCsilac
     */
    public void setDescCsilac(java.lang.String descCsilac) {
        this.descCsilac = descCsilac;
    }


    /**
     * Gets the descDepartamento value for this DatoPer.
     * 
     * @return descDepartamento
     */
    public java.lang.String getDescDepartamento() {
        return descDepartamento;
    }


    /**
     * Sets the descDepartamento value for this DatoPer.
     * 
     * @param descDepartamento
     */
    public void setDescDepartamento(java.lang.String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }


    /**
     * Gets the descDivision value for this DatoPer.
     * 
     * @return descDivision
     */
    public java.lang.String getDescDivision() {
        return descDivision;
    }


    /**
     * Sets the descDivision value for this DatoPer.
     * 
     * @param descDivision
     */
    public void setDescDivision(java.lang.String descDivision) {
        this.descDivision = descDivision;
    }


    /**
     * Gets the descEdificio value for this DatoPer.
     * 
     * @return descEdificio
     */
    public java.lang.String getDescEdificio() {
        return descEdificio;
    }


    /**
     * Sets the descEdificio value for this DatoPer.
     * 
     * @param descEdificio
     */
    public void setDescEdificio(java.lang.String descEdificio) {
        this.descEdificio = descEdificio;
    }


    /**
     * Gets the descJornada value for this DatoPer.
     * 
     * @return descJornada
     */
    public java.lang.String getDescJornada() {
        return descJornada;
    }


    /**
     * Sets the descJornada value for this DatoPer.
     * 
     * @param descJornada
     */
    public void setDescJornada(java.lang.String descJornada) {
        this.descJornada = descJornada;
    }


    /**
     * Gets the descLocalidad value for this DatoPer.
     * 
     * @return descLocalidad
     */
    public java.lang.String getDescLocalidad() {
        return descLocalidad;
    }


    /**
     * Sets the descLocalidad value for this DatoPer.
     * 
     * @param descLocalidad
     */
    public void setDescLocalidad(java.lang.String descLocalidad) {
        this.descLocalidad = descLocalidad;
    }


    /**
     * Gets the descProfesion value for this DatoPer.
     * 
     * @return descProfesion
     */
    public java.lang.String getDescProfesion() {
        return descProfesion;
    }


    /**
     * Sets the descProfesion value for this DatoPer.
     * 
     * @param descProfesion
     */
    public void setDescProfesion(java.lang.String descProfesion) {
        this.descProfesion = descProfesion;
    }


    /**
     * Gets the descRegimen value for this DatoPer.
     * 
     * @return descRegimen
     */
    public java.lang.String getDescRegimen() {
        return descRegimen;
    }


    /**
     * Sets the descRegimen value for this DatoPer.
     * 
     * @param descRegimen
     */
    public void setDescRegimen(java.lang.String descRegimen) {
        this.descRegimen = descRegimen;
    }


    /**
     * Gets the descUnidad value for this DatoPer.
     * 
     * @return descUnidad
     */
    public java.lang.String getDescUnidad() {
        return descUnidad;
    }


    /**
     * Sets the descUnidad value for this DatoPer.
     * 
     * @param descUnidad
     */
    public void setDescUnidad(java.lang.String descUnidad) {
        this.descUnidad = descUnidad;
    }


    /**
     * Gets the division value for this DatoPer.
     * 
     * @return division
     */
    public java.lang.String getDivision() {
        return division;
    }


    /**
     * Sets the division value for this DatoPer.
     * 
     * @param division
     */
    public void setDivision(java.lang.String division) {
        this.division = division;
    }


    /**
     * Gets the edificio value for this DatoPer.
     * 
     * @return edificio
     */
    public java.lang.String getEdificio() {
        return edificio;
    }


    /**
     * Sets the edificio value for this DatoPer.
     * 
     * @param edificio
     */
    public void setEdificio(java.lang.String edificio) {
        this.edificio = edificio;
    }


    /**
     * Gets the email value for this DatoPer.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this DatoPer.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the errorSql value for this DatoPer.
     * 
     * @return errorSql
     */
    public java.lang.String getErrorSql() {
        return errorSql;
    }


    /**
     * Sets the errorSql value for this DatoPer.
     * 
     * @param errorSql
     */
    public void setErrorSql(java.lang.String errorSql) {
        this.errorSql = errorSql;
    }


    /**
     * Gets the esFuncionario value for this DatoPer.
     * 
     * @return esFuncionario
     */
    public boolean isEsFuncionario() {
        return esFuncionario;
    }


    /**
     * Sets the esFuncionario value for this DatoPer.
     * 
     * @param esFuncionario
     */
    public void setEsFuncionario(boolean esFuncionario) {
        this.esFuncionario = esFuncionario;
    }


    /**
     * Gets the esRP value for this DatoPer.
     * 
     * @return esRP
     */
    public boolean isEsRP() {
        return esRP;
    }


    /**
     * Sets the esRP value for this DatoPer.
     * 
     * @param esRP
     */
    public void setEsRP(boolean esRP) {
        this.esRP = esRP;
    }


    /**
     * Gets the esRU value for this DatoPer.
     * 
     * @return esRU
     */
    public boolean isEsRU() {
        return esRU;
    }


    /**
     * Sets the esRU value for this DatoPer.
     * 
     * @param esRU
     */
    public void setEsRU(boolean esRU) {
        this.esRU = esRU;
    }


    /**
     * Gets the faxUnidad value for this DatoPer.
     * 
     * @return faxUnidad
     */
    public java.lang.String getFaxUnidad() {
        return faxUnidad;
    }


    /**
     * Sets the faxUnidad value for this DatoPer.
     * 
     * @param faxUnidad
     */
    public void setFaxUnidad(java.lang.String faxUnidad) {
        this.faxUnidad = faxUnidad;
    }


    /**
     * Gets the jornada value for this DatoPer.
     * 
     * @return jornada
     */
    public java.lang.String getJornada() {
        return jornada;
    }


    /**
     * Sets the jornada value for this DatoPer.
     * 
     * @param jornada
     */
    public void setJornada(java.lang.String jornada) {
        this.jornada = jornada;
    }


    /**
     * Gets the localidad value for this DatoPer.
     * 
     * @return localidad
     */
    public java.lang.String getLocalidad() {
        return localidad;
    }


    /**
     * Sets the localidad value for this DatoPer.
     * 
     * @param localidad
     */
    public void setLocalidad(java.lang.String localidad) {
        this.localidad = localidad;
    }


    /**
     * Gets the nivelJer value for this DatoPer.
     * 
     * @return nivelJer
     */
    public java.lang.String getNivelJer() {
        return nivelJer;
    }


    /**
     * Sets the nivelJer value for this DatoPer.
     * 
     * @param nivelJer
     */
    public void setNivelJer(java.lang.String nivelJer) {
        this.nivelJer = nivelJer;
    }


    /**
     * Gets the nombre value for this DatoPer.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DatoPer.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the nombreCompleto value for this DatoPer.
     * 
     * @return nombreCompleto
     */
    public java.lang.String getNombreCompleto() {
        return nombreCompleto;
    }


    /**
     * Sets the nombreCompleto value for this DatoPer.
     * 
     * @param nombreCompleto
     */
    public void setNombreCompleto(java.lang.String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    /**
     * Gets the oficina value for this DatoPer.
     * 
     * @return oficina
     */
    public java.lang.String getOficina() {
        return oficina;
    }


    /**
     * Sets the oficina value for this DatoPer.
     * 
     * @param oficina
     */
    public void setOficina(java.lang.String oficina) {
        this.oficina = oficina;
    }


    /**
     * Gets the planta value for this DatoPer.
     * 
     * @return planta
     */
    public java.lang.String getPlanta() {
        return planta;
    }


    /**
     * Sets the planta value for this DatoPer.
     * 
     * @param planta
     */
    public void setPlanta(java.lang.String planta) {
        this.planta = planta;
    }


    /**
     * Gets the priape value for this DatoPer.
     * 
     * @return priape
     */
    public java.lang.String getPriape() {
        return priape;
    }


    /**
     * Sets the priape value for this DatoPer.
     * 
     * @param priape
     */
    public void setPriape(java.lang.String priape) {
        this.priape = priape;
    }


    /**
     * Gets the profesion value for this DatoPer.
     * 
     * @return profesion
     */
    public java.lang.String getProfesion() {
        return profesion;
    }


    /**
     * Sets the profesion value for this DatoPer.
     * 
     * @param profesion
     */
    public void setProfesion(java.lang.String profesion) {
        this.profesion = profesion;
    }


    /**
     * Gets the programa value for this DatoPer.
     * 
     * @return programa
     */
    public java.lang.String getPrograma() {
        return programa;
    }


    /**
     * Sets the programa value for this DatoPer.
     * 
     * @param programa
     */
    public void setPrograma(java.lang.String programa) {
        this.programa = programa;
    }


    /**
     * Gets the regimen value for this DatoPer.
     * 
     * @return regimen
     */
    public java.lang.String getRegimen() {
        return regimen;
    }


    /**
     * Sets the regimen value for this DatoPer.
     * 
     * @param regimen
     */
    public void setRegimen(java.lang.String regimen) {
        this.regimen = regimen;
    }


    /**
     * Gets the segape value for this DatoPer.
     * 
     * @return segape
     */
    public java.lang.String getSegape() {
        return segape;
    }


    /**
     * Sets the segape value for this DatoPer.
     * 
     * @param segape
     */
    public void setSegape(java.lang.String segape) {
        this.segape = segape;
    }


    /**
     * Gets the sexo value for this DatoPer.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this DatoPer.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the telefonoUnidad value for this DatoPer.
     * 
     * @return telefonoUnidad
     */
    public java.lang.String getTelefonoUnidad() {
        return telefonoUnidad;
    }


    /**
     * Sets the telefonoUnidad value for this DatoPer.
     * 
     * @param telefonoUnidad
     */
    public void setTelefonoUnidad(java.lang.String telefonoUnidad) {
        this.telefonoUnidad = telefonoUnidad;
    }


    /**
     * Gets the topeUnidadesRP value for this DatoPer.
     * 
     * @return topeUnidadesRP
     */
    public int getTopeUnidadesRP() {
        return topeUnidadesRP;
    }


    /**
     * Sets the topeUnidadesRP value for this DatoPer.
     * 
     * @param topeUnidadesRP
     */
    public void setTopeUnidadesRP(int topeUnidadesRP) {
        this.topeUnidadesRP = topeUnidadesRP;
    }


    /**
     * Gets the topeUnidadesRU value for this DatoPer.
     * 
     * @return topeUnidadesRU
     */
    public int getTopeUnidadesRU() {
        return topeUnidadesRU;
    }


    /**
     * Sets the topeUnidadesRU value for this DatoPer.
     * 
     * @param topeUnidadesRU
     */
    public void setTopeUnidadesRU(int topeUnidadesRU) {
        this.topeUnidadesRU = topeUnidadesRU;
    }


    /**
     * Gets the unidad value for this DatoPer.
     * 
     * @return unidad
     */
    public java.lang.String getUnidad() {
        return unidad;
    }


    /**
     * Sets the unidad value for this DatoPer.
     * 
     * @param unidad
     */
    public void setUnidad(java.lang.String unidad) {
        this.unidad = unidad;
    }


    /**
     * Gets the unidadRP value for this DatoPer.
     * 
     * @return unidadRP
     */
    public java.lang.String[] getUnidadRP() {
        return unidadRP;
    }


    /**
     * Sets the unidadRP value for this DatoPer.
     * 
     * @param unidadRP
     */
    public void setUnidadRP(java.lang.String[] unidadRP) {
        this.unidadRP = unidadRP;
    }

    public java.lang.String getUnidadRP(int i) {
        return this.unidadRP[i];
    }

    public void setUnidadRP(int i, java.lang.String _value) {
        this.unidadRP[i] = _value;
    }


    /**
     * Gets the unidadRU value for this DatoPer.
     * 
     * @return unidadRU
     */
    public java.lang.String[] getUnidadRU() {
        return unidadRU;
    }


    /**
     * Sets the unidadRU value for this DatoPer.
     * 
     * @param unidadRU
     */
    public void setUnidadRU(java.lang.String[] unidadRU) {
        this.unidadRU = unidadRU;
    }

    public java.lang.String getUnidadRU(int i) {
        return this.unidadRU[i];
    }

    public void setUnidadRU(int i, java.lang.String _value) {
        this.unidadRU[i] = _value;
    }


    /**
     * Gets the unidadesRP value for this DatoPer.
     * 
     * @return unidadesRP
     */
    public java.util.Vector getUnidadesRP() {
        return unidadesRP;
    }


    /**
     * Sets the unidadesRP value for this DatoPer.
     * 
     * @param unidadesRP
     */
    public void setUnidadesRP(java.util.Vector unidadesRP) {
        this.unidadesRP = unidadesRP;
    }


    /**
     * Gets the unidadesRU value for this DatoPer.
     * 
     * @return unidadesRU
     */
    public java.util.Vector getUnidadesRU() {
        return unidadesRU;
    }


    /**
     * Sets the unidadesRU value for this DatoPer.
     * 
     * @param unidadesRU
     */
    public void setUnidadesRU(java.util.Vector unidadesRU) {
        this.unidadesRU = unidadesRU;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatoPer)) return false;
        DatoPer other = (DatoPer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.FIngre==null && other.getFIngre()==null) || 
             (this.FIngre!=null &&
              this.FIngre.equals(other.getFIngre()))) &&
            ((this.FNacim==null && other.getFNacim()==null) || 
             (this.FNacim!=null &&
              this.FNacim.equals(other.getFNacim()))) &&
            ((this.RUFunc==null && other.getRUFunc()==null) || 
             (this.RUFunc!=null &&
              this.RUFunc.equals(other.getRUFunc()))) &&
            ((this.TDirecPer==null && other.getTDirecPer()==null) || 
             (this.TDirecPer!=null &&
              this.TDirecPer.equals(other.getTDirecPer()))) &&
            ((this.TInterPer==null && other.getTInterPer()==null) || 
             (this.TInterPer!=null &&
              this.TInterPer.equals(other.getTInterPer()))) &&
            ((this.area==null && other.getArea()==null) || 
             (this.area!=null &&
              this.area.equals(other.getArea()))) &&
            ((this.cantPag==null && other.getCantPag()==null) || 
             (this.cantPag!=null &&
              this.cantPag.equals(other.getCantPag()))) &&
            ((this.cedula==null && other.getCedula()==null) || 
             (this.cedula!=null &&
              this.cedula.equals(other.getCedula()))) &&
            ((this.centroResp==null && other.getCentroResp()==null) || 
             (this.centroResp!=null &&
              this.centroResp.equals(other.getCentroResp()))) &&
            ((this.clasePago==null && other.getClasePago()==null) || 
             (this.clasePago!=null &&
              this.clasePago.equals(other.getClasePago()))) &&
            ((this.csilac==null && other.getCsilac()==null) || 
             (this.csilac!=null &&
              this.csilac.equals(other.getCsilac()))) &&
            ((this.departamento==null && other.getDepartamento()==null) || 
             (this.departamento!=null &&
              this.departamento.equals(other.getDepartamento()))) &&
            ((this.descArea==null && other.getDescArea()==null) || 
             (this.descArea!=null &&
              this.descArea.equals(other.getDescArea()))) &&
            ((this.descClasePago==null && other.getDescClasePago()==null) || 
             (this.descClasePago!=null &&
              this.descClasePago.equals(other.getDescClasePago()))) &&
            ((this.descCsilac==null && other.getDescCsilac()==null) || 
             (this.descCsilac!=null &&
              this.descCsilac.equals(other.getDescCsilac()))) &&
            ((this.descDepartamento==null && other.getDescDepartamento()==null) || 
             (this.descDepartamento!=null &&
              this.descDepartamento.equals(other.getDescDepartamento()))) &&
            ((this.descDivision==null && other.getDescDivision()==null) || 
             (this.descDivision!=null &&
              this.descDivision.equals(other.getDescDivision()))) &&
            ((this.descEdificio==null && other.getDescEdificio()==null) || 
             (this.descEdificio!=null &&
              this.descEdificio.equals(other.getDescEdificio()))) &&
            ((this.descJornada==null && other.getDescJornada()==null) || 
             (this.descJornada!=null &&
              this.descJornada.equals(other.getDescJornada()))) &&
            ((this.descLocalidad==null && other.getDescLocalidad()==null) || 
             (this.descLocalidad!=null &&
              this.descLocalidad.equals(other.getDescLocalidad()))) &&
            ((this.descProfesion==null && other.getDescProfesion()==null) || 
             (this.descProfesion!=null &&
              this.descProfesion.equals(other.getDescProfesion()))) &&
            ((this.descRegimen==null && other.getDescRegimen()==null) || 
             (this.descRegimen!=null &&
              this.descRegimen.equals(other.getDescRegimen()))) &&
            ((this.descUnidad==null && other.getDescUnidad()==null) || 
             (this.descUnidad!=null &&
              this.descUnidad.equals(other.getDescUnidad()))) &&
            ((this.division==null && other.getDivision()==null) || 
             (this.division!=null &&
              this.division.equals(other.getDivision()))) &&
            ((this.edificio==null && other.getEdificio()==null) || 
             (this.edificio!=null &&
              this.edificio.equals(other.getEdificio()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.errorSql==null && other.getErrorSql()==null) || 
             (this.errorSql!=null &&
              this.errorSql.equals(other.getErrorSql()))) &&
            this.esFuncionario == other.isEsFuncionario() &&
            this.esRP == other.isEsRP() &&
            this.esRU == other.isEsRU() &&
            ((this.faxUnidad==null && other.getFaxUnidad()==null) || 
             (this.faxUnidad!=null &&
              this.faxUnidad.equals(other.getFaxUnidad()))) &&
            ((this.jornada==null && other.getJornada()==null) || 
             (this.jornada!=null &&
              this.jornada.equals(other.getJornada()))) &&
            ((this.localidad==null && other.getLocalidad()==null) || 
             (this.localidad!=null &&
              this.localidad.equals(other.getLocalidad()))) &&
            ((this.nivelJer==null && other.getNivelJer()==null) || 
             (this.nivelJer!=null &&
              this.nivelJer.equals(other.getNivelJer()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.nombreCompleto==null && other.getNombreCompleto()==null) || 
             (this.nombreCompleto!=null &&
              this.nombreCompleto.equals(other.getNombreCompleto()))) &&
            ((this.oficina==null && other.getOficina()==null) || 
             (this.oficina!=null &&
              this.oficina.equals(other.getOficina()))) &&
            ((this.planta==null && other.getPlanta()==null) || 
             (this.planta!=null &&
              this.planta.equals(other.getPlanta()))) &&
            ((this.priape==null && other.getPriape()==null) || 
             (this.priape!=null &&
              this.priape.equals(other.getPriape()))) &&
            ((this.profesion==null && other.getProfesion()==null) || 
             (this.profesion!=null &&
              this.profesion.equals(other.getProfesion()))) &&
            ((this.programa==null && other.getPrograma()==null) || 
             (this.programa!=null &&
              this.programa.equals(other.getPrograma()))) &&
            ((this.regimen==null && other.getRegimen()==null) || 
             (this.regimen!=null &&
              this.regimen.equals(other.getRegimen()))) &&
            ((this.segape==null && other.getSegape()==null) || 
             (this.segape!=null &&
              this.segape.equals(other.getSegape()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.telefonoUnidad==null && other.getTelefonoUnidad()==null) || 
             (this.telefonoUnidad!=null &&
              this.telefonoUnidad.equals(other.getTelefonoUnidad()))) &&
            this.topeUnidadesRP == other.getTopeUnidadesRP() &&
            this.topeUnidadesRU == other.getTopeUnidadesRU() &&
            ((this.unidad==null && other.getUnidad()==null) || 
             (this.unidad!=null &&
              this.unidad.equals(other.getUnidad()))) &&
            ((this.unidadRP==null && other.getUnidadRP()==null) || 
             (this.unidadRP!=null &&
              java.util.Arrays.equals(this.unidadRP, other.getUnidadRP()))) &&
            ((this.unidadRU==null && other.getUnidadRU()==null) || 
             (this.unidadRU!=null &&
              java.util.Arrays.equals(this.unidadRU, other.getUnidadRU()))) &&
            ((this.unidadesRP==null && other.getUnidadesRP()==null) || 
             (this.unidadesRP!=null &&
              this.unidadesRP.equals(other.getUnidadesRP()))) &&
            ((this.unidadesRU==null && other.getUnidadesRU()==null) || 
             (this.unidadesRU!=null &&
              this.unidadesRU.equals(other.getUnidadesRU())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFIngre() != null) {
            _hashCode += getFIngre().hashCode();
        }
        if (getFNacim() != null) {
            _hashCode += getFNacim().hashCode();
        }
        if (getRUFunc() != null) {
            _hashCode += getRUFunc().hashCode();
        }
        if (getTDirecPer() != null) {
            _hashCode += getTDirecPer().hashCode();
        }
        if (getTInterPer() != null) {
            _hashCode += getTInterPer().hashCode();
        }
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        if (getCantPag() != null) {
            _hashCode += getCantPag().hashCode();
        }
        if (getCedula() != null) {
            _hashCode += getCedula().hashCode();
        }
        if (getCentroResp() != null) {
            _hashCode += getCentroResp().hashCode();
        }
        if (getClasePago() != null) {
            _hashCode += getClasePago().hashCode();
        }
        if (getCsilac() != null) {
            _hashCode += getCsilac().hashCode();
        }
        if (getDepartamento() != null) {
            _hashCode += getDepartamento().hashCode();
        }
        if (getDescArea() != null) {
            _hashCode += getDescArea().hashCode();
        }
        if (getDescClasePago() != null) {
            _hashCode += getDescClasePago().hashCode();
        }
        if (getDescCsilac() != null) {
            _hashCode += getDescCsilac().hashCode();
        }
        if (getDescDepartamento() != null) {
            _hashCode += getDescDepartamento().hashCode();
        }
        if (getDescDivision() != null) {
            _hashCode += getDescDivision().hashCode();
        }
        if (getDescEdificio() != null) {
            _hashCode += getDescEdificio().hashCode();
        }
        if (getDescJornada() != null) {
            _hashCode += getDescJornada().hashCode();
        }
        if (getDescLocalidad() != null) {
            _hashCode += getDescLocalidad().hashCode();
        }
        if (getDescProfesion() != null) {
            _hashCode += getDescProfesion().hashCode();
        }
        if (getDescRegimen() != null) {
            _hashCode += getDescRegimen().hashCode();
        }
        if (getDescUnidad() != null) {
            _hashCode += getDescUnidad().hashCode();
        }
        if (getDivision() != null) {
            _hashCode += getDivision().hashCode();
        }
        if (getEdificio() != null) {
            _hashCode += getEdificio().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getErrorSql() != null) {
            _hashCode += getErrorSql().hashCode();
        }
        _hashCode += (isEsFuncionario() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isEsRP() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isEsRU() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFaxUnidad() != null) {
            _hashCode += getFaxUnidad().hashCode();
        }
        if (getJornada() != null) {
            _hashCode += getJornada().hashCode();
        }
        if (getLocalidad() != null) {
            _hashCode += getLocalidad().hashCode();
        }
        if (getNivelJer() != null) {
            _hashCode += getNivelJer().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNombreCompleto() != null) {
            _hashCode += getNombreCompleto().hashCode();
        }
        if (getOficina() != null) {
            _hashCode += getOficina().hashCode();
        }
        if (getPlanta() != null) {
            _hashCode += getPlanta().hashCode();
        }
        if (getPriape() != null) {
            _hashCode += getPriape().hashCode();
        }
        if (getProfesion() != null) {
            _hashCode += getProfesion().hashCode();
        }
        if (getPrograma() != null) {
            _hashCode += getPrograma().hashCode();
        }
        if (getRegimen() != null) {
            _hashCode += getRegimen().hashCode();
        }
        if (getSegape() != null) {
            _hashCode += getSegape().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getTelefonoUnidad() != null) {
            _hashCode += getTelefonoUnidad().hashCode();
        }
        _hashCode += getTopeUnidadesRP();
        _hashCode += getTopeUnidadesRU();
        if (getUnidad() != null) {
            _hashCode += getUnidad().hashCode();
        }
        if (getUnidadRP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnidadRP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnidadRP(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUnidadRU() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnidadRU());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnidadRU(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUnidadesRP() != null) {
            _hashCode += getUnidadesRP().hashCode();
        }
        if (getUnidadesRU() != null) {
            _hashCode += getUnidadesRU().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatoPer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:com.antel.sgp.WebServices", "DatoPer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FIngre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FIngre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FNacim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FNacim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUFunc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUFunc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Vector"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TDirecPer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TDirecPer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TInterPer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TInterPer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("", "area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantPag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centroResp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centroResp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clasePago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clasePago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("csilac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "csilac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "departamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descArea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descClasePago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descClasePago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descCsilac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descCsilac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descDepartamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descDepartamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descDivision");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descDivision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descEdificio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descEdificio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descJornada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descJornada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descLocalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descLocalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descProfesion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descProfesion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descRegimen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descRegimen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descUnidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descUnidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("division");
        elemField.setXmlName(new javax.xml.namespace.QName("", "division"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edificio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "edificio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorSql");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorSql"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esFuncionario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esFuncionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esRP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esRP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esRU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esRU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxUnidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faxUnidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jornada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jornada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelJer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nivelJer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCompleto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreCompleto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oficina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oficina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("planta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "planta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priape");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priape"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profesion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profesion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "programa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regimen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regimen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segape");
        elemField.setXmlName(new javax.xml.namespace.QName("", "segape"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoUnidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoUnidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topeUnidadesRP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "topeUnidadesRP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topeUnidadesRU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "topeUnidadesRU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadRP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadRP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadRU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadRU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadesRP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadesRP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Vector"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadesRU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadesRU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Vector"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
