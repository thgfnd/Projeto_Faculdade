import java.util.ArrayList;
import java.util.List;

public class GerenciardorVeiculos {
    private final List<ControleTrocaOleo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(ControleTrocaOleo controle) {
        veiculos.add(controle);
    }

    public List<ControleTrocaOleo> getVeiculos() {
        return veiculos;
    }

    public ControleTrocaOleo buscarPorModelo(String modelo) {
        for (ControleTrocaOleo c : veiculos) {
            if (c.getCarro().getModelo().equalsIgnoreCase(modelo)) {
                return c;
            }
        }
        return null;
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo registrado.");
            return;
        }
        System.out.println("\n=== Veículos Registrados ===");
        for (ControleTrocaOleo c : veiculos) {
            System.out.println("- " + c.getCarro().getModelo() + " (" + c.getCarro().getAno() + ")");
        }
    }
}
