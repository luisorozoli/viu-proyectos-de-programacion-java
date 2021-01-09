package Entidades;

/**
 * Define la clase <strong>Trabajos</strong>
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Trabajos {
    
    private int iIdTrabajo;
    private String sIdentificadorTrab;
    private String sCantidadOperaciones;
    private String sPropietario;
    private String sIdTrabajo;
    private String sCentroTrabajo;

//    public String getsIdTrabajo() {
//        return sIdTrabajo;
//    }
    
    /**
     * Método constructor de la clase <strong>Trabajos</strong>.
     */
    public Trabajos() {
        
    }
    
    /**
     * Método constructor de la clase <strong>Trabajos</strong>.
     * @param iIdTrabajo int que representa el identificador único y autonúmerico del trabajo
     * @param sIdentificadorTrab String con el identificador alfanumérico del trabajo. Tiene un máximo de 30 caracteres
     * @param sCantidadOperaciones int que representa la cantidad de operaciones del trabajo
     * @param sPropietario  String que representa el identificador del propietario del trabajo
     */
    public Trabajos(int iIdTrabajo, String sIdentificadorTrab, String sCantidadOperaciones, String sPropietario){
        this.iIdTrabajo = iIdTrabajo;       
        this.sIdentificadorTrab = sIdentificadorTrab;
        this.sCantidadOperaciones = sCantidadOperaciones;
        this.sPropietario = sPropietario;
    }

    /**
     * Método getter utilizado para obtener el <strong>iIdTrabajo</strong>.
     * @return int iIdTrabajo
     */
    public int getiIdTrabajo() {
        return iIdTrabajo;
    }

    /**
     * Método setter utilizado para establecer el <strong>iIdTrabajo</strong>.
     * @param iIdTrabajo indica el <strong>idTrabajo</strong> que debe tener el trabajo
     */
    public void setiIdTrabajo(int iIdTrabajo) {
        this.iIdTrabajo = iIdTrabajo;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>sIdentificadorTrab</strong>.
     * @return sIdentificadorTrab
     */
    public String getsIdentificadorTrab() {
        return sIdentificadorTrab;
    }

    /** Método setter utilizado para establecer el <strong>sIdentificadorTrab</strong>.
     * @param sIdentificadorTrab indica el <strong>sIdentificadorTrab</strong> que debe tener el trabajo
     */
    public void setsIdentificadorTrab(String sIdentificadorTrab) {
        this.sIdentificadorTrab = sIdentificadorTrab;
    }

    /** Método getter utilizado para obtener el <strong>sCantidadOperaciones</strong>.
     * @return sCantidadOperaciones
     */
    public String getsCantidadOperaciones() {
        return sCantidadOperaciones;
    }

    /** Método setter utilizado para establecer el <strong>sCantidadOperaciones</strong>.
     * @param sCantidadOperaciones indica el <strong>sCantidadOperaciones</strong> que debe tener el trabajo
     */
    public void setsCantidadOperaciones(String sCantidadOperaciones) {
        this.sCantidadOperaciones = sCantidadOperaciones;
    }

    /** Método getter utilizado para obtener el <strong>sPropietario</strong>.
     * @return sPropietario
     */
    public String getsPropietario() {
        return sPropietario;
    }

    /** Método setter utilizado para establecer el <strong>sPropietario</strong>.
     * @param sPropietario indica el <strong>sPropietaro</strong> que debe tener el trabajo
     */
    public void setsPropietario(String sPropietario) {
        this.sPropietario = sPropietario;
    }
    
    /**
     * Método getter utilizado para obtener el <strong>sCentroTrabajo</strong>
     * @return sCentroTrabajo;
     */
    public String getsCentroTrabajo() {
        return sCentroTrabajo;
    }

    /**
     * Método setter utilizado para establecer el <strong>sCentroTrabajo</strong>.
     * @param sCentroTrabajo indica el <strong>sCentroTrabajo</strong> que debe tener el trabajo
     */
    public void setsCentroTrabajo(String sCentroTrabajo) {
        this.sCentroTrabajo = sCentroTrabajo;
    }
        
}
