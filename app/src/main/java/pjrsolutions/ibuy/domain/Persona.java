package pjrsolutions.ibuy.domain;

public class Persona {
    private String cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String clave;

    public Persona() {
        super();
        this.setCedula(null);
        this.setNombre(null);
        this.setApellidos(null);
        this.setCorreo(null);
        this.setClave(null);
    }
    public Persona(String cedula,String nombre, String apellidos, String correo,String clave){
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setCorreo(correo);
        this.setClave(clave);
    }

    public void setCedula(String cedula){
        this.cedula= cedula;
    }
    public String getCedula(){
        return this.cedula;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    public String getApellidos(){
        return this.apellidos;
    }

    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getCorreo() {
        return this.correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
