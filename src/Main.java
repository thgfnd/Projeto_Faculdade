import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciardorVeiculos gerenciador = new GerenciardorVeiculos();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Registrar novo veículo");
            System.out.println("2. Consultar status da troca de óleo");
            System.out.println("3. Registrar nova troca de óleo");
            System.out.println("4. Listar veículos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Opção inválida, digite um número.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1: // cadastrar
                    try {
                        System.out.print("Modelo do carro: ");
                        String modelo = scanner.nextLine();

                        System.out.print("Ano: ");
                        int ano = scanner.nextInt();

                        System.out.print("Quilometragem atual: ");
                        int kmAtual = scanner.nextInt();
                        scanner.nextLine();

                        Carro carro = new Carro(modelo, ano, kmAtual);

                        System.out.print("Intervalo de troca por KM (0 = não controlar): ");
                        int intervaloKm = scanner.nextInt();

                        System.out.print("Intervalo de troca por TEMPO em meses (0 = não controlar): ");
                        int intervaloMeses = scanner.nextInt();
                        scanner.nextLine();

                        boolean porKm = intervaloKm > 0;
                        boolean porTempo = intervaloMeses > 0;

                        System.out.print("Data da última troca (AAAA-MM-DD): ");
                        LocalDate dataUltimaTroca = LocalDate.parse(scanner.nextLine());

                        System.out.print("KM da última troca: ");
                        int kmUltimaTroca = scanner.nextInt();

                        ControleTrocaOleo controle = new ControleTrocaOleo(
                                carro, dataUltimaTroca, kmUltimaTroca,
                                porKm, porTempo, intervaloKm, intervaloMeses
                        );

                        gerenciador.adicionarVeiculo(controle);
                        System.out.println("✅ Veículo registrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao registrar veículo: " + e.getMessage());
                        scanner.nextLine();
                    }
                    break;

                case 2: // consultar
                    System.out.print("Digite o modelo do carro para consultar: ");
                    String modeloConsulta = scanner.nextLine();
                    ControleTrocaOleo consulta = gerenciador.buscarPorModelo(modeloConsulta);

                    if (consulta == null) {
                        System.out.println("⚠️ Veículo não encontrado.");
                        break;
                    }

                    if (consulta.isControlarPorKm()) {
                        try {
                            System.out.print("Informe a quilometragem atual do veículo: ");
                            int kmAtualConsulta = scanner.nextInt();
                            consulta.getCarro().setQuilometragem(kmAtualConsulta);
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Quilometragem inválida.");
                            scanner.nextLine();
                            break;
                        }
                    }

                    consulta.getCarro().exibirInfo();
                    consulta.exibirDetalhes();
                    consulta.exibirStatus();
                    consulta.exibirProximaTroca();
                    break;

                case 3: // registrar troca
                    System.out.print("Digite o modelo do carro para registrar troca: ");
                    String modeloTroca = scanner.nextLine();
                    ControleTrocaOleo troca = gerenciador.buscarPorModelo(modeloTroca);

                    if (troca == null) {
                        System.out.println("⚠️ Veículo não encontrado.");
                        break;
                    }

                    if (troca.isControlarPorKm()) {
                        try {
                            System.out.print("Informe a nova quilometragem atual: ");
                            int novaKm = scanner.nextInt();
                            troca.getCarro().setQuilometragem(novaKm);
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Quilometragem inválida.");
                            scanner.nextLine();
                            break;
                        }
                    }

                    troca.registrarNovaTroca();
                    break;

                case 4: // listar
                    gerenciador.listarVeiculos();
                    break;

                case 5: // sair
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Opção inválida.");
            }
        }
    }
}
