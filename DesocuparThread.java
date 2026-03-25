package Hotel;

public class DesocuparThread extends Thread{
	int quarto;
    Portaria portaria;

    public DesocuparThread(Portaria portaria, int quarto) {
        this.portaria = portaria;
        this.quarto = quarto;
    }
    public void run() {
        portaria.desocupar(quarto);
    }
}