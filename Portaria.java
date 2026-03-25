package Hotel;
import java.util.Scanner;

public class Portaria extends Quarto implements Reserva{
	Scanner leitor = new Scanner(System.in);
	Quarto quartos[] = new Quarto[10];
	
	public void inicioDeTudo() {
		for(int i=0; i<10; i++) {
			quartos[i] = new Quarto(" ", i+1, 0);
		}
	}
	@Override
    public synchronized void reservar(int quarto, String nome, int pessoas) {
        if(verificarSituação(quarto)==false) {
            quartos[quarto].nome_hospede = nome;
            quartos[quarto].num_pessoas = pessoas;
            quartos[quarto].numero_quarto = quarto+1;

            System.out.println("Reserva feita com sucesso!");
        } else {
            System.out.println("Quarto já ocupado.");
        }
    }
	@Override
    public synchronized void desocupar(int quarto) {
        if(verificarSituação(quarto)==true) {
            quartos[quarto].nome_hospede = " ";
            quartos[quarto].num_pessoas = 0;

            System.out.println("Quarto desocupado!");
        } else {
            System.out.println("Quarto já está vazio.");
        }
    }
	@Override
	public Boolean verificarSituação(int quarto) {
		if(quartos[quarto].num_pessoas==0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	 public synchronized void listar_desocupados() {
        for(int i=0; i<10; i++) {
            if(!verificarSituação(i)) {
                System.out.println("Quarto " + (i+1));
            }
        }
    }
	@Override
    public synchronized void listar_hospedes() {
        System.out.println("\n--- Situação atual ---");
        for(int i=0; i<10; i++) {
            if(verificarSituação(i)) {
                System.out.printf("Quarto %d | Hospede: %s| Pessoas %d \n", quartos[i].numero_quarto, quartos[i].nome_hospede, quartos[i].num_pessoas);
            }
        }
    }
	public void menu() {
		System.out.println("\nOque deseja fazer?");
		System.out.println("0 - Fechar hotel.");
		System.out.println("1 - Cadastrar um hospede.");
		System.out.println("2 - Listar os hospedes atuais.");
		System.out.println("3 - Desocupar um quarto.");
	}
	public void rodar() {
		inicioDeTudo();
		int op;
		do {
			menu();
			op = leitor.nextInt();
			switch(op) {
				case 1: 
					listar_desocupados();
	                System.out.print("Escolha o quarto: ");
	                int q = leitor.nextInt() - 1;
	                leitor.nextLine();
	                System.out.print("Nome: ");
	                String nome = leitor.nextLine();
	                System.out.print("Número de pessoas: ");
	                int p = leitor.nextInt();
	                ReservaThread t = new ReservaThread(this, q, nome, p);
	                t.start();
	                try {
	                    t.join();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                break;
				case 2: listar_hospedes(); break;
				case 3: 
					System.out.print("Quarto para desocupar: ");
					int d = leitor.nextInt() - 1;
					DesocuparThread t2 = new DesocuparThread(this, d);
					t2.start();
					try {
					    t2.join();
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
					break;
			}
		} while(op != 0);
		System.out.println("Sistema finalizado.");
	}
}