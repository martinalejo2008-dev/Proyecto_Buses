package modelo;

public class ParadaRuta {
    private int orden;
    private int minutosDesdeAnterior;
    private Parada parada;

    public ParadaRuta(int orden, int minutosDesdeAnterior, Parada parada) {
        this.orden = orden;
        this.minutosDesdeAnterior = minutosDesdeAnterior;
        this.parada = parada;
    }

    public int getOrden() { return orden; }
    public int getMinutosDesdeAnterior() { return minutosDesdeAnterior; }
    public Parada getParada() { return parada; }
}