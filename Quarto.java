package Hotel;

public class Quarto {
	Quarto(){}
	Quarto(String nome, int numero_quarto, int num_pessoas){
		this.nome_hospede = nome;
		this.numero_quarto = numero_quarto;
		this.num_pessoas = num_pessoas;
	}
	public String nome_hospede;
	public int numero_quarto, num_pessoas;
}