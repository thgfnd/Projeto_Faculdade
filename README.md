# Projeto Controle de Troca de Óleo

Sistema em Java para gerenciar veículos e controlar a troca de óleo com base em **quilometragem** e **tempo**.  

> ⚠️ Projeto em andamento

---

## Funcionalidades implementadas até agora
- Registrar veículos (modelo, ano, quilometragem)
- Consultar status da troca de óleo por KM e/ou tempo
- Registrar nova troca de óleo
- Listar veículos cadastrados

---

## Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/thgfnd/projeto_Controle.git
Abra o projeto no IntelliJ IDEA ou outra IDE Java.

Compile o projeto e execute a classe principal:

bash
Copiar código
Main.java
Siga as instruções do menu no console.

Exemplo de uso
yaml
Copiar código
=== MENU ===
1. Registrar novo veículo
2. Consultar status da troca de óleo
3. Registrar nova troca de óleo
4. Listar veículos
5. Sair
Escolha uma opção: 1

Modelo do carro: Honda Civic
Ano: 2020
Quilometragem atual: 15000
Intervalo de troca por KM (0 = não controlar): 5000
Intervalo de troca por TEMPO em meses (0 = não controlar): 6
Data da última troca (AAAA-MM-DD): 2025-02-01
KM da última troca: 10000

✅ Veículo registrado com sucesso!
Tecnologias usadas
Java 17+

IntelliJ IDEA (IDE recomendada)

Git e GitHub (para versionamento)

Estrutura do projeto
Carro.java → classe que representa o veículo

ControleTrocaOleo.java → lógica de controle da troca de óleo

GerenciadorVeiculos.java → gerencia lista de veículos

Main.java → menu e execução do programa

Status do projeto
Em andamento ⚠️

Funcionalidades básicas implementadas

Planejado: interface gráfica, persistência em banco de dados e melhorias de usabilidade
