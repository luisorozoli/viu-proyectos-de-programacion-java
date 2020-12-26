package Entidades;

/**
 * Define la clase <strong>Usuarios</strong>.
 * 
 * @author Sebastian Plaza, Gonzalo Diaz, Luis Orozco.
 */
public class Usuarios {
    
    private int userId;
    private String identificador;
    private String clave;
    private String tipo;
    
    
    /**
     * Método constructor de la clase <strong>Usuarios</strong>.
     */
    public Usuarios(){
        
    }
    
    
    /**
     * Método constructor de la clase <strong>Usuarios</strong>.
     * 
     * @param id int que representa el identificador único y autonumérico del usuario
     * @param identif String con el identificador alfanumérico del usuario. Tiene un máximo de 10 caracteres
     * @param pass String con la contraseña del usuario
     * @param tipo_usuario String que indica si el usuario es <i>Administrador</i>, <i>AdministradorCentro</i> o <i>Usuario</i>
     */
    public Usuarios(int id, String identif, String pass, String tipo_usuario){
        userId = id;
        identificador = identif;
        clave = pass;
        tipo = tipo_usuario;        
    }
    
    
    /**
     * Método setter utilizado para establecer el <strong>id</strong> de un
     * usuario
     * @param id Indica el <strong>id</strong> que debe tener el usuario
     */
    public void setUserId(int id){
        this.userId = id;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>userId</strong>.
     * @return int userId
     */
    public int getUserId(){
        return userId;
    }
    
    
    /**
     * Método setter utilizado para establecer el <strong>identificador</strong>
     * de un usuario
     * @param identif String que representa al <strong>identificador</strong>
     * del usuario
     */
    public void setIdentificafor(String identif){
        this.identificador = identif;
    }
    
    
    /**
     * Método getter utilizado para obtener el <strong>identificador</strong>
     * del usuario
     * @return String identificador
     */
    public String getIdentificador(){
        return identificador;
    }
    
    
    /**
     * Método setter utilizado para establecer la <strong>clave</strong> del usuario
     * @param pass String con la <strong>clave</strong> del usuario
     */
    public void setClave(String pass){
        this.clave = pass;
    }
    
    
    /**
     * Método getter utilizado para obtener la <strong>clave</strong> del usuario
     * @return String clave
     */
    public String getClave(){
        return clave;
    }
    
    
    /**
     * Método setter utilizado para establecer el tipo de usuario
     * @param tipo String con el tipo de usuario  (<i>Administrador</i>, <i>AdministradorCentro</i>, <i>Usuario</i>)
     */
    public void setTipoUsuario(String tipo){
        this.tipo = tipo;
    }
    
    
    /**
     * Método getter utilizado para obtener el tipo de usuario
     * @return String tipo
     */
    public String getTipoUsuario(){
        return tipo;
    }

}
