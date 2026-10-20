package negocio;

public class ReglaNegocioException extends RuntimeException {
    private String mensaje;
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
    public String getMensaje() { return mensaje; }
}