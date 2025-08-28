import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControleTrocaOleo {
    private final Carro carro;
    private LocalDate dataUltimaTroca;
    private int kmUltimaTroca;

    private final boolean controlarPorKm;
    private final boolean controlarPorTempo;
    private final int intervaloKm;
    private final int intervaloMeses;

    public ControleTrocaOleo(Carro carro, LocalDate dataUltimaTroca, int kmUltimaTroca,
                             boolean controlarPorKm, boolean controlarPorTempo,
                             int intervaloKm, int intervaloMeses) {
        this.carro = carro;
        this.dataUltimaTroca = dataUltimaTroca;
        this.kmUltimaTroca = kmUltimaTroca;
        this.controlarPorKm = controlarPorKm;
        this.controlarPorTempo = controlarPorTempo;
        this.intervaloKm = intervaloKm;
        this.intervaloMeses = intervaloMeses;
    }

    public Carro getCarro() { return carro; }
    public boolean isControlarPorKm() { return controlarPorKm; }
    public boolean isControlarPorTempo() { return controlarPorTempo; }

    private int kmExcedente() {
        if (!controlarPorKm) return 0;
        int limiteKm = kmUltimaTroca + intervaloKm;
        return carro.getQuilometragem() - limiteKm;
    }

    private long mesesExcedentes() {
        if (!controlarPorTempo) return 0;
        long decorrido = ChronoUnit.MONTHS.between(dataUltimaTroca, LocalDate.now());
        return decorrido - intervaloMeses;
    }

    public boolean precisaTrocarOleo() {
        boolean passouKm = controlarPorKm && kmExcedente() > 0;
        boolean passouMeses = controlarPorTempo && mesesExcedentes() > 0;
        return passouKm || passouMeses;
    }

    public void exibirStatus() {
        System.out.println("\n--- Status da Troca de Óleo ---");

        boolean precisa = precisaTrocarOleo();

        if (precisa) {
            System.out.println("⚠️  Troca de óleo necessária!");

            if (controlarPorKm) {
                int excedente = kmExcedente();
                if (excedente > 0) {
                    System.out.println("\"Você ultrapassou " + excedente + " km do limite de troca.\"");
                } else if (excedente == 0) {
                    System.out.println("\"Você atingiu exatamente o limite de km para a troca.\"");
                }
            }

            if (controlarPorTempo) {
                long excMeses = mesesExcedentes();
                if (excMeses > 0) {
                    System.out.println("\"Você ultrapassou " + excMeses + " meses do prazo de troca.\"");
                } else if (excMeses == 0) {
                    System.out.println("\"Você atingiu exatamente o limite de meses para a troca.\"");
                }
            }
        } else {
            System.out.println("✅ Óleo em dia.");

            if (controlarPorKm) {
                int faltamKm = -kmExcedente();
                System.out.println("Faltam " + faltamKm + " km para a próxima troca por km.");
            }

            if (controlarPorTempo) {
                long faltamMeses = -mesesExcedentes();
                System.out.println("Faltam " + faltamMeses + " meses para a próxima troca por tempo.");
            }
        }
    }

    public void exibirDetalhes() {
        System.out.println("\n--- Informações da Troca de Óleo ---");
        System.out.println("Data da última troca: " + dataUltimaTroca);
        System.out.println("KM da última troca: " + kmUltimaTroca);
        System.out.println("Controle por KM: " + (controlarPorKm ? "Sim (" + intervaloKm + " km)" : "Não"));
        System.out.println("Controle por Tempo: " + (controlarPorTempo ? "Sim (" + intervaloMeses + " meses)" : "Não"));
    }

    public void exibirProximaTroca() {
        System.out.println("\n--- Previsão da Próxima Troca ---");

        if (controlarPorKm && kmExcedente() < 0) {
            System.out.println("Próxima troca por KM: " + (kmUltimaTroca + intervaloKm) + " km");
        }

        if (controlarPorTempo && mesesExcedentes() < 0) {
            LocalDate proximaData = dataUltimaTroca.plusMonths(intervaloMeses);
            System.out.println("Próxima troca por Data: " + proximaData);
        }
    }

    public void registrarNovaTroca() {
        dataUltimaTroca = LocalDate.now();
        kmUltimaTroca = carro.getQuilometragem();
        System.out.println("🔧 Nova troca registrada com sucesso!");
    }
}
