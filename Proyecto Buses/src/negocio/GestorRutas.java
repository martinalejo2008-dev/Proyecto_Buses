package negocio;
import modelo.*;
import java.util.ArrayList;
import java.util.List;

public class GestorRutas {
    private List<Ruta> rutas = new ArrayList<>();

    public void crearRuta(Ruta nuevaRuta) {
        if (nuevasRutasContienenErrores(nuevaRuta)) {
            throw new ReglaNegocioException("La ruta debe tener al menos dos paradas y no repetir paradas consecutivas.");
        }
        for (Ruta r : rutas) {
            if (r.getCodigo().equals(nuevaRuta.getCodigo())) {
                throw new ReglaNegocioException("El código de ruta ya existe.");
            }
        }
        rutas.add(nuevaRuta);
    }

    private boolean nuevasRutasContienenErrores(Ruta r) {
        List<ParadaRuta> ps = r.getParadas();
        if (ps == null || ps.size() < 2) return true;
        for (int i = 0; i < ps.size() - 1; i++) {
            if (ps.get(i).getParada().getId().equals(ps.get(i + 1).getParada().getId())) {
                return true;
            }
        }
        return false;
    }

    public Ruta buscarRuta(String codigo) {
        return rutas.stream().filter(r -> r.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    public List<Ruta> listarRutas() { return rutas; }
}