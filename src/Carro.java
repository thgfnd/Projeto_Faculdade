public class Carro {
    private String modelo;
    private int ano;
    private int quilometragem;

    public Carro(String modelo, int ano, int quilometragem) {
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
    }

    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public int getQuilometragem() { return quilometragem; }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void exibirInfo() {
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Quilometragem atual: " + quilometragem + " km");
    }
}
