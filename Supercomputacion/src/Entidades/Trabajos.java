package Entidades;

/**
 * Define la clase Trabajos
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Trabajos {
    
    private int iIdTrabajo;
    private String sIdentificadorTrab;
    private String sCantidadOperaciones;
    private String sPropietario;
    private String sIdTrabajo;

    public String getsIdTrabajo() {
        return sIdTrabajo;
    }
    
    public Trabajos() {
        
    }
    public Trabajos(int iIdTrabajo, String sIdentificadorTrab, String sCantidadOperaciones, String sPropietario){
        this.iIdTrabajo = iIdTrabajo;       
        this.sIdentificadorTrab = sIdentificadorTrab;
        this.sCantidadOperaciones = sCantidadOperaciones;
        this.sPropietario = sPropietario;
    }

    public int getiIdTrabajo() {
        return iIdTrabajo;
    }

    public void setiIdTrabajo(int iIdTrabajo) {
        this.iIdTrabajo = iIdTrabajo;
    }
    /**
     * @return the sIdentificadorTrab
     */
    public String getsIdentificadorTrab() {
        return sIdentificadorTrab;
    }

    /**
     * @param sIdentificadorTrab the sIdentificadorTrab to set
     */
    public void setsIdentificadorTrab(String sIdentificadorTrab) {
        this.sIdentificadorTrab = sIdentificadorTrab;
    }

    /**
     * @return the sCantidadOperaciones
     */
    public String getsCantidadOperaciones() {
        return sCantidadOperaciones;
    }

    /**
     * @param sCantidadOperaciones the sCantidadOperaciones to set
     */
    public void setsCantidadOperaciones(String sCantidadOperaciones) {
        this.sCantidadOperaciones = sCantidadOperaciones;
    }

    /**
     * @return the sPropietario
     */
    public String getsPropietario() {
        return sPropietario;
    }

    /**
     * @param sPropietario the sPropietario to set
     */
    public void setsPropietario(String sPropietario) {
        this.sPropietario = sPropietario;
    }
        
}
