package modelo;

public class Parada {
    private String id;
    private String nombre;
    private String ubicacionReferencia;

    public Parada(String id, String nombre, String ubicacionReferencia) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacionReferencia = ubicacionReferencia;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
}