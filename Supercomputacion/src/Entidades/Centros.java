package Entidades;

/**
 * Define la clase <strong>Centros</strong>
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Centros {
    
    private int idCentro;
    private String sIdentificadorCentro;
    private int capacidadProcesamiento;
    private int tamanoMaxCola;
    private String sAdministrador;
    
    
    /**
     * Método constructor de la clase <strong>Centros</strong>.
     */
    public Centros() {        
    }
    
    
    /**
     * Método constructor de la clase <strong>Centros</strong>.
     * @param idCentro int que representa el identificador único y autonumérico del centro
     * @param sIdentificadorCentro String con el identificador alfanumérico del centro. Tiene un máximo de 30 caracteres
     * @param capacidadProcesamiento int que representa la capacidad de procesamiento del centro
     * @param tamanoMaxCola int que representa el tamaño máximo de la cola del centro
     * @param sAdministrador String con el identificador del Administrador del centro
     */
    public Centros(int idCentro, String sIdentificadorCentro, int capacidadProcesamiento, int tamanoMaxCola, String sAdministrador){
        this.idCentro = idCentro;
        this.sIdentificadorCentro = sIdentificadorCentro;
        this.capacidadProcesamiento = capacidadProcesamiento;
        this.tamanoMaxCola = tamanoMaxCola;
        this.sAdministrador = sAdministrador;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>idCentro</strong>.
     * @return int idCentro
     */
    public int getIdCentro() {
        return idCentro;
    }
    
    
    /**
     * Método setter utilizado para establecer <strong>idCentro</strong> de un centro
     * @param idCentro Indica el <strong>idCentro</strong> que debe tener el centro
     */
    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>sIdentificadorCentro</strong>.
     * @return String sIdentificadorCentro
     */
    public String getsIdentificadorCentro() {
        return sIdentificadorCentro;
    }
    
    
    /**
     * Método setter utilizado para establecer <strong>sIdentificadorCentro</strong> de un centro
     * @param sIdentificadorCentro Indica el <strong>sIdentificadorCentro</strong> que debe tener el centro
     */
    public void setsIdentificadorCentro(String sIdentificadorCentro) {
        this.sIdentificadorCentro = sIdentificadorCentro;
    }
    
    
    /**
     * Método getter utilizado para obtener la <strong>capacidadProcesamiento</strong>.
     * @return int capacidadProcesamiento
     */
    public int getCapacidadProcesamiento() {
        return capacidadProcesamiento;
    }
    
    
    /**
     * Método setter utilizado para establecer <strong>capacidadProcesamiento</strong> de un centro
     * @param capacidadProcesamiento Indica el <strong>capacidadProcesamiento</strong> que debe tener el centro
     */
    public void setCapacidadProcesamiento(int capacidadProcesamiento) {
        this.capacidadProcesamiento = capacidadProcesamiento;
    }
    
    
    /**
     * Método getter utilizado para obtener la <strong>tamanoMaxCola</strong>.
     * @return int tamanoMaxCola
     */
    public int getTamanoMaxCola() {
        return tamanoMaxCola;
    }
    
    
    /**
     * Método setter utilizado para establecer <strong>tamanoMaxCola</strong> de un centro
     * @param tamanoMaxCola Indica el <strong>tamanoMaxCola</strong> que debe tener el centro
     */
    public void setTamanoMaxCola(int tamanoMaxCola) {
        this.tamanoMaxCola = tamanoMaxCola;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>sAdministrador</strong>.
     * @return String sAdministrador
     */
    public String getsAdministrador() {
        return sAdministrador;
    }
    
    
    /**
     * Método setter utilizado para establecer el <strong>sAdministrador</strong> de un centro
     * @param sAdministrador Indica el <strong>sAdministrador</strong> que debe tener el centro
     */
    public void setsAdministrador(String sAdministrador) {
        this.sAdministrador = sAdministrador;
    }
        
}
