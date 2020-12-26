package Entidades;

/**
 * Define la clase Centros
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Centros {
    
    private String sIdentificadorCentro;
    private String sCapacidadProcesamiento;
    private String sTamanoMaxCola;
    private String sAdministrador;
    private String sIdCentro;

    public String getsIdCentro() {
        return sIdCentro;
    }
    
    public Centros() {
        
    }
    public Centros(String sIdentificadorCentro, String sCantidadOperaciones, String sPropietario){
               this.sIdentificadorCentro = sIdentificadorCentro;
               this.sCapacidadProcesamiento = sCapacidadProcesamiento;
               this.sTamanoMaxCola = sTamanoMaxCola;
               this.sAdministrador = sAdministrador;
    }

    /**
     * @return the sIdentificadorCentro
     */
    public String getsIdentificadorCentro() {
        return sIdentificadorCentro;
    }

    /**
     * @param sIdentificadorCentro the sIdentificadorCentro to set
     */
    public void setsIdentificadorCentro(String sIdentificadorCentro) {
        this.sIdentificadorCentro = sIdentificadorCentro;
    }

    /**
     * @return the sCapacidadProcesamiento
     */
    public String getsCapacidadProcesamiento() {
        return sCapacidadProcesamiento;
    }

    /**
     * @param sCapacidadProcesamiento the sCapacidadProcesamiento to set
     */
    public void setsCapacidadProcesamiento(String sCapacidadProcesamiento) {
        this.sCapacidadProcesamiento = sCapacidadProcesamiento;
    }

    /**
     * @return the sTamanoMaxCola
     */
    public String getsTamanoMaxCola() {
        return sTamanoMaxCola;
    }

    /**
     * @param sTamanoMaxCola the sTamanoMaxCola to set
     */
    public void setsTamanoMaxCola(String sTamanoMaxCola) {
        this.sTamanoMaxCola = sTamanoMaxCola;
    }
    
    /**
     * @return the sAdministrador
     */
    public String getsAdministrador() {
        return sAdministrador;
    }

    /**
     * @param sAdministrador the sAdministrador to set
     */
    public void setsAdministrador(String sAdministrador) {
        this.sAdministrador = sAdministrador;
    }
        
}
