package modelo;

public class Bus {
    private String codigo;
    private String placa;
    private int capacidad;
    private EstadoBus estado;

    public Bus(String codigo, String placa, int capacidad) {
        this.codigo = codigo;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = EstadoBus.ACTIVO;
    }

    public boolean estaActivo() { return this.estado == EstadoBus.ACTIVO; }
    public String getCodigo() { return codigo; }
    public String getPlaca() { return placa; }
    public int getCapacidad() { return capacidad; }
    public EstadoBus getEstado() { return estado; }
    public void setEstado(EstadoBus estado) { this.estado = estado; }
}