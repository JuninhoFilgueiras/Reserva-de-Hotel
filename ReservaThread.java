package Hotel;

public class ReservaThread extends Thread{
	int quarto;
    String nome;
    int pessoas;
    Portaria portaria;

    public ReservaThread(Portaria portaria, int quarto, String nome, int pessoas) {
        this.portaria = portaria;
        this.quarto = quarto;
        this.nome = nome;
        this.pessoas = pessoas;
    }
    public void run() {
        portaria.reservar(quarto, nome, pessoas);
    }
}